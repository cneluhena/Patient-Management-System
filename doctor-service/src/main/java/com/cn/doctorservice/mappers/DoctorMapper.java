package com.cn.doctorservice.mappers;

import com.cn.doctorservice.dto.DoctorDTO;
import com.cn.doctorservice.dto.SpecializationDTO;
import com.cn.doctorservice.entity.Doctor;
import com.cn.doctorservice.entity.Specialization;

import java.util.List;

public class DoctorMapper {

    public static Doctor toEntity(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setId(doctorDTO.getId());
        doctor.setName(doctorDTO.getName());
        List<Specialization> specializationList = doctorDTO.getSpecializations().stream().map(SpecializationMapper::toEntity).toList();
        doctor.setSpecializations(specializationList);
        return doctor;
    }

    public static DoctorDTO toDTO(Doctor doctor) {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctor.getId());
        doctorDTO.setName(doctor.getName());
        List<SpecializationDTO> specializations = doctor.getSpecializations().stream().map(SpecializationMapper::toDto).toList();
        doctorDTO.setSpecializations(specializations);
        return doctorDTO;
    }


}
