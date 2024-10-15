package com.emazon.user.adapters.driven.jpa.mysql.repository;

import com.emazon.user.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.emazon.user.domain.utils.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleName(RoleName roleName);
}
