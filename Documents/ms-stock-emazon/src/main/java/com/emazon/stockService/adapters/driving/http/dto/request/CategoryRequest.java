package com.emazon.stockService.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryRequest {
    @NotBlank(message = "Category name cannot be empty")
    @Size(max = 50, message = "Category name cannot exceed 50 characters")
    private String name;

    @NotBlank(message = "Category description cannot be empty")
    @Size(max = 90, message = "Category description cannot exceed 90 characters")
    private String description;
}
