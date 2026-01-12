package com.cn.doctorservice.exceptions;

public class ConsultationSessionNotExists extends RuntimeException {
    public ConsultationSessionNotExists(String message) {
        super(message);
    }
}
