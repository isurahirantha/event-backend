package com.codeloon.ems.service;

import com.codeloon.ems.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}