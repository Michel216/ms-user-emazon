package com.emazon.user.adapters.driven.jpa.mysql.adapter;

import com.emazon.user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.emazon.user.adapters.driven.jpa.mysql.mapper.UserEntityMapper;
import com.emazon.user.adapters.driven.jpa.mysql.repository.RoleRepository;
import com.emazon.user.adapters.driven.jpa.mysql.repository.UserRepository;
import com.emazon.user.domain.exceptions.UserWithEmailNotFoundException;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.spi.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserJpaAdapter implements UserPersistencePort {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private  final RoleRepository roleRepository;

    @Override
    public void createUser(User user) {
        UserEntity userEntity = userEntityMapper.toEntity(user);
        log.warn("Creating user: {}", user.getRole().getRoleName());
        userEntity.setRole(
                roleRepository.findByRoleName(userEntity.getRole().getRoleName()).orElse(null)
        );
        userRepository.save(userEntity);

    }

    @Override
    public boolean userExistsByIdentityDocument(String identityDocument) {
        return userRepository.existsByIdentityDocument(identityDocument);
    }

    @Override
    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User getUserByEmail(String email){
        return userEntityMapper.toUser(
                userRepository.findByEmail(email).orElseThrow(() -> new UserWithEmailNotFoundException(email))
        );
    }
}
