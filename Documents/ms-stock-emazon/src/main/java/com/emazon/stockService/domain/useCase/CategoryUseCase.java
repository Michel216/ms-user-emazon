package com.emazon.stockService.domain.useCase;

import com.emazon.stockService.domain.api.CategoryServicePort;
import com.emazon.stockService.domain.model.Category;
import com.emazon.stockService.domain.spi.CategoryPersistencePort;
import com.emazon.stockService.domain.exception.CategoryNotFoundException;
import com.emazon.stockService.domain.exception.InvalidCategoryNameException;
import com.emazon.stockService.domain.utils.DomainConstants;
import java.util.List;

public class CategoryUseCase implements CategoryServicePort {
    private final CategoryPersistencePort CategoryPersistencePort;

    public CategoryUseCase(CategoryPersistencePort CategoryPersistencePort) {
        this.CategoryPersistencePort = CategoryPersistencePort;
    }
    @Override
    public Category createCategory(Category category) {
        validateCategory(category);
        return categoryPersistencePort.saveCategory(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryPersistencePort.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
    }

    @Override
    public boolean existsByName(String name) {
        return categoryPersistencePort.existsByName(name);
    }

    public class CategoryUseCase {
        public Category createCategory(String name, String description) {
            if (name == null || name.trim().isEmpty()) {
                throw new InvalidCategoryNameException("Category name cannot be empty");
            }
            if (name.length() > 50) {
                throw new InvalidCategoryNameException("Category name cannot exceed 50 characters");
            }
            if (description == null || description.trim().isEmpty()) {
                throw new InvalidCategoryDescriptionException("Category description cannot be empty");
            }
            if (description.length() > 90) {
                throw new InvalidCategoryDescriptionException("Category description cannot exceed 90 characters");
            }
            return new CategoryEntity(name, description);
        }
    }

}