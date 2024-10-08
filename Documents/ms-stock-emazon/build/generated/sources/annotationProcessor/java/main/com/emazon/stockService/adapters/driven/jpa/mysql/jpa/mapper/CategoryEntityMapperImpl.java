package com.emazon.stockService.adapters.driven.jpa.mysql.jpa.mapper;

import com.emazon.stockService.adapters.driven.jpa.mysql.jpa.entity.CategoryEntity;
import com.emazon.stockService.domain.model.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-24T21:48:53-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class CategoryEntityMapperImpl implements CategoryEntityMapper {

    @Override
    public CategoryEntity toEntity(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setId( category.getId() );
        categoryEntity.setName( category.getName() );
        categoryEntity.setDescription( category.getDescription() );

        return categoryEntity;
    }

    @Override
    public Category toDomainModel(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = categoryEntity.getId();
        name = categoryEntity.getName();
        description = categoryEntity.getDescription();

        Category category = new Category( id, name, description );

        return category;
    }
}
