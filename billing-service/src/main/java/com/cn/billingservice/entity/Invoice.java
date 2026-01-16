package com.cn.billingservice.entity;


import com.cn.billingservice.enums.InvoiceStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="tbl_invoice")
@Getter
@Setter
public class Invoice {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private UUID id;

    @Column(name="billing_account_if")
    private UUID billingAccountID;

    @Column(name="invoice_number")
    private String invoiceNumber;

    @Column(name="total_amount")
    private BigDecimal totalAmount;

    @Column(name="invoice_status")
    private InvoiceStatus invoiceStatus;

    @Column(name="created_date")
    private LocalDateTime createdDate;

}


