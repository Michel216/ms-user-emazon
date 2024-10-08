package com.emazon.stockService.domain.api;

import com.emazon.stockService.domain.model.Category;
import java.util.List;

public interface CategoryServicePort {
    void createCategory(Category category);
    void deleteCategory(String name);
    Category getCategory(String name);
    List<Category> getAllCategoriesAscending(int page, int size);
    List<Category> getAllCategoriesDescending(int page, int size);
}