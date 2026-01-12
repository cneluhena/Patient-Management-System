package com.cn.appointmentservice.mapper;

import com.cn.appointmentservice.dto.AppointmentDTO;
import com.cn.appointmentservice.entity.Appointment;

public class AppointmentMapper {

    public static Appointment toEntity(AppointmentDTO appointmentDTO) {

        Appointment appointment = new Appointment();
        appointment.setId(appointmentDTO.getId());
        appointment.setSessionId(appointmentDTO.getSessionId());
        appointment.setPatientId(appointmentDTO.getPatientId());
        appointment.setCreatedAt(appointmentDTO.getCreatedAt());
        appointment.setAppointmentDateTime(appointmentDTO.getAppointmentDateTime());
        appointment.setStatus(appointmentDTO.getStatus());
        return appointment;

    }
}
