package com.codeloon.ems.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {

    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 10, message = "Username must be between 4 and 10 characters")
    private String username;

    @NotBlank(message = "Password is required")
    // @Size(min = 4, max = 10, message = "Password must be between 4 and 10 characters")
    private String password;

}