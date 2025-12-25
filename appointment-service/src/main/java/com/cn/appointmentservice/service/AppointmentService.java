package com.cn.appointmentservice.service;


import com.cn.appointmentservice.dto.AppointmentDTO;
import com.cn.appointmentservice.entity.Appointment;
import com.cn.appointmentservice.mapper.AppointmentMapper;
import com.cn.appointmentservice.repository.AppointmentRepository;
import com.cn.protos.DoctorGetRequest;
import com.cn.protos.DoctorGetResponse;
import com.cn.protos.DoctorServiceGrpc;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @GrpcClient("doctor-service")
    private DoctorServiceGrpc.DoctorServiceBlockingStub doctorStub;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional
    @CircuitBreaker(name = "doctorService", fallbackMethod = "fallback")
    public String createAppointment(AppointmentDTO appointmentDto) {

        DoctorGetResponse response = doctorStub
                .withDeadlineAfter(2,TimeUnit.SECONDS)
                .getDoctorDetails(
                DoctorGetRequest.newBuilder().setDoctorId(appointmentDto.getDoctorId().toString()).build()
        );

        Appointment appointment = AppointmentMapper.toEntity(appointmentDto);

        return response.getName();

    }

    public String fallback(AppointmentDTO appointmentDto, Throwable ex) {
        log.info("Help me");
        return "Fall back method";
    }




}
