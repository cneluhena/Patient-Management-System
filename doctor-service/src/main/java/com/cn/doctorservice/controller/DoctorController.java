package com.cn.doctorservice.controller;


import com.cn.doctorservice.dto.DoctorDTO;
import com.cn.doctorservice.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    //method for creating a new doctor
    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO){
            DoctorDTO dto = doctorService.createDoctor(doctorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable UUID id){
        DoctorDTO dto = doctorService.getDoctorById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> updateDoctorById(@PathVariable UUID id, @RequestBody DoctorDTO doctorDTO){
        DoctorDTO dto = doctorService.updateDoctor(id, doctorDTO);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DoctorDTO> deleteDoctorById(@PathVariable UUID id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

