package com.emazon.user.adapters.driving.http.service.impl;

import com.emazon.user.adapters.driving.http.dto.request.UserRequest;
import com.emazon.user.adapters.driving.http.dto.response.RegisterResponse;
import com.emazon.user.adapters.driving.http.mapper.request.UserRequestMapper;
import com.emazon.user.adapters.driving.http.service.UserService;
import com.emazon.user.domain.api.UserServicePort;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.utils.DomainConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserServicePort userServicePort;
    private final UserRequestMapper userRequestMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse createWarehouseAssistant(UserRequest userRequest) {
        User user = userRequestMapper.toUser(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userServicePort.createWarehouseAssistant(user);
        return RegisterResponse.builder().status(DomainConstants.WAREHOUSE_ASSISTANT_REGISTERED_MESSAGE).build();
    }
}
