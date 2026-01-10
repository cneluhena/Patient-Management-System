package com.cn.appointmentservice.controller;

import com.cn.appointmentservice.dto.ConsultationSessionDTO;
import com.cn.appointmentservice.service.ConsultationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/consultation-session")
public class ConsultingSessionController {

    private final ConsultationService consultationService;

    public ConsultingSessionController(ConsultationService consultationService) {
        this.consultationService =  consultationService;
    }

    @PostMapping
    public ResponseEntity<?> createConsultationSession(@RequestBody ConsultationSessionDTO consultationSessionDTO) {
        ConsultationSessionDTO dto = consultationService.createConsultationSession(consultationSessionDTO);
        return ResponseEntity.ok().body(dto);
    }



}
