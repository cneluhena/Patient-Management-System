package com.cn.doctorservice.entity;

import jakarta.persistence.*;
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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    @Column(name="session_time")
    private LocalDateTime time;

    @Column(name="max_patients")
    private int maxNumOfPatients;

    @Column(name="booked_patients")
    private int bookedNumOfPatients;


}
