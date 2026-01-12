package com.cn.appointmentservice.exceptions;

public class AppointmentNotExists extends RuntimeException {
    public AppointmentNotExists(String message) {
        super(message);
    }
}
