package com.cn.billingservice.entity;


import com.cn.billingservice.enums.BillingAccountType;
import com.cn.billingservice.enums.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="tbl_billing_account")
@Getter
@Setter
public class BillingAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name="patient_id")
    private UUID patientId;

    @Column(name="status")
    BillingAccountType status;

    @Column(name="balance")
    BigDecimal balance;

    @Column(name="currency")
    Currency currency;

    @Column(name="created_at")
    LocalDateTime createdTime;
}

