package com.emazon.user.adapters.driven.jpa.mysql.adapter;

import com.emazon.user.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.emazon.user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.emazon.user.adapters.driven.jpa.mysql.mapper.UserEntityMapper;
import com.emazon.user.adapters.driven.jpa.mysql.repository.RoleRepository;
import com.emazon.user.adapters.driven.jpa.mysql.repository.UserRepository;
import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.utils.RoleName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserJpaAdapterTest {

    @Mock
    private UserEntityMapper userEntityMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser() {
        RoleEntity roleEntity = new RoleEntity(1L, RoleName.WAREHOUSE_ASSISTANT);
        User user = new User("", "name", "lastname", "0000000000", LocalDateTime.of(1,1,1,1,1,1), "+5555555555555", "email@email.com", "password", new Role(1L, RoleName.WAREHOUSE_ASSISTANT));
        UserEntity userEntity = new UserEntity("", "name", "lastname", "0000000000", LocalDateTime.of(1,1,1,1,1,1), "+5555555555555", "email@email.com", "password", roleEntity);
        when(userEntityMapper.toEntity(user)).thenReturn(userEntity);
        when(roleRepository.findByRoleName(any())).thenReturn(Optional.of(roleEntity));
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        userJpaAdapter.createUser(user);
        verify(roleRepository).findByRoleName(any());
        verify(userRepository).save(userEntity);

    }

    @Test
    void userExistsByIdentityDocument() {
        when(userRepository.existsByIdentityDocument(any())).thenReturn(true);
        boolean exists = userJpaAdapter.userExistsByIdentityDocument("123456");
        verify(userRepository).existsByIdentityDocument("123456");
        assertTrue(exists);
    }

    @Test
    void userExistsByEmail() {
        when(userRepository.existsByEmail(any())).thenReturn(true);
        boolean exists = userJpaAdapter.userExistsByEmail("email@email.com");
        verify(userRepository).existsByEmail("email@email.com");
        assertTrue(exists);
    }

    @Test
    void getUserByEmail() {
        String email = "email@email.com";
        RoleEntity roleEntity = new RoleEntity(1L, RoleName.WAREHOUSE_ASSISTANT);
        UserEntity userEntity = new UserEntity("xd", "name", "lastname", "0000000000", LocalDateTime.of(1,1,1,1,1,1), "+5555555555555", "email@email.com", "password", roleEntity);
        User user = new User("xd", "name", "lastname", "0000000000", LocalDateTime.of(1,1,1,1,1,1), "+5555555555555", "email@email.com", "password", new Role(1L, RoleName.WAREHOUSE_ASSISTANT));
        when(userEntityMapper.toUser(userEntity)).thenReturn(user);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(userEntity));
        User returnedUser = userJpaAdapter.getUserByEmail(email);
        verify(userRepository).findByEmail("email@email.com");
        assertEquals(user.getId(), returnedUser.getId());
        assertEquals(user.getEmail(), returnedUser.getEmail());
    }
}