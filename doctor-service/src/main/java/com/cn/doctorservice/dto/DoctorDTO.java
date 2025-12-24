package com.cn.doctorservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class DoctorDTO {
    private UUID id;
    private String name;
    private List<SpecializationDTO> specializations;

}
