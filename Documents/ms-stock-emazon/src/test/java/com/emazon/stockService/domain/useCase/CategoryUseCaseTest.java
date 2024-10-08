package com.emazon.stockService.domain.useCase;

import com.emazon.stockService.domain.api.CategoryServicePort;
import com.emazon.stockService.domain.exception.CategoryNotFoundException;
import com.emazon.stockService.domain.exception.InvalidCategoryNameException;
import com.emazon.stockService.domain.model.Category;
import com.emazon.stockService.domain.spi.CategoryPersistencePort;
import com.emazon.stockService.domain.utils.DomainConstants;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryUseCaseTest {

    @Mock
    private CategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    public CategoryUseCaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCategory() {
        Category category = new Category(1L, "Test", "Description");

        when(categoryPersistencePort.findByName(category.getName())).thenReturn(Optional.empty());

        categoryUseCase.createCategory(category);

        verify(categoryPersistencePort, times(1)).save(category);
    }

    @Test
    public void testCreateCategoryThrowsException() {
        Category category = new Category(1L, "Test", "Description");

        when(categoryPersistencePort.findByName(category.getName())).thenReturn(Optional.of(category));

        InvalidCategoryNameException exception = assertThrows(
                InvalidCategoryNameException.class,
                () -> categoryUseCase.createCategory(category)
        );
        assertEquals(DomainConstants.CATEGORY_ALREADY_EXISTS_MESSAGE, exception.getMessage());
    }

    @Test
    public void testGetCategory() {
        String name = "Test";
        Category category = new Category(1L, name, "Description");

        when(categoryPersistencePort.findByName(name)).thenReturn(Optional.of(category));

        Category result = categoryUseCase.getCategory(name);

        assertEquals(category, result);
    }

    @Test
    public void testGetCategoryThrowsException() {
        String name = "Test";

        when(categoryPersistencePort.findByName(name)).thenReturn(Optional.empty());

        CategoryNotFoundException exception = assertThrows(
                CategoryNotFoundException.class,
                () -> categoryUseCase.getCategory(name)
        );
        assertEquals(DomainConstants.CATEGORY_NOT_FOUND, exception.getMessage());
    }

}
