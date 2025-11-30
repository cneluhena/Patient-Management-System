package com.cn.authservice.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class LoginResponseDTO {

    private String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }


}
