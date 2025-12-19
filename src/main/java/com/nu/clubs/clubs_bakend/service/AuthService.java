package com.nu.clubs.clubs_bakend.service;

import com.nu.clubs.clubs_bakend.config.JwtUtil;
import com.nu.clubs.clubs_bakend.dto.LoginRequest;
import com.nu.clubs.clubs_bakend.dto.SignupRequest;
import com.nu.clubs.clubs_bakend.dto.UserResponse;
import com.nu.clubs.clubs_bakend.exception.BadRequestException;
import com.nu.clubs.clubs_bakend.exception.UnauthorizedException;
import com.nu.clubs.clubs_bakend.model.Role;
import com.nu.clubs.clubs_bakend.model.User;
import com.nu.clubs.clubs_bakend.model.UserType;
import com.nu.clubs.clubs_bakend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
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
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Invalid email or password"));

        if (!user.getActive()) {
            throw new UnauthorizedException("Account is inactive");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRoles().stream().map(Role::name).collect(Collectors.toList()));
        return mapToUserResponse(user, token);
    }

    public UserResponse signup(SignupRequest signupRequest) {
        if (!signupRequest.getEmail().endsWith("@nu.edu.eg")) {
            throw new BadRequestException("Email must be a university email ending with @nu.edu.eg");
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());
        Set<Role> roles = Set.of(Role.STUDENT);
        User user = new User(signupRequest.getFirstName(),
                signupRequest.getLastName(),
                signupRequest.getEmail(),
                encodedPassword,
                signupRequest.getPhoneNumber(),
                roles);

        user.setFullName(
                signupRequest.getFirstName() + " " + signupRequest.getLastName()
        );
        user.setUserType(UserType.User);

        user = userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail(), roles.stream().map(Role::name).collect(Collectors.toList()));
        return mapToUserResponse(user, token);
    }

    private UserResponse mapToUserResponse(User user, String token) {
        List<String> roles = user.getRoles().stream().map(Role::name).collect(Collectors.toList());
        return new UserResponse(user.getUserId(), user.getEmail(), roles, token);
    }
}
