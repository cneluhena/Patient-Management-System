package com.cn.appointmentservice.controller;


import com.cn.appointmentservice.dto.AppointmentDTO;
import com.cn.appointmentservice.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentDTO appointmentDto) {
        String doctor = appointmentService.createAppointment(appointmentDto);
        return ResponseEntity.ok().body(doctor);
    }

}
