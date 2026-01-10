package com.cn.appointmentservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="tbl_doctor")
public class Doctor {

    @Id
    private UUID id;

    private String name;

    @ElementCollection
    @CollectionTable(
            name = "doctor_specializations",
            joinColumns = @JoinColumn(name = "doctor_id")
    )
    @Column(name = "specialization")
    private List<String> specializations;

}
