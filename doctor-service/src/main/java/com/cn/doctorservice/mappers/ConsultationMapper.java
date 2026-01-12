package com.cn.doctorservice.mappers;


import com.cn.doctorservice.dto.ConsultationSessionDTO;
import com.cn.doctorservice.entity.ConsultationSession;
import com.cn.doctorservice.entity.Doctor;

public class ConsultationMapper {

    public static ConsultationSession toEntity(ConsultationSessionDTO consultationSessionDTO, Doctor doctor) {

        ConsultationSession consultationSession = new ConsultationSession();
        consultationSession.setId(consultationSessionDTO.getId());
        consultationSession.setDoctor(doctor);
        consultationSession.setTime(consultationSessionDTO.getTime());
        consultationSession.setMaxNumOfPatients(consultationSessionDTO.getNumberOfPatients());

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
