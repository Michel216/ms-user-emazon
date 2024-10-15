package com.emazon.user.adapters.driven.jpa.mysql.mapper;

import com.emazon.user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.emazon.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {
    UserEntity toEntity(User user);
    User toUser(UserEntity userEntity);
    List<User> toUsers(List<UserEntity> userEntities);
}
