package com.emazon.user.adapters.driving.http.service.impl;

import com.emazon.user.adapters.driving.http.dto.request.AuthenticationRequest;
import com.emazon.user.adapters.driving.http.dto.request.AuthorizationRequest;
import com.emazon.user.adapters.driving.http.dto.response.AuthenticationResponse;
import com.emazon.user.adapters.driving.http.dto.response.AuthorizationResponse;
import com.emazon.user.adapters.driving.http.dto.request.UserRequest;
import com.emazon.user.adapters.driving.http.dto.response.RegisterResponse;
import com.emazon.user.adapters.driving.http.mapper.request.UserRequestMapper;
import com.emazon.user.adapters.driving.http.service.AuthenticationServices;
import com.emazon.user.configuration.security.AuthenticationService;
import com.emazon.user.domain.api.UserServicePort;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.utils.DomainConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthenticationServices {

    private final UserServicePort userServicePort;
    private final UserRequestMapper userRequestMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authService;

    @Override
    public RegisterResponse createWarehouseAssistant(UserRequest userRequest) {
        User user = userRequestMapper.toUser(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userServicePort.createWarehouseAssistant(user);
        return RegisterResponse.builder().status(DomainConstants.WAREHOUSE_ASSISTANT_REGISTERED_MESSAGE).build();
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        return authService.authenticate(authenticationRequest);
    }

    @Override
    public AuthorizationResponse authorize(AuthorizationRequest authorizationRequest) {
        return authService.authorize(authorizationRequest);
    }
}
