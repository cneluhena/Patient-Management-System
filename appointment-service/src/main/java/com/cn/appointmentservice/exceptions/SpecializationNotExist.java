package com.cn.appointmentservice.exceptions;

public class SpecializationNotExist extends RuntimeException {
    public SpecializationNotExist(String message) {
        super(message);
    }
}
