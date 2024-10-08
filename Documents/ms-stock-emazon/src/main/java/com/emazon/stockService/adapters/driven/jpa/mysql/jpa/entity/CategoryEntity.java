package com.emazon.stockService.adapters.driven.jpa.mysql.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false, length = 90)
    private String description;


    public CategoryEntity() {}

    public CategoryEntity(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
