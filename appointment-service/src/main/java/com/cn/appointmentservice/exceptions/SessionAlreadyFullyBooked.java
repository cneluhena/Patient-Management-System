package com.cn.appointmentservice.exceptions;

public class SessionAlreadyFullyBooked extends RuntimeException {
    public SessionAlreadyFullyBooked(String message) {
        super(message);
    }
}
