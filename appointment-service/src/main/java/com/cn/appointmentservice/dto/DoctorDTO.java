package com.cn.appointmentservice.dto;

import java.util.List;
import java.util.UUID;

public class DoctorDTO {
    private UUID id;
    private String name;
    private List<SpecializationDTO> specializations;
}
