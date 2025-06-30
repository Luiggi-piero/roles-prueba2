package com.example.skilllinkbackend.features.project.validations.creation;

import com.example.skilllinkbackend.config.exceptions.NotFoundException;
import com.example.skilllinkbackend.features.category.model.Category;
import com.example.skilllinkbackend.features.category.repository.ICategoryRepository;
import com.example.skilllinkbackend.features.project.dto.ProjectRegisterDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActiveCategoriesValidator implements ProjectCreationValidation {

    private final ICategoryRepository categoryRepository;

    public ActiveCategoriesValidator(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void validate(ProjectRegisterDTO projectRegisterDTO) {
        List<Category> existingCategories = categoryRepository.findExistingIds(projectRegisterDTO.categoriesId());
        boolean allExist = existingCategories.size() == projectRegisterDTO.categoriesId().size();
        if (!allExist) {
            throw new NotFoundException("Al menos una categoría no existe");
        }
    }
}
