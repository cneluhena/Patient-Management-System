package com.cn.appointmentservice.service;

import com.cn.appointmentservice.dto.ConsultationSessionDTO;
import com.cn.appointmentservice.entity.Appointment;
import com.cn.appointmentservice.entity.ConsultationSession;
import com.cn.appointmentservice.exceptions.DoctorNotExists;
import com.cn.appointmentservice.mapper.AppointmentMapper;
import com.cn.appointmentservice.mapper.ConsultationMapper;
import com.cn.appointmentservice.repository.ConsultationRepository;
import com.cn.protos.DoctorGetRequest;
import com.cn.protos.DoctorGetResponse;
import com.cn.protos.DoctorServiceGrpc;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ConsultationService {

    private final ConsultationRepository consultationRepository;

    public ConsultationService(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @GrpcClient("doctor-service")
    private DoctorServiceGrpc.DoctorServiceBlockingStub doctorStub;

    @Transactional
    public ConsultationSessionDTO createConsultationSession(ConsultationSessionDTO consultationSessionDTO) {
        try {
            DoctorGetResponse response = doctorStub
                    .withDeadlineAfter(2, TimeUnit.SECONDS)
                    .getDoctorDetails(
                            DoctorGetRequest.newBuilder().setDoctorId(consultationSessionDTO.getDoctorId().toString()).build()
                    );

            consultationSessionDTO.setId(UUID.randomUUID());
            ConsultationSession consultationSession = ConsultationMapper.toEntity(consultationSessionDTO);
            consultationRepository.save(consultationSession);
            return consultationSessionDTO;
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                throw new DoctorNotExists("Requested doctor does not exist");
            } else {
                throw new RuntimeException("Doctor service error: " + e.getStatus().getDescription());
            }
        }

    }

//    public ConsultationSessionDTO getConsultationSession(UUID id) {
//
//        Optional<ConsultationSession> optional = consultationRepository.findById(id);
//
//        if (optional.isPresent()) {
//
//        }
//    }

}
