package com.cn.doctorservice.controller;

import com.cn.doctorservice.dto.SpecializationDTO;
import com.cn.doctorservice.service.SpecializationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctor-specialization")
public class SpecializationController {

    private final SpecializationService specializationService;

    public SpecializationController(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @PostMapping
    public ResponseEntity<SpecializationDTO> createSpecialization(@RequestBody SpecializationDTO specializationDto){
        SpecializationDTO dto = specializationService.createSpecialization(specializationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecializationDTO> getSpecialization(@PathVariable Long id){
        SpecializationDTO dto = specializationService.getSpecializationById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping
    public ResponseEntity<SpecializationDTO> updateSpecialization(@RequestBody SpecializationDTO specializationDto){
        SpecializationDTO dto = specializationService.updateSpecialization(specializationDto);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialization(@PathVariable Long id){
        specializationService.deleteSpecializationById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
