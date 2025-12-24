package com.cn.doctorservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="tbl_doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="tbl_doctor_specialization_mapping",
            joinColumns= @JoinColumn(name="doctor_id"),
            inverseJoinColumns = @JoinColumn(name="specialization_id")

    )
    private List<Specialization> specializations;

}
