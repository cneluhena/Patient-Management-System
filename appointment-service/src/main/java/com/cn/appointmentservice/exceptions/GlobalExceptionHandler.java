package com.cn.appointmentservice.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = { DoctorNotExists.class })
    public ResponseEntity<ApiError> handleDoctorNotExistException(DoctorNotExists e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(e.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

}
