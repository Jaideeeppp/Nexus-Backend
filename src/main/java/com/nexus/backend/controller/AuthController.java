
package com.nexus.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nexus.backend.dto.request.RegisterRequest;
import com.nexus.backend.dto.response.ApiResponse;
import com.nexus.backend.dto.response.UserResponse;
import com.nexus.backend.service.UserService;

import com.nexus.backend.dto.request.LoginRequest;
import com.nexus.backend.dto.response.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(
            @Valid @RequestBody RegisterRequest request) {

        UserResponse user = userService.registerUser(request);

        ApiResponse<UserResponse> response = new ApiResponse<>(
                201,
                "Your account has been created successfully.",
                user
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponse>> login(
	        @Valid @RequestBody LoginRequest request) {
	
	    LoginResponse loginResponse = userService.loginUser(request);
	
	    ApiResponse<LoginResponse> response = new ApiResponse<>(
	            200,
	            "You have logged in successfully.",
	            loginResponse
	    );
	
	    return ResponseEntity.ok(response);
	}

}
