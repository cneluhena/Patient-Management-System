package com.cn.appointmentservice.service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.stereotype.Service;

@Service
public class CircuitBreakerStatusService {

    private final CircuitBreakerRegistry registry;

    public CircuitBreakerStatusService(CircuitBreakerRegistry registry) {
        this.registry = registry;
    }

    public String getDoctorServiceCircuitBreakerStatus() {
        CircuitBreaker cb = registry.circuitBreaker("doctorService");
        return cb.getState().name(); // CLOSED, OPEN, HALF_OPEN
    }
}