package com.cn.doctorservice.controller;


import com.cn.doctorservice.dto.ConsultationSessionDTO;
import com.cn.doctorservice.service.ConsultationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/consultation-session")
public class ConsultationSessionController {

    private final ConsultationService consultationService;

    public ConsultationSessionController(ConsultationService consultationService) {
        this.consultationService =  consultationService;
    }

    @PostMapping
    public ResponseEntity<?> createConsultationSession(@RequestBody ConsultationSessionDTO consultationSessionDTO) {
        ConsultationSessionDTO dto = consultationService.createConsultationSession(consultationSessionDTO);
        return ResponseEntity.ok().body(dto);
    }



}
