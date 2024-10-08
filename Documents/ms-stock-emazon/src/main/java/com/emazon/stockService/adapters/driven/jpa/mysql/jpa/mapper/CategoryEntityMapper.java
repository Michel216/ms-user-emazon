package com.emazon.stockService.adapters.driven.jpa.mysql.jpa.mapper;

import com.emazon.stockService.adapters.driven.jpa.mysql.jpa.entity.CategoryEntity;
import com.emazon.stockService.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    CategoryEntity toEntity(Category category);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Category toDomain(CategoryEntity categoryEntity);
}
