package com.cn.appointmentservice.service;

import com.cn.appointmentservice.dto.AppointmentDTO;
import com.cn.appointmentservice.entity.Appointment;
import com.cn.appointmentservice.enums.AppointmentStatus;
import com.cn.appointmentservice.mapper.AppointmentMapper;
import com.cn.appointmentservice.repository.AppointmentRepository;
import com.cn.protos.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final AppointmentServiceEventPublisher appointmentServiceEventPublisher;

    @GrpcClient("doctor-service")
    private DoctorServiceGrpc.DoctorServiceBlockingStub doctorStub;

    @GrpcClient("doctor-service")
    private ConsultationServiceGrpc.ConsultationServiceBlockingStub consultationStub;


    public AppointmentService(AppointmentRepository appointmentRepository,  AppointmentServiceEventPublisher appointmentServiceEventPublisher) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentServiceEventPublisher = appointmentServiceEventPublisher;
    }

    //@CircuitBreaker(name = "doctorService", fallbackMethod = "fallback")
    @Transactional
    public void createAppointment(AppointmentDTO appointmentDto) {
        appointmentDto.setCreatedAt(LocalDateTime.now());
        log.info("Creating appointment for appointment {}", appointmentDto.getSessionId());
        appointmentDto.setStatus(AppointmentStatus.PENDING);
        Appointment appointment = AppointmentMapper.toEntity(appointmentDto);
        appointmentRepository.save(appointment);
        appointmentServiceEventPublisher.publishAppointmentCreatedEvent(appointment);
        log.info("Appointment Created Successfully");

    }

    public void fallback(AppointmentDTO appointmentDto, Throwable ex) {
        log.info("Help me");
    }

}
