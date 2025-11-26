package com.cn.patientservice.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PatientResponseDTO {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String datOfBirth;

}
