package com.emazon.stockService.adapters.driving.http.mapper;

import com.emazon.stockService.adapters.driving.http.dto.request.CategoryRequest;
import com.emazon.stockService.domain.model.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-24T21:48:52-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class CategoryRequestMapperImpl implements CategoryRequestMapper {

    @Override
    public Category toCategory(CategoryRequest categoryRequest) {
        if ( categoryRequest == null ) {
            return null;
        }

        String name = null;
        String description = null;

        name = categoryRequest.getName();
        description = categoryRequest.getDescription();

        Long id = null;

        Category category = new Category( id, name, description );

        return category;
    }
}
