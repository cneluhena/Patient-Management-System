package com.cn.doctorservice.service;
import com.cn.doctorservice.dto.DoctorDTO;
import com.cn.doctorservice.entity.Doctor;
import com.cn.doctorservice.entity.Specialization;
import com.cn.doctorservice.exceptions.DoctorNotExists;
import com.cn.doctorservice.mappers.DoctorMapper;
import com.cn.doctorservice.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    private final SpecializationService specializationService;

    public DoctorService(DoctorRepository doctorRepository, SpecializationService specializationService) {
        this.doctorRepository = doctorRepository;
        this.specializationService = specializationService;
    }

    @Transactional
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {

        List<Specialization> specializations = specializationService.findSpecializationsThatExists(doctorDTO.getSpecializations());
        Doctor doctor = DoctorMapper.toEntity(doctorDTO);
        doctor.setSpecializations(specializations);
        Doctor newDoctor = doctorRepository.save(doctor);
        return DoctorMapper.toDTO(newDoctor);

    }

    @Transactional
    public DoctorDTO updateDoctor(UUID id, DoctorDTO doctorDTO) {

        Optional<Doctor> optional = doctorRepository.findById(id);
        if (optional.isEmpty()) {
            throw new DoctorNotExists("Doctor Not Exists");

        }

        List<Specialization> specializations = specializationService.findSpecializationsThatExists(doctorDTO.getSpecializations());

        Doctor doctor = DoctorMapper.toEntity(doctorDTO);
        doctor.setSpecializations(specializations);
        Doctor newDoctor = doctorRepository.save(doctor);
        return DoctorMapper.toDTO(newDoctor);

    }

    @Transactional
    public void deleteDoctor(UUID id) {

        if (doctorRepository.findById(id).isEmpty()) {
            throw new DoctorNotExists("Doctor Not Exists");
        }

        doctorRepository.deleteById(id);

    }


    public DoctorDTO getDoctorById(UUID id) {

        Optional<Doctor> optional = doctorRepository.findById(id);

        if (optional.isPresent()) {
            return DoctorMapper.toDTO(optional.get());
        }

        throw new DoctorNotExists("Doctor not exists");

    }

}
