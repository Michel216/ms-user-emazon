package com.emazon.user.configuration.security;

import com.emazon.user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.emazon.user.adapters.driven.jpa.mysql.repository.UserRepository;
import com.emazon.user.adapters.driving.http.dto.request.AuthenticationRequest;
import com.emazon.user.adapters.driving.http.dto.response.AuthenticationResponse;
import com.emazon.user.configuration.security.jwt.JwtService;
import com.emazon.user.domain.exceptions.UserWithEmailNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserEntity user = userRepository.findById(request.getEmail())
                .orElseThrow(() -> new UserWithEmailNotFoundException(request.getEmail()));

        Map<String, String> claims = new HashMap<>();
        String token = jwtService.generateToken(claims, user);
        claims.put("role", user.getAuthorities().stream().toList().get(0).getAuthority());
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
