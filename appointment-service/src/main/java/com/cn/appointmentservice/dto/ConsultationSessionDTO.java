package com.cn.appointmentservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ConsultationSessionDTO {

    private UUID id;
    private UUID doctorId;
    private LocalDateTime time;
    private int numberOfPatients;

}
