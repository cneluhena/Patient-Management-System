package com.cn.appointmentservice.service;


import com.cn.appointmentservice.entity.Doctor;
import com.cn.appointmentservice.repository.DoctorRepository;
import com.cn.protos.DoctorEvent;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
public class DoctorEventConsumerService {

    private final DoctorRepository doctorRepository;

    public DoctorEventConsumerService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Transactional
    @KafkaListener(topics = "doctor-events", groupId = "appointment-service")
    public void consumeDoctorEvent(byte[] event) {
        try {
            DoctorEvent doctorEvent = DoctorEvent.parseFrom(event);

            if ("DOCTOR_CREATED".equals(doctorEvent.getEventType().toString())) {
                Doctor doctor = new Doctor();
                doctor.setId(UUID.fromString(doctorEvent.getDoctorId()));
                doctor.setName(doctorEvent.getName());
                doctor.setSpecializations(doctorEvent.getSpecializationsList());
                doctorRepository.save(doctor);
                log.info("Doctor Created Event Recieved");
            }

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }

}
