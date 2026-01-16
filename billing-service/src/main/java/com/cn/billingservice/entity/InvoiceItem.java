package com.cn.billingservice.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Table(name="tbl_invoice_item")
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name="invoice_id")
    private UUID invoiceId;

    @Column(name="patient_id")
    private UUID patientId;

    @Column(name="charge_id")
    private UUID chargeId;

    @Column(name="description")
    private String description;

    @Column(name="amount")
    private BigDecimal amount;
}

