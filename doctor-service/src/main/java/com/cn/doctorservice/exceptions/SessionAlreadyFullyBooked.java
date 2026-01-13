package com.cn.doctorservice.exceptions;

public class SessionAlreadyFullyBooked extends RuntimeException {
    public SessionAlreadyFullyBooked(String message) {
        super(message);
    }
}
