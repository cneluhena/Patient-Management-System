package com.cn.appointmentservice.exceptions;

public class DoctorNotExists extends RuntimeException {

    public DoctorNotExists(String message) {
        super(message);
    }

}

