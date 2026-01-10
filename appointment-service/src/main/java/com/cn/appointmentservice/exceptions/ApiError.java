package com.cn.appointmentservice.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApiError {
    private String error;
    private int status;
}
