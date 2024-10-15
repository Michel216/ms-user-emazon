package com.emazon.user.configuration.security;

import com.emazon.user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.emazon.user.adapters.driven.jpa.mysql.repository.UserRepository;
import com.emazon.user.adapters.driving.http.dto.request.AuthenticationRequest;
import com.emazon.user.adapters.driving.http.dto.request.AuthorizationRequest;
import com.emazon.user.adapters.driving.http.dto.response.AuthenticationResponse;
import com.emazon.user.adapters.driving.http.dto.response.AuthorizationResponse;
import com.emazon.user.configuration.security.jwt.JwtService;
import com.emazon.user.configuration.security.jwt.exceptions.JwtInvalidTokenException;
import com.emazon.user.domain.exceptions.UserWithEmailNotFoundException;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
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

        UserEntity user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new UserWithEmailNotFoundException(request.getEmail()));

        Map<String, String> claims = new HashMap<>();
        claims.put("role", user.getAuthorities().stream().toList().get(0).getAuthority());
        String token = jwtService.generateToken(claims, user);

        return AuthenticationResponse.builder().token(token).build();
    }

    public AuthorizationResponse authorize(AuthorizationRequest authorizationRequest) {
        try {
            String email = jwtService.getClaim(authorizationRequest.getToken(), Claims::getSubject);
            String role = jwtService.getClaim(authorizationRequest.getToken(), claim -> claim.get("role")).toString();
            UserEntity user = userRepository
                    .findByEmail(email)
                    .orElseThrow(() -> new UserWithEmailNotFoundException(email));

            boolean authorized = jwtService.isTokenValid(authorizationRequest.getToken(), user);

            return AuthorizationResponse.builder()
                    .authorized(authorized)
                    .role(role).email(email)
                    .build();

        } catch (JwtInvalidTokenException e) {
            return AuthorizationResponse.builder()
                    .authorized(false)
                    .role(null).email(null)
                    .build();

        }
    }
}
