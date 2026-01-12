package com.cn.doctorservice.service;

import com.cn.doctorservice.dto.ConsultationSessionDTO;
import com.cn.doctorservice.entity.ConsultationSession;
import com.cn.doctorservice.entity.Doctor;
import com.cn.doctorservice.exceptions.DoctorNotExists;
import com.cn.doctorservice.mappers.ConsultationMapper;
import com.cn.doctorservice.repository.ConsultationRepository;
import com.cn.doctorservice.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@Slf4j
public class ConsultationService {

    private final ConsultationRepository consultationRepository;

    private final DoctorRepository doctorRepository;

    public ConsultationService(ConsultationRepository consultationRepository, DoctorRepository doctorRepository) {
        this.consultationRepository = consultationRepository;
        this.doctorRepository = doctorRepository;
    }

    @Transactional
    public ConsultationSessionDTO createConsultationSession(ConsultationSessionDTO consultationSessionDTO) {

            Doctor doctor = doctorRepository.findById(consultationSessionDTO.getDoctorId())
                    .orElseThrow(() -> new DoctorNotExists("Doctor Not Exists"));

            consultationSessionDTO.setId(UUID.randomUUID());
            ConsultationSession consultationSession = ConsultationMapper.toEntity(consultationSessionDTO, doctor);
            consultationRepository.save(consultationSession);
            log.info("ConsultationSession Created Successfully");
            return consultationSessionDTO;

    }

}
