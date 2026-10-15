
package com.nexus.backend.service;

import com.nexus.backend.dto.request.RegisterRequest;
import com.nexus.backend.dto.response.UserResponse;
import com.nexus.backend.dto.request.LoginRequest;
import com.nexus.backend.dto.response.LoginResponse;

public interface UserService {

    UserResponse registerUser(RegisterRequest request);
    
    LoginResponse loginUser(LoginRequest request);
}
