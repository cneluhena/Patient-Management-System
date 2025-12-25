package com.cn.appointmentservice.controller;

import com.cn.appointmentservice.service.CircuitBreakerStatusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor/circuit")
public class CircuitController {

    private final CircuitBreakerStatusService statusService;

    public CircuitController(CircuitBreakerStatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public String getDoctorCircuitStatus() {
        return statusService.getDoctorServiceCircuitBreakerStatus();
    }
}
