package com.cn.billingservice.entity;

import com.cn.billingservice.enums.Service;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="tbl_billing_charge")
@Getter
@Setter
public class BillingCharge {

    @Id
    private UUID id;

    @Column(name="billing_account_id")
    private UUID billingAccountId;

    @Column(name="source_service")
    private Service sourceService;

    @Column(name="reference_id")
    private UUID referenceId;

    @Column(name="description")
    private String description;

    @Column(name="amount")
    private BigDecimal amount;

    @Column(name="created_at")
    private LocalDateTime createdAt;


}

