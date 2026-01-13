package com.cn.patientservice.kafka;

import com.cn.patientservice.entity.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;


@Service
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(Patient patient) {

        PatientEvent patientEvent = PatientEvent.newBuilder()
                .setPatientId(patient.getId().toString())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .setEventType("Patient Created")
                .build();

        try {
            kafkaTemplate.send("patient", patientEvent.toByteArray());
        } catch (Exception ex) {
            log.error("Error sending Patient Created Event {}", patientEvent);

        }

    }


}
