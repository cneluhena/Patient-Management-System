package com.cn.doctorservice.service;

import com.cn.doctorservice.dto.SpecializationDTO;
import com.cn.doctorservice.entity.Specialization;
import com.cn.doctorservice.exceptions.SpecializationNotExist;
import com.cn.doctorservice.mappers.SpecializationMapper;
import com.cn.doctorservice.repository.SpecializationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SpecializationService {

    private final SpecializationRepository specializationRepository;

    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;

    }

    public Specialization findSpecializationById(Long id) {
        Optional<Specialization> optional = specializationRepository.findById(id);
        return optional.orElse(null);
    }

    public List<Specialization> findAllSpecializationsByIds(Set<Long> ids) {
        return specializationRepository.findAllById(ids);
    }

    public List<Specialization> findSpecializationsThatExists(List<SpecializationDTO> specializationDTOS) {

        Set<Long> ids = specializationDTOS.stream().map(SpecializationDTO::getId).collect(Collectors.toSet());

        List<Specialization> specializations = specializationRepository.findAllById(ids);

        Set<Long> foundIds = specializations.stream().map(Specialization::getId).collect(Collectors.toSet());
        Set<Long> missingIds = ids.stream().filter(id -> !foundIds.contains(id)).collect(Collectors.toSet());

        if (!missingIds.isEmpty()) {
            throw new SpecializationNotExist("Specializations with ids" + missingIds + "not exist" );
        }

        return specializations;

    }

    @Transactional
    public SpecializationDTO createSpecialization(SpecializationDTO specializationDto){

        Specialization specialization = SpecializationMapper.toEntity(specializationDto);
        Specialization newSpecialization = specializationRepository.save(specialization);
        return SpecializationMapper.toDto(newSpecialization);
    }

    public SpecializationDTO getSpecializationById(Long id){

        Optional<Specialization> optional = specializationRepository.findById(id);

        if (optional.isPresent()) {
            return SpecializationMapper.toDto(optional.get());
        }

        throw new SpecializationNotExist("Specialization not exist");

    }

    public void deleteSpecializationById(Long id) {

        Optional<Specialization> optional = specializationRepository.findById(id);

        if (optional.isEmpty()){
            throw new SpecializationNotExist("Specialization not exist");
        }
        specializationRepository.deleteById(id);

    }

    
    @Transactional
    public SpecializationDTO updateSpecialization(SpecializationDTO specializationDto) {

        if (specializationRepository.findById(specializationDto.getId()).isPresent()) {
            Specialization specialization = SpecializationMapper.toEntity(specializationDto);
            Specialization newSpecialization = specializationRepository.save(specialization);
            return SpecializationMapper.toDto(newSpecialization);
        }

        throw new SpecializationNotExist("Specialization not exist");

    }

}
