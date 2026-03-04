package com.project.expence_tracker.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.expence_tracker.config.JwtService;
import com.project.expence_tracker.dto.AuthResponse;
import com.project.expence_tracker.dto.LoginRequestDTO;
import com.project.expence_tracker.dto.RegisterRequestDTO;
import com.project.expence_tracker.entity.UserInfo;
import com.project.expence_tracker.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserInfoRepository userInfoRepository;
    private final JwtService jwtService;

    public ResponseEntity<?> signup(RegisterRequestDTO request) {

        if (userInfoRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Email already exists");
        }

        if (userInfoRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Phone number already exists");
        }

        UserInfo user = UserInfo.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .build();

        userInfoRepository.save(user);

        String accessToken = jwtService.generateAccessToken(user.getEmail());
        String refreshToken = jwtService.generateRefreshToken(user.getEmail());

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    public ResponseEntity<?> login(LoginRequestDTO request) {

        String identifier = request.getEmailOrPhno();

        UserInfo user = userInfoRepository
                .findByEmailOrPhoneNumber(identifier, identifier)
                .orElse(null);

        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }

        String accessToken = jwtService.generateAccessToken(user.getEmail());
        String refreshToken = jwtService.generateRefreshToken(user.getEmail());

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }
}