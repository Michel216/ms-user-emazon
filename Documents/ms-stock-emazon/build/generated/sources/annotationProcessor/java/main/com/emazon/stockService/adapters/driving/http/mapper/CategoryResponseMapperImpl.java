package com.emazon.stockService.adapters.driving.http.mapper;

import com.emazon.stockService.adapters.driving.http.dto.response.CategoryResponse;
import com.emazon.stockService.domain.model.Category;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-24T21:48:53-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class CategoryResponseMapperImpl implements CategoryResponseMapper {

    @Override
    public CategoryResponse toCategoryResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = category.getId();
        name = category.getName();
        description = category.getDescription();

        CategoryResponse categoryResponse = new CategoryResponse( id, name, description );

        return categoryResponse;
    }

    @Override
    public List<CategoryResponse> toCategoryResponseList(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryResponse> list = new ArrayList<CategoryResponse>( categories.size() );
        for ( Category category : categories ) {
            list.add( toCategoryResponse( category ) );
        }

        return list;
    }
}
