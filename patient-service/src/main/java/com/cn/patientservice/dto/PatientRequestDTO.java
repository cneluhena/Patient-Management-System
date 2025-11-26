package com.cn.patientservice.dto;

import com.cn.patientservice.dto.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PatientRequestDTO {

    @NotBlank
    @Size(min = 2, max = 100, message="Name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message="Email Should Be Valid")
    private String email;

    @NotBlank(message="Address is required")
    private String address;

    @NotBlank(message="Phone number is required")
    private String phone;

    @NotBlank(message="Registered date is required")
    private String dateOfBirth;

    @NotBlank(groups= CreatePatientValidationGroup.class,message="Registered Date is required")
    private String registeredDate;

}
