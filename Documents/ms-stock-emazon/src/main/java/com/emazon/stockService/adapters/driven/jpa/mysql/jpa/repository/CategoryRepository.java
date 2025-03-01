package com.emazon.stockService.adapters.driven.jpa.mysql.jpa.repository;

import com.emazon.stockService.adapters.driven.jpa.mysql.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByName(String name);
}