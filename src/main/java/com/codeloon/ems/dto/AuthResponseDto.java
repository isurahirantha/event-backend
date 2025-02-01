package com.codeloon.ems.dto;

import lombok.Data;

@Data
public class AuthResponseDto {

    private String accessToken;
    private String accessMsg;
    private String accessCode;
}