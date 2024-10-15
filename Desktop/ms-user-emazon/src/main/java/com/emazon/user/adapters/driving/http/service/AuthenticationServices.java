package com.emazon.user.adapters.driving.http.service;

import com.emazon.user.adapters.driving.http.dto.request.AuthenticationRequest;
import com.emazon.user.adapters.driving.http.dto.request.AuthorizationRequest;
import com.emazon.user.adapters.driving.http.dto.request.UserRequest;
import com.emazon.user.adapters.driving.http.dto.response.AuthenticationResponse;
import com.emazon.user.adapters.driving.http.dto.response.AuthorizationResponse;
import com.emazon.user.adapters.driving.http.dto.response.RegisterResponse;

public interface AuthenticationServices {
    RegisterResponse createWarehouseAssistant(UserRequest userRequest);
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
    AuthorizationResponse authorize(AuthorizationRequest authorizationRequest);
}