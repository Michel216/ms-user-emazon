package com.emazon.user.adapters.driving.http.controller;

import com.emazon.user.adapters.driving.http.dto.request.AuthenticationRequest;
import com.emazon.user.adapters.driving.http.dto.request.AuthorizationRequest;
import com.emazon.user.adapters.driving.http.dto.request.UserRequest;
import com.emazon.user.adapters.driving.http.dto.response.AuthenticationResponse;
import com.emazon.user.adapters.driving.http.dto.response.AuthorizationResponse;
import com.emazon.user.adapters.driving.http.dto.response.RegisterResponse;
import com.emazon.user.adapters.driving.http.service.AuthenticationServices;
import com.emazon.user.adapters.driving.http.utils.JsonConversionException;
import com.emazon.user.configuration.security.jwt.JwtService;
import com.emazon.user.domain.utils.DomainConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc
class AuthControllerTest {

    private MockMvc mockMvc;

    private final WebApplicationContext webApplicationContext;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private AuthenticationServices authenticationServices;


    @Autowired
    public AuthControllerTest(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void registerWarehouseAssistantIsOk() throws Exception {
        UserRequest userRequest = new UserRequest("name", "lastname", "0000000000", LocalDateTime.of(1, 1, 1, 1, 1, 1), "+555555555555", "email@email.com", "password");
        RegisterResponse response = RegisterResponse.builder().status(DomainConstants.WAREHOUSE_ASSISTANT_REGISTERED_MESSAGE).build();
        when(authenticationServices.createWarehouseAssistant(any())).thenReturn(response);
        this.mockMvc.perform(post("/auth/register/warehouse-assistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConversionException.toJson(userRequest)))
                .andExpect(content().json(JsonConversionException.toJson(response)))
                .andExpect(status().isCreated());
        verify(authenticationServices).createWarehouseAssistant(any());

    }

    @Test
    void login() throws Exception {
        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .email("email@email.com")
                .password("password")
                .build();
        AuthenticationResponse mockResponse = AuthenticationResponse.builder()
                .token("token").build();
        when(authenticationServices.login(authenticationRequest)).thenReturn(mockResponse);
        this.mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConversionException.toJson(authenticationRequest)))
                .andExpect(status().isAccepted());
        verify(authenticationServices).login(any());
    }

    @Test
    void authorize() throws Exception {
        AuthorizationRequest request = AuthorizationRequest.builder()
                .token("token").build();
        AuthorizationResponse mockResponse = AuthorizationResponse.builder()
                .authorized(true)
                .email("email@email.com")
                .role("ROLE")
                .build();
        when(authenticationServices.authorize(request)).thenReturn(mockResponse);
        this.mockMvc.perform(post("/auth/authorize")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConversionException.toJson(request)))
                .andExpect(content().json(JsonConversionException.toJson(mockResponse)))
                .andExpect(status().isAccepted());
        verify(authenticationServices).authorize(request);
    }
}