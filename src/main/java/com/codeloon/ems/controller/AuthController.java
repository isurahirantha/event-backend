package com.codeloon.ems.controller;

import com.codeloon.ems.dto.AuthResponseDto;
import com.codeloon.ems.dto.LoginDto;
import com.codeloon.ems.service.AuthService;
import com.codeloon.ems.util.DataVarList;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * Authenticates the user with the provided login credentials.
     * If authentication is successful, returns a JWT access token.
     * If authentication fails, an appropriate error message, access code, and HTTP status code are returned.
     *
     * <p><b>Access Codes:</b></p>
     * <ul>
     *     <li><b>0</b> - Authentication successful, returns access token.</li>
     *     <li><b>1</b> - Password reset required, redirect to password reset page.</li>
     *     <li><b>2</b> - Account issue, user should contact admin.</li>
     * </ul>
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        try {
            return buildResponse(authService.login(loginDto), DataVarList.SUCCESS_AUTH, DataVarList.AUTH_SUCCESS, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return handleAuthenticationException(e);
        }
    }

    private ResponseEntity<AuthResponseDto> buildResponse(String token, String accessMsg, String accessCode, HttpStatus httpStatus) {
        AuthResponseDto authResponseDto = AuthResponseDto.builder()
                .accessCode(accessCode)
                .accessToken(token)
                .accessMsg(accessMsg)
                .build();
        return new ResponseEntity<>(authResponseDto, httpStatus);
    }

    private ResponseEntity<AuthResponseDto> handleAuthenticationException(AuthenticationException e) {
        String accessMsg = "";
        String accessCode = DataVarList.AUTH_FAILED_ACCOUNT_ISSUE;
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if (e instanceof DisabledException) {
            accessMsg = DataVarList.FAILED_AUTH_ACC_BLOCKED;
            httpStatus = HttpStatus.FORBIDDEN;
        } else if (e instanceof LockedException) {
            accessMsg = DataVarList.FAILED_AUTH_ACC_LOCKED;
            httpStatus = HttpStatus.FORBIDDEN;
        } else if (e instanceof AccountExpiredException) {
            accessMsg = DataVarList.FAILED_AUTH_ACC_EXPIRED;
            httpStatus = HttpStatus.FORBIDDEN;
        } else if (e instanceof CredentialsExpiredException) {
            CredentialsExpiredException cee = (CredentialsExpiredException) e;
            accessMsg = cee.getMessage().toLowerCase().contains("before")
                    ? DataVarList.FAILED_AUTH_CRED_EXPIRED
                    : DataVarList.FAILED_AUTH_FIRST_LOGIN;
            accessCode = DataVarList.AUTH_FAILED_RESET_PASSWORD;
            httpStatus = HttpStatus.FORBIDDEN;
        } else if (e instanceof BadCredentialsException) {
            accessMsg = DataVarList.FAILED_AUTH_ACC_INVALIED;
            httpStatus = HttpStatus.UNAUTHORIZED;
        }

        return buildResponse("", accessMsg, accessCode, httpStatus);
    }
}
