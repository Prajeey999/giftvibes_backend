package com.giftvibes.prajeetkumar.Controller;


import static com.giftvibes.prajeetkumar.utils.MyConstant.AUTH;
import static com.giftvibes.prajeetkumar.utils.MyConstant.FORGOT_PASSWORD;
import static com.giftvibes.prajeetkumar.utils.MyConstant.LOGIN;
import static com.giftvibes.prajeetkumar.utils.MyConstant.REFRESR_TOKEN;
import static com.giftvibes.prajeetkumar.utils.MyConstant.REGISTER;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giftvibes.prajeetkumar.Service.AuthenticationService;
import com.giftvibes.prajeetkumar.dto.request.ForgotPasswordRequest;
import com.giftvibes.prajeetkumar.dto.request.LoginRequest;
import com.giftvibes.prajeetkumar.dto.request.RegisterRequest;
import com.giftvibes.prajeetkumar.dto.response.ForgotPasswordResponse;
import com.giftvibes.prajeetkumar.dto.response.LoginResponse;
import com.giftvibes.prajeetkumar.dto.response.RegisterResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@Tag(name = "Authentication")
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = new RegisterResponse();
        try {
            response = authService.register(request);
            return new ResponseEntity<>(response, ACCEPTED);
        } catch (Exception e) {
            response.setMessage("Registration failed due to an unexpected error.");
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }

    @PostMapping(LOGIN)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = new LoginResponse();
        try {
            response = authService.login(request);
            return new ResponseEntity<>(response, ACCEPTED);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
            response.setMessage("Login failed!");
            response.setAccessToken("");
            return new ResponseEntity<>(response, EXPECTATION_FAILED);
        }
    }

    @PatchMapping(FORGOT_PASSWORD)
    public ResponseEntity<ForgotPasswordResponse> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        try {
            ForgotPasswordResponse response = authService.forgotPassword(request);
            return ResponseEntity.ok(response);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(ForgotPasswordResponse.builder().message("User not found").build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                                .body(ForgotPasswordResponse.builder().message("Failed to update password").build());
        }
    }

    @PostMapping(REFRESR_TOKEN)
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }
}