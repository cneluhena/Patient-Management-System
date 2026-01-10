package com.cn.appointmentservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name="tbl_consultation_session")
@Getter
@Setter
public class ConsultationSession {

    @Id
    @Column(name="id")
    private UUID id;

    @Column(name="doctor_id")
    private UUID doctorId;

    @Column(name="time")
    private LocalDateTime time;

    @Column(name="number_of_patients")
    private int numberOfPatients;

}
