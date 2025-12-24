package com.cn.appointmentservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name="appointment")
@Getter
@Setter
public class Appointment {

    @Id
    @Column(name="id")
    private UUID id;

    @Column(name="doctor_id")
    private UUID doctorId;

    @Column(name="patient_id")
    private UUID patientId;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="appointment_date_time")
    private LocalDateTime appointmentDateTime;


}
