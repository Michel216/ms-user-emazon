package com.emazon.user.adapters.driving.http.service;

import com.emazon.user.adapters.driving.http.dto.request.UserRequest;
import com.emazon.user.adapters.driving.http.dto.response.RegisterResponse;

public interface UserService {
    RegisterResponse createWarehouseAssistant(UserRequest userRequest);
}
