package com.example.skilllinkbackend.features.category.service;

import com.example.skilllinkbackend.features.category.dto.CategoryRegisterDTO;
import com.example.skilllinkbackend.features.category.dto.CategoryResponseDTO;
import com.example.skilllinkbackend.features.category.dto.CategoryUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface ICategoryService {
    CategoryResponseDTO createCategory(CategoryRegisterDTO categoryDTO);

    Page<CategoryResponseDTO> getCategories(Pageable pagination);

    CategoryResponseDTO findById(Long id);

    CategoryResponseDTO updateCategory(Long id, CategoryUpdateDTO categoryDTO);

    void deleteCategory(Long id);
}
