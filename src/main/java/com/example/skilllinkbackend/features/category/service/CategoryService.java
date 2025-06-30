package com.example.skilllinkbackend.features.category.service;

import com.example.skilllinkbackend.config.exceptions.NotFoundException;
import com.example.skilllinkbackend.features.category.dto.CategoryRegisterDTO;
import com.example.skilllinkbackend.features.category.dto.CategoryResponseDTO;
import com.example.skilllinkbackend.features.category.dto.CategoryUpdateDTO;
import com.example.skilllinkbackend.features.category.model.Category;
import com.example.skilllinkbackend.features.category.repository.ICategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryRegisterDTO categoryDTO) {
        Category category = new Category(categoryDTO);
        return new CategoryResponseDTO(categoryRepository.save(category));
    }

    @Override
    public Page<CategoryResponseDTO> getCategories(Pageable pagination) {
        return categoryRepository.findAll(pagination).map(CategoryResponseDTO::new);
    }

    @Override
    public CategoryResponseDTO findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));
        return new CategoryResponseDTO(category);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryUpdateDTO categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));
        category.update(categoryDTO);
        return new CategoryResponseDTO(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada"));
        category.deactive();
    }
}
