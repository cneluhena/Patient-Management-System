package com.cn.appointmentservice.service;

import com.cn.appointmentservice.entity.Appointment;
import com.cn.appointmentservice.entity.Doctor;
import com.cn.appointmentservice.enums.AppointmentStatus;
import com.cn.appointmentservice.exceptions.AppointmentNotExists;
import com.cn.appointmentservice.repository.AppointmentRepository;
import com.cn.appointmentservice.repository.DoctorRepository;
import com.cn.protos.AppointmentEvent;
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

    private final AppointmentRepository appointmentRepository;

    public DoctorEventConsumerService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
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

    @Transactional
    @KafkaListener(topics = "appointment-events", groupId = "appointment-confirmed-service")
    public void consumeAppointmentEvent(byte[] event) {
        log.info("Appointment confirmed Event Received");
        try {
            AppointmentEvent appointmentEvent = AppointmentEvent.parseFrom(event);

            if ("APPOINTMENT_CONFIRMED".equals(appointmentEvent.getEventType().toString())) {
                Appointment appointment = appointmentRepository.findById(UUID.fromString(appointmentEvent.getId())).orElseThrow(()->new AppointmentNotExists("Appointment does not Exists"));
                appointment.setStatus(AppointmentStatus.CONFIRMED);
                log.info("Appointment Confirmed Event Processed");
            }

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }

}
