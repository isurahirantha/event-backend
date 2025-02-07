package com.codeloon.ems.service;

import com.codeloon.ems.configuration.authentication.JwtTokenProvider;
import com.codeloon.ems.model.AuthResponse;
import com.codeloon.ems.dto.LoginDto;
import com.codeloon.ems.util.DataVarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<AuthResponse> login(LoginDto loginDto) {
        String token = "";
        try {
            // 01 - AuthenticationManager is used to authenticate the user
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()
            ));

            /* 02 - SecurityContextHolder is used to allows the rest of the application to know
            that the user is authenticated and can use user data from Authentication object */
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 03 - Generate the token based on username and secret key
            token = jwtTokenProvider.generateToken(authentication);
        } catch (AuthenticationException e) {
            // 04 - Return the token to controller
            return handleAuthenticationException(e);
        }
        // 04 - Return the token to controller
        return this.buildResponse(token, DataVarList.SUCCESS_AUTH, DataVarList.AUTH_SUCCESS, HttpStatus.OK);
    }


    private ResponseEntity<AuthResponse> buildResponse(String token, String authMsg, String authStatus, HttpStatus httpStatus) {
        AuthResponse authResponseDto = AuthResponse.builder()
                .accessCode(authStatus)
                .accessToken(token)
                .accessMsg(authMsg)
                .build();
        return new ResponseEntity<>(authResponseDto, httpStatus);
    }

    private ResponseEntity<AuthResponse> handleAuthenticationException(AuthenticationException e) {
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
            accessMsg = !cee.getMessage().toLowerCase().contains("before")
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
