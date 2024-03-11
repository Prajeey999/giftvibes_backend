package com.giftvibes.prajeetkumar.Service.impl;

import static com.giftvibes.prajeetkumar.Enumerated.TokenType.BEARER;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giftvibes.prajeetkumar.Enumerated.Role;
import com.giftvibes.prajeetkumar.Models.Token;
import com.giftvibes.prajeetkumar.Models.User;
import com.giftvibes.prajeetkumar.Repository.TokenRepository;
import com.giftvibes.prajeetkumar.Repository.UserRepository;
import com.giftvibes.prajeetkumar.Service.AuthenticationService;
import com.giftvibes.prajeetkumar.dto.request.ForgotPasswordRequest;
import com.giftvibes.prajeetkumar.dto.request.LoginRequest;
import com.giftvibes.prajeetkumar.dto.request.RegisterRequest;
import com.giftvibes.prajeetkumar.dto.response.ForgotPasswordResponse;
import com.giftvibes.prajeetkumar.dto.response.LoginResponse;
import com.giftvibes.prajeetkumar.dto.response.RegisterResponse;
import com.giftvibes.prajeetkumar.utils.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class AuthenticationServiceImpl implements AuthenticationService {

        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;
        private final JwtUtil jwtUtil;
        private final UserRepository userRepository;
        private final TokenRepository tokenRepository;

        @Override
        public RegisterResponse register(RegisterRequest request) {
                var user = User.builder()
                                .name(request.getName())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .role(Role.valueOf(request.getRole().toUpperCase()))
                                .build();
                userRepository.save(user);
                return RegisterResponse.builder()
                                .message("User registered successfully")
                                .build();
        }

        @Override
        public LoginResponse login(LoginRequest request) {
                authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                                                request.getPassword()));
                var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
                Map<String, Object> claims = new HashMap<>();
                claims.put("role", user.getRole().toString());
                var accessToken = jwtUtil.generateToken(claims, user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                return LoginResponse.builder()
                                .message("Logged in successfully.")
                                .accessToken(accessToken)
                                .build();
        }

        @Override
        public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request){
                var user=userRepository.findByEmail(request.getEmail()).orElseThrow(()->new UsernameNotFoundException("User not found with email "+request.getEmail()));
                if(passwordEncoder.matches(request.getEmail(), user.getEmail())){
                return ForgotPasswordResponse.builder().message("Password incorrect.").build();
                }
                if(!request.getNewPassword().equals(request.getConfirmPassword())){
                return ForgotPasswordResponse.builder().message("Password Mismatch").build();
                }
                user.setPassword(passwordEncoder.encode(request.getNewPassword()));
                return ForgotPasswordResponse.builder().message("Password updated successfully").build();
        }

        private void saveUserToken(User user, String accessToken) {
                var token = Token.builder()
                                .user(user)
                                .token(accessToken)
                                .tokenType(BEARER)
                                .expired(false)
                                .revoked(false)
                                .build();
                tokenRepository.save(token);
        }

        private void revokeAllUserTokens(User user) {
                var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
                if (validUserTokens.isEmpty())
                        return;
                validUserTokens.forEach(token -> {
                        token.setExpired(true);
                        token.setRevoked(true);
                });
                tokenRepository.saveAll(validUserTokens);
        }

        @Override
        public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
                final String authHeader = request.getHeader(AUTHORIZATION);
                final String refreshToken;
                final String userEmail;
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                        return;
                }
                refreshToken = authHeader.substring(7);
                userEmail = jwtUtil.extractUsername(refreshToken);
                if (userEmail != null) {
                        var user = this.userRepository.findByEmail(userEmail).orElseThrow();
                        if (jwtUtil.isTokenValid(refreshToken, user)) {
                                var accessToken = jwtUtil.generateToken(user);
                                revokeAllUserTokens(user);
                                saveUserToken(user, accessToken);
                                var authResponse = LoginResponse.builder()
                                                .message("New access token generated successfully.")
                                                .accessToken(accessToken)
                                                .build();
                                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
                        }
                }
        }

}