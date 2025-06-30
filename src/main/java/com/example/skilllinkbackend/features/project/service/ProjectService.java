package com.example.skilllinkbackend.features.project.service;

import com.example.skilllinkbackend.config.exceptions.NotFoundException;
import com.example.skilllinkbackend.features.category.model.Category;
import com.example.skilllinkbackend.features.category.repository.ICategoryRepository;
import com.example.skilllinkbackend.features.project.dto.ProjectRegisterDTO;
import com.example.skilllinkbackend.features.project.dto.ProjectResponseDTO;
import com.example.skilllinkbackend.features.project.dto.ProjectUpdateDTO;
import com.example.skilllinkbackend.features.project.model.Project;
import com.example.skilllinkbackend.features.project.repository.IProjectRepository;
import com.example.skilllinkbackend.features.project.validations.creation.ProjectCreationValidation;
import com.example.skilllinkbackend.features.project.validations.edition.ProjectEditionValidation;
import com.example.skilllinkbackend.features.usuario.model.User;
import com.example.skilllinkbackend.features.usuario.repository.IUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements IProjectService {

    private final IProjectRepository projectRepository;
    private final List<ProjectCreationValidation> creationValidators;
    private final IUserRepository userRepository;
    private final ICategoryRepository categoryRepository;
    private final List<ProjectEditionValidation> editionValidators;

    public ProjectService(
            IProjectRepository projectRepository,
            List<ProjectCreationValidation> creationValidators,
            IUserRepository userRepository,
            ICategoryRepository categoryRepository,
            List<ProjectEditionValidation> editionValidators) {
        this.projectRepository = projectRepository;
        this.creationValidators = creationValidators;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.editionValidators = editionValidators;
    }

    @Override
    public ProjectResponseDTO createProject(ProjectRegisterDTO projectRegisterDTO) {
        creationValidators.forEach(v -> v.validate(projectRegisterDTO));
        User creator = userRepository.findByUserId(projectRegisterDTO.creatorId()).get();
        List<User> members = userRepository.findExistingEmails(projectRegisterDTO.members());
        List<Category> categories = categoryRepository.findExistingIds(projectRegisterDTO.categoriesId());

        Project project = new Project(projectRegisterDTO, creator, members, categories);
        Project projectResponse = projectRepository.save(project);

        return new ProjectResponseDTO(projectResponse);
    }

    @Override
    public Page<ProjectResponseDTO> getProjects(Pageable pagination) {
        return projectRepository.findAll(pagination).map(ProjectResponseDTO::new);
    }

    @Override
    public ProjectResponseDTO findById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proyecto no encontrado"));
        return new ProjectResponseDTO(project);
    }

    @Override
    public ProjectResponseDTO updateProject(Long id, ProjectUpdateDTO projectUpdateDTO) {
        editionValidators.forEach(v -> v.validate(projectUpdateDTO));
        Project project = projectRepository.findById(id).get();
        User creator = userRepository.findByUserId(projectUpdateDTO.creatorId()).get();
        List<User> members = userRepository.findExistingEmails(projectUpdateDTO.members());
        List<Category> categories = categoryRepository.findExistingIds(projectUpdateDTO.categoriesId());

        project.update(projectUpdateDTO, creator, members, categories);
        return new ProjectResponseDTO(project);
    }

    @Override
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Proyecto no encontrado"));
        project.deactive();
    }
}
