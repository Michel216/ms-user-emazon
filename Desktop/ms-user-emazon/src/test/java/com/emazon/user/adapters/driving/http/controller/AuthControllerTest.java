package com.emazon.user.adapters.driving.http.controller;

import com.emazon.user.adapters.driving.http.dto.request.UserRequest;
import com.emazon.user.adapters.driving.http.dto.response.RegisterResponse;
import com.emazon.user.adapters.driving.http.service.UserService;
import com.emazon.user.adapters.driving.http.utils.JsonParser;
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
    private UserService userService;


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
        when(userService.createWarehouseAssistant(any())).thenReturn(response);
        this.mockMvc.perform(post("/auth/register/warehouse-assistant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(userRequest)))
                .andExpect(content().json(JsonParser.toJson(response)))
                .andExpect(status().isCreated());
        verify(userService).createWarehouseAssistant(any());

    }
}