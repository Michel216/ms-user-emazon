package com.emazon.stockService.adapters.driving.http.controller;

import com.emazon.stockService.adapters.driving.http.dto.request.CategoryRequest;
import com.emazon.stockService.adapters.driving.http.dto.response.CategoryResponse;
import com.emazon.stockService.adapters.driving.http.mapper.CategoryRequestMapper;
import com.emazon.stockService.adapters.driving.http.mapper.CategoryResponseMapper;
import com.emazon.stockService.domain.api.CategoryServicePort;
import com.emazon.stockService.domain.model.Category;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
public class CategoryRestControllerTest {

    @Mock
    private CategoryServicePort categoryServicePort;

    @Mock
    private CategoryRequestMapper categoryRequestMapper;

    @Mock
    private CategoryResponseMapper categoryResponseMapper;

    @InjectMocks
    private CategoryRestController categoryRestController;

    public CategoryRestControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCategory() {
        CategoryRequest request = new CategoryRequest("Test", "Description");
        Category category = new Category(1L, "Test", "Description");
        CategoryResponse response = new CategoryResponse(1L, "Test", "Description");

        when(categoryRequestMapper.toCategory(request)).thenReturn(category);
        when(categoryResponseMapper.toCategoryResponse(category)).thenReturn(response);
        doNothing().when(categoryServicePort).createCategory(any(Category.class));

        ResponseEntity<CategoryResponse> result = categoryRestController.createCategory(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(categoryServicePort, times(1)).createCategory(any(Category.class));
    }

    @Test
    public void testGetCategory() {
        String name = "Test";
        Category category = new Category(1L, name, "Description");
        CategoryResponse response = new CategoryResponse(1L, name, "Description");

        when(categoryServicePort.getCategory(name)).thenReturn(category);
        when(categoryResponseMapper.toCategoryResponse(category)).thenReturn(response);

        ResponseEntity<CategoryResponse> result = categoryRestController.getCategory(name);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

}
