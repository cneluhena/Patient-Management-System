package com.cn.appointmentservice.service;

import com.cn.appointmentservice.entity.Appointment;
import com.cn.protos.AppointmentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AppointmentServiceEventPublisher {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public AppointmentServiceEventPublisher(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishAppointmentCreatedEvent(Appointment appointment) {

        log.info("publishing");

        AppointmentEvent event = AppointmentEvent.newBuilder()
                .setId(appointment.getId().toString())
                .setEventType(AppointmentEvent.EventType.APPOINTMENT_CREATED)
                .setSessionId(appointment.getSessionId().toString())
                .build();

        kafkaTemplate.send("appointment-events", event.toByteArray())
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Event published to topic={}, partition={}, offset={}",
                                result.getRecordMetadata().topic(),
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                    } else {
                        log.error("Failed to publish event", ex);
                    }
                });

    }

}
