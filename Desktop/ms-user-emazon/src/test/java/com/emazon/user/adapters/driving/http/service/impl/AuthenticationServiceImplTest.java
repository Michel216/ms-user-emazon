package com.emazon.user.adapters.driving.http.service.impl;

import com.emazon.user.adapters.driving.http.dto.request.AuthenticationRequest;
import com.emazon.user.adapters.driving.http.dto.request.AuthorizationRequest;
import com.emazon.user.adapters.driving.http.dto.request.UserRequest;
import com.emazon.user.adapters.driving.http.dto.response.AuthenticationResponse;
import com.emazon.user.adapters.driving.http.dto.response.AuthorizationResponse;
import com.emazon.user.adapters.driving.http.dto.response.RegisterResponse;
import com.emazon.user.adapters.driving.http.mapper.request.UserRequestMapper;
import com.emazon.user.configuration.security.AuthenticationService;
import com.emazon.user.domain.api.UserServicePort;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.utils.DomainConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationServiceImplTest {

    @Mock
    private UserServicePort userServicePort;

    @Mock
    private UserRequestMapper userRequestMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationService authService;

    @InjectMocks
    private AuthServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createWarehouseAssistant() {
        UserRequest userRequest = new UserRequest("name", "lastname", "0000000000", LocalDateTime.of(1,1,1,1,1,1), "+5555555555555", "email@email.com", "password");
        User user = new User(null, "name", "lastname", "0000000000", LocalDateTime.of(1,1,1,1,1,1), "+5555555555555", "email@email.com", "encrypted", null);
        when(userRequestMapper.toUser(userRequest)).thenReturn(user);
        when(passwordEncoder.encode("password")).thenReturn("encrypted");
        doNothing().when(userServicePort).createWarehouseAssistant(user);
        RegisterResponse response = userService.createWarehouseAssistant(userRequest);
        verify(userServicePort).createWarehouseAssistant(any());
        assertNotNull(response);
        assertEquals(DomainConstants.WAREHOUSE_ASSISTANT_REGISTERED_MESSAGE, response.getStatus());
    }

    @Test
    void login() {
        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .email("email@email.com")
                .password("password")
                .build();
        AuthenticationResponse mockResponse = AuthenticationResponse.builder()
                .token("token").build();
        when(authService.authenticate(authenticationRequest)).thenReturn(mockResponse);
        AuthenticationResponse response = userService.login(authenticationRequest);
        verify(authService).authenticate(any());
        assertEquals(mockResponse.getToken(), response.getToken());
    }

    @Test
    void authorize() {
        AuthorizationRequest request = AuthorizationRequest.builder()
                .token("token").build();
        AuthorizationResponse mockResponse = AuthorizationResponse.builder()
                .authorized(true)
                .email("email@email.com")
                .role("ROLE")
                .build();
        when(authService.authorize(any())).thenReturn(mockResponse);
        AuthorizationResponse response = userService.authorize(request);
        verify(authService).authorize(any());
        assertEquals(mockResponse.isAuthorized(), response.isAuthorized());
    }
}