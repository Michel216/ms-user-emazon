package com.emazon.user.adapters.driving.http.service.impl;

import com.emazon.user.adapters.driving.http.dto.request.UserRequest;
import com.emazon.user.adapters.driving.http.dto.response.RegisterResponse;
import com.emazon.user.adapters.driving.http.mapper.request.UserRequestMapper;
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

class UserServiceImplTest {

    @Mock
    private UserServicePort userServicePort;

    @Mock
    private UserRequestMapper userRequestMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createWarehouseAssistant() {
        UserRequest userRequest = new UserRequest("name", "lastname", "0000000000", LocalDateTime.of(1,1,1,1,1,1), "+5555555555555", "email@email.com", "password");
        User user = new User(null, "name", "lastname", "0000000000", LocalDateTime.of(1,1,1,1,1,1), "+5555555555555", "email@email.com", "xdddddddddd", null);
        when(userRequestMapper.toUser(userRequest)).thenReturn(user);
        when(passwordEncoder.encode("password")).thenReturn("xdddddddddd");
        doNothing().when(userServicePort).createWarehouseAssistant(user);
        RegisterResponse response = userService.createWarehouseAssistant(userRequest);
        verify(userServicePort).createWarehouseAssistant(any());
        assertNotNull(response);
        assertEquals(DomainConstants.WAREHOUSE_ASSISTANT_REGISTERED_MESSAGE, response.getStatus());
    }
}