package com.cn.appointmentservice.service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CircuitBreakerStatusService {

    private final CircuitBreakerRegistry registry;

    public CircuitBreakerStatusService(CircuitBreakerRegistry registry) {
        this.registry = registry;
    }

    public String getDoctorServiceCircuitBreakerStatus() {
        CircuitBreaker cb = registry.circuitBreaker("doctorService");
        log.info("I am chamod neluhena");
        log.info("I am chamod neluhena");
        log.info("I am chamod neluhena");
        log.info("I am chamod neluhena");


        return cb.getState().name(); // CLOSED, OPEN, HALF_OPEN
    }
}