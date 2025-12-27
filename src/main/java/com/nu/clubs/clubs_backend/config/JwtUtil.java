package com.nu.clubs.clubs_backend.config;

import org.springframework.stereotype.Component;
import java.util.Base64;
import java.util.List;

@Component
public class JwtUtil {
    public String generateToken(String email, List<String> roles) {
        String payload = email + "|" + String.join(",", roles) + "|" + System.currentTimeMillis();
        return Base64.getEncoder().encodeToString(payload.getBytes());
    }
}
