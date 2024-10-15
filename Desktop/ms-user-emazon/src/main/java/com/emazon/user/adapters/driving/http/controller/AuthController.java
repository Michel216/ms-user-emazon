package com.emazon.user.adapters.driving.http.controller;

import com.emazon.user.adapters.driving.http.dto.request.AuthenticationRequest;
import com.emazon.user.adapters.driving.http.dto.request.AuthorizationRequest;
import com.emazon.user.adapters.driving.http.dto.request.UserRequest;
import com.emazon.user.adapters.driving.http.dto.response.AuthenticationResponse;
import com.emazon.user.adapters.driving.http.dto.response.AuthorizationResponse;
import com.emazon.user.adapters.driving.http.dto.response.RegisterResponse;
import com.emazon.user.adapters.driving.http.service.AuthenticationServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationServices authenticationServices;

    @Operation(summary = "Register a new warehouse assistant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Assistant has been registered"),
            @ApiResponse(responseCode = "400", description = "Some of the field doesn't pass validations"),
            @ApiResponse(responseCode = "409", description = "User with that email already exists"),
            @ApiResponse(responseCode = "409", description = "User with that identity document already exists"),
            @ApiResponse(responseCode = "409", description = "User is under aged"),
    })
    @PostMapping("/register/warehouse-assistant")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<RegisterResponse> registerWarehouseAssistant(@RequestBody @Valid UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authenticationServices.createWarehouseAssistant(userRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authenticationRequest){
        return ResponseEntity.accepted().body(authenticationServices.login(authenticationRequest));
    }

    @PostMapping("/authorize")
    public ResponseEntity<AuthorizationResponse> authorize(@RequestBody @Valid AuthorizationRequest authorizationRequest){
        return ResponseEntity.accepted().body(authenticationServices.authorize(authorizationRequest));
    }
}
