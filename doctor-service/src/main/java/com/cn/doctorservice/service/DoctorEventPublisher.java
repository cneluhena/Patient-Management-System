package com.cn.doctorservice.service;

import com.cn.doctorservice.dto.DoctorDTO;
import com.cn.doctorservice.entity.Doctor;
import com.cn.doctorservice.mappers.SpecializationMapper;
import com.cn.protos.DoctorEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorEventPublisher {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public DoctorEventPublisher(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishDoctorCreated(Doctor doctor) {

        List<String> specializations = SpecializationMapper.mapSpecializations(doctor.getSpecializations());

        DoctorEvent event = DoctorEvent.newBuilder()
                .setEventType(DoctorEvent.EventType.DOCTOR_CREATED)
                .setDoctorId(doctor.getId().toString())
                .setName(doctor.getName())
                .addAllSpecializations(specializations)
                .build();

        kafkaTemplate.send("doctor-events", doctor.getId().toString(), event.toByteArray());

    }


}
