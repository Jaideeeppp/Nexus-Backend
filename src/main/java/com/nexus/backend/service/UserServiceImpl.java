
package com.nexus.backend.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexus.backend.dto.request.RegisterRequest;
import com.nexus.backend.dto.response.UserResponse;
import com.nexus.backend.entity.User;
import com.nexus.backend.repository.UserRepository;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;


import com.nexus.backend.dto.request.LoginRequest;
import com.nexus.backend.dto.response.LoginResponse;
import com.nexus.backend.entity.User;
import com.nexus.backend.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

import com.nexus.backend.exception.InvalidCredentialsException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private JwtService jwtService;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    @Override
    @Transactional
    public UserResponse registerUser(RegisterRequest request) {

        String email = request.getEmail()
                .trim()
                .toLowerCase(Locale.ROOT);


if (userRepository.existsByEmail(email)) {
    String message = messageSource.getMessage(
            "user.email.exists",
            null,
            LocaleContextHolder.getLocale()
    );

    throw new IllegalArgumentException(message);
}


        User user = new User();
        user.setFullName(request.getFullName().trim());
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt()
        );
    }
    

@Override
public LoginResponse loginUser(LoginRequest request) {

    String email = request.getEmail().trim().toLowerCase(Locale.ROOT);

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException(
                    messageSource.getMessage(
                            "user.password.invalid",
                            null,
                            LocaleContextHolder.getLocale()
                    )
            ));

    if (!passwordEncoder.matches(
            request.getPassword(),
            user.getPassword())) {

        throw new IllegalArgumentException(
                messageSource.getMessage(
                        "user.password.invalid",
                        null,
                        LocaleContextHolder.getLocale()
                )
        );
    }

    String token = jwtService.generateToken(user);

    return new LoginResponse(
            token,
            user.getId(),
            user.getFullName(),
            user.getEmail()
    );
}

}
