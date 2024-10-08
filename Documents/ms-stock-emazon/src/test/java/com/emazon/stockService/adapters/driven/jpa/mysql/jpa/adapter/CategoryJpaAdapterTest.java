package com.emazon.stockService.adapters.driven.jpa.mysql.jpa.adapter;

import com.emazon.stockService.adapters.driven.jpa.mysql.jpa.entity.CategoryEntity;
import com.emazon.stockService.adapters.driven.jpa.mysql.jpa.mapper.CategoryEntityMapper;
import com.emazon.stockService.adapters.driven.jpa.mysql.jpa.repository.CategoryRepository;
import com.emazon.stockService.domain.model.Category;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryJpaAdapterTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryJpaAdapter categoryJpaAdapter;

    public CategoryJpaAdapterTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        Category category = new Category(1L, "Test", "Description");
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Test", "Description");

        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.empty());
        when(categoryEntityMapper.toEntity(category)).thenReturn(categoryEntity);

        categoryJpaAdapter.save(category);

        verify(categoryRepository, times(1)).save(categoryEntity);
    }

    @Test
    public void testSaveThrowsException() {
        Category category = new Category(1L, "Test", "Description");

        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.of(new CategoryEntity()));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> categoryJpaAdapter.save(category)
        );
        assertEquals("Category already exists", exception.getMessage());
    }

    @Test
    public void testFindByName() {
        String name = "Test";
        CategoryEntity categoryEntity = new CategoryEntity(1L, name, "Description");
        Category category = new Category(1L, name, "Description");

        when(categoryRepository.findByName(name)).thenReturn(Optional.of(categoryEntity));
        when(categoryEntityMapper.toDomainModel(categoryEntity)).thenReturn(category);

        Optional<Category> result = categoryJpaAdapter.findByName(name);

        assertTrue(result.isPresent());
        assertEquals(category, result.get());
    }

}
