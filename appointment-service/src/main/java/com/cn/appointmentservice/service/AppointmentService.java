package com.cn.appointmentservice.service;

import com.cn.appointmentservice.dto.AppointmentDTO;
import com.cn.appointmentservice.entity.Appointment;
import com.cn.appointmentservice.enums.AppointmentStatus;
import com.cn.appointmentservice.exceptions.SessionAlreadyFullyBooked;
import com.cn.appointmentservice.mapper.AppointmentMapper;
import com.cn.appointmentservice.repository.AppointmentRepository;
import com.cn.protos.*;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;


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
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDto) {

        try {

            SessionReserveRequest request = SessionReserveRequest.newBuilder()
                    .setSessionId(appointmentDto.getSessionId().toString())
                    .build();

            SessionReserveResponse response = consultationStub.reserveConsultationSession(request);
            appointmentDto.setCreatedAt(LocalDateTime.now());
            log.info("Creating appointment for appointment {}", appointmentDto.getSessionId());
            appointmentDto.setStatus(AppointmentStatus.PENDING);
            Appointment appointment = AppointmentMapper.toEntity(appointmentDto);
            appointmentRepository.save(appointment);
            log.info("Appointment Created Successfully");
            return appointmentDto;

        } catch (StatusRuntimeException e) {

            assert e.getStatus().getDescription() != null;

            if (e.getStatus().getDescription().equals("SESSION_BOOKED")) {
                log.error("Failed to reserve consultation session", e.getStatus());
                throw new SessionAlreadyFullyBooked(
                        "Failed to reserve consultation session: " + e.getStatus().getDescription()
                );
            } else {
                throw new RuntimeException("Error while reserving the session");
            }


        }

    }

    public void fallback(AppointmentDTO appointmentDto, Throwable ex) {
        log.info("Help me");
    }

}
