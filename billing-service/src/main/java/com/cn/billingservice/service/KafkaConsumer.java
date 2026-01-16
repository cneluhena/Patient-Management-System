package com.cn.billingservice.service;

import com.cn.billingservice.entity.BillingAccount;
import com.cn.billingservice.enums.BillingAccountType;
import com.cn.billingservice.repository.BillingAccountRepository;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.cn.protos.PatientEvent;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class KafkaConsumer {

    private final BillingAccountRepository billingAccountRepository;

    public KafkaConsumer(BillingAccountRepository billingAccountRepository) {
        this.billingAccountRepository = billingAccountRepository;
    }

    @KafkaListener(topics = "patient.patients", groupId = "patient-service")
    public void consumePatientCreateEvent(byte[] event) {
        try {

            PatientEvent patientEvent = PatientEvent.parseFrom(event);

            if ("PATIENT_CREATED".equals(patientEvent.getEventType().toString())) {
               createBillingAccount(patientEvent.getPatientId());
            }

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    public void createBillingAccount(String patientId) {
        BillingAccount billingAccount = new BillingAccount();
        billingAccount.setPatientId(UUID.fromString(patientId));
        billingAccount.setStatus(BillingAccountType.ACTIVE);
        billingAccount.setBalance(BigDecimal.ZERO);
        billingAccount.setCreatedTime(LocalDateTime.now());
        billingAccountRepository.save(billingAccount);
    }


}
