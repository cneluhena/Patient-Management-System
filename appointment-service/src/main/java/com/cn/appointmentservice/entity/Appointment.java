package com.cn.appointmentservice.entity;

import com.cn.appointmentservice.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name="tbl_appointment")
@Getter
@Setter
public class Appointment {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name="session_id")
    private UUID sessionId;

    @Column(name="patient_id")
    private UUID patientId;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="appointment_status")
    private AppointmentStatus status;

    @Column(name="appointment_date_time")
    private LocalDateTime appointmentDateTime;


}
