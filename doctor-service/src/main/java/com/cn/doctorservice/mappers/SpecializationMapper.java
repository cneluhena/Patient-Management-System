package com.cn.doctorservice.mappers;

import com.cn.doctorservice.dto.SpecializationDTO;
import com.cn.doctorservice.entity.Specialization;
import com.cn.protos.DoctorEvent;

import java.util.List;
import java.util.stream.Collectors;

public class SpecializationMapper {

    public static Specialization toEntity(SpecializationDTO dto) {
        Specialization specialization = new Specialization();
        specialization.setId(dto.getId());
        specialization.setSpecialization(dto.getName());
        return specialization;
    }

    public static SpecializationDTO toDto(Specialization specialization) {
        SpecializationDTO specializationDTO = new SpecializationDTO();
        specializationDTO.setId(specialization.getId());
        specializationDTO.setName(specialization.getSpecialization());
        return specializationDTO;
    }

    public static List<String> mapSpecializations(List<Specialization> dtos) {
        return dtos.stream()
                .map(Specialization::getSpecialization)
                .collect(Collectors.toList());
    }



}
