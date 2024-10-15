package com.emazon.user.adapters.driven.jpa.mysql.repository;

import com.emazon.user.adapters.driven.jpa.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    boolean existsByIdentityDocument(String identityDocument);
    boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);
}
