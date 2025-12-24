package com.cn.doctorservice.exceptions;

public class SpecializationNotExist extends RuntimeException {
    public SpecializationNotExist(String message) {
        super(message);
    }
}
