package com.project.expence_tracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.expence_tracker.dto.LoginRequestDTO;
import com.project.expence_tracker.dto.RegisterRequestDTO;
import com.project.expence_tracker.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	public final AuthService authService ;
	

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody RegisterRequestDTO request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?>  login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }
}
