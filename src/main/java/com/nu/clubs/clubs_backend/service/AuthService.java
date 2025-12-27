package com.nu.clubs.clubs_backend.service;

import com.nu.clubs.clubs_backend.config.JwtUtil;
import com.nu.clubs.clubs_backend.dto.LoginRequest;
import com.nu.clubs.clubs_backend.dto.SignupRequest;
import com.nu.clubs.clubs_backend.dto.UserResponse;
import com.nu.clubs.clubs_backend.exception.BadRequestException;
import com.nu.clubs.clubs_backend.exception.UnauthorizedException;
import com.nu.clubs.clubs_backend.model.User;
import com.nu.clubs.clubs_backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UserResponse login(LoginRequest loginRequest) {
        // Stubbed login: returns a dummy token without validating credentials
        // Adjust to actual implementation when DTOs and security config are finalized
        User user = userRepository.findByEmail("").orElse(null);
        if (user == null) {
            throw new UnauthorizedException("Invalid email or password");
        }
        String token = jwtUtil.generateToken(user.getEmail(), List.of("USER"));
        return mapToUserResponse(user, token);
    }

    public UserResponse signup(SignupRequest signupRequest) {
        // Stubbed signup: returns a dummy response; implement validation when DTOs are defined
        throw new BadRequestException("Signup not implemented");
    }

    private UserResponse mapToUserResponse(User user, String token) {
        List<String> roles = user.getRoles().stream().map(r -> r.getName()).collect(Collectors.toList());
        return new UserResponse(user.getId(), user.getEmail(), roles, token);
    }
}
