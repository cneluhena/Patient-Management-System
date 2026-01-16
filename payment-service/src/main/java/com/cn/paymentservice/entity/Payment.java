package com.cn.paymentservice.entity;

import com.cn.paymentservice.enums.PaymentMethod;
import com.cn.paymentservice.enums.PaymentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="tbl_payment")
public class Payment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private UUID id;

    @Column(name="billing_account")
    private String billingAccountId;

    @Column(name="amount")
    private BigDecimal amount;

    @Column(name="payment_method")
    private PaymentMethod paymentMethod;

    @Column(name="payment_status")
    private PaymentStatus paymentStatus;

    @Column(name="created_date")
    private LocalDateTime createdDate;

}
