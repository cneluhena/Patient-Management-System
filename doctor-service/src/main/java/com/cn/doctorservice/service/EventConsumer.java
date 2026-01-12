package com.cn.doctorservice.service;

import com.cn.doctorservice.entity.ConsultationSession;
import com.cn.doctorservice.repository.ConsultationRepository;
import com.cn.doctorservice.repository.DoctorRepository;
import com.cn.protos.AppointmentEvent;
import com.cn.protos.DoctorEvent;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
public class EventConsumer {

    private final ConsultationRepository consultationRepository;

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public EventConsumer(ConsultationRepository consultationRepository, KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.consultationRepository = consultationRepository;
        this.kafkaTemplate = kafkaTemplate;

    }

    @Transactional
    @KafkaListener(topics = "appointment-events", groupId = "appointment-service")
    public void consumeAppointmentEvent(byte[] event) {

        try {
            log.info("Received appointment event");
            AppointmentEvent appointmentEvent = AppointmentEvent.parseFrom(event);

            if ("APPOINTMENT_CREATED".equals(appointmentEvent.getEventType().toString())) {

                int updated = consultationRepository.reserveSession(UUID.fromString(appointmentEvent.getSessionId()));

                if (updated == 0) {
                    log.error("Session Already Fully Booked");
                } else {

                    AppointmentEvent confirmedEvent = AppointmentEvent.newBuilder()
                            .setId(appointmentEvent.getId())
                            .setSessionId(appointmentEvent.getSessionId())
                            .setEventType(AppointmentEvent.EventType.APPOINTMENT_CONFIRMED)
                            .build();

                    kafkaTemplate.send("appointment-events", UUID.randomUUID().toString(), confirmedEvent.toByteArray());
                    log.info("Appointment Confirmed Event Produced");

                }


            }

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }

}
