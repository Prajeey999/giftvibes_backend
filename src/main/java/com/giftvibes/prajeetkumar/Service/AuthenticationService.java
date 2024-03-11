package com.giftvibes.prajeetkumar.Service;

import java.io.IOException;

import com.giftvibes.prajeetkumar.dto.request.ForgotPasswordRequest;
import com.giftvibes.prajeetkumar.dto.request.LoginRequest;
import com.giftvibes.prajeetkumar.dto.request.RegisterRequest;
import com.giftvibes.prajeetkumar.dto.response.ForgotPasswordResponse;
import com.giftvibes.prajeetkumar.dto.response.LoginResponse;
import com.giftvibes.prajeetkumar.dto.response.RegisterResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request);
}