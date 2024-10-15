package com.emazon.user.adapters.driving.http.mapper.request;

import com.emazon.user.adapters.driving.http.dto.request.UserRequest;
import com.emazon.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRequestMapper {
    User toUser(UserRequest userRequest);
    List<User> toUsers(List<UserRequest> userRequests);
}
