package com.cn.appointmentservice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class AppointmentDTO {

    private UUID id;

    private UUID doctorId;

    private UUID patientId;

    private LocalDateTime createdAt;

    private LocalDateTime appointmentDateTime;

}
