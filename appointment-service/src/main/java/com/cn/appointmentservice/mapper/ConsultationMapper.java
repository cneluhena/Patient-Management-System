package com.cn.appointmentservice.mapper;

import com.cn.appointmentservice.dto.ConsultationSessionDTO;
import com.cn.appointmentservice.entity.ConsultationSession;

public class ConsultationMapper {

    public static ConsultationSession toEntity(ConsultationSessionDTO consultationSessionDTO) {

        ConsultationSession consultationSession = new ConsultationSession();
        consultationSession.setId(consultationSessionDTO.getId());
        consultationSession.setDoctorId(consultationSessionDTO.getDoctorId());
        consultationSession.setTime(consultationSessionDTO.getTime());
        consultationSession.setNumberOfPatients(consultationSessionDTO.getNumberOfPatients());

        return consultationSession;

    }

//    public static ConsultationSessionDTO toDTO(ConsultationSession consultationSession) {
//        ConsultationSessionDTO dto = new ConsultationSessionDTO();
//        dto.setId(consultationSession.getId());
//        dto.setDoctorId(consultationSession.getDoctorId());
//        dto.setTime(consultationSession.getTime());
//        dto.setNumberOfPatients(consultationSession.getNumberOfPatients());
//    }

}
