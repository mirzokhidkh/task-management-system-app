package com.example.taskmanagementsystemapp.service;

import com.example.taskmanagementsystemapp.entity.Category;
import com.example.taskmanagementsystemapp.entity.CategoryUser;
import com.example.taskmanagementsystemapp.entity.User;
import com.example.taskmanagementsystemapp.entity.WorkspaceUser;
import com.example.taskmanagementsystemapp.entity.enums.WorkspaceRoleName;
import com.example.taskmanagementsystemapp.payload.ApiResponse;
import com.example.taskmanagementsystemapp.payload.CategoryDTO;
import com.example.taskmanagementsystemapp.payload.CategoryUserDTO;
import com.example.taskmanagementsystemapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.taskmanagementsystemapp.utils.CommonUtils.isExistsAuthority;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryUserRepository categoryUserRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProjectUserRepository projectUserRepository;
    @Autowired
    WorkspaceUserRepository workspaceUserRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public ApiResponse addOrEditCategory(CategoryDTO categoryDTO, User user) {
        WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(categoryDTO.getWorkspaceId(), user.getId()).get();
        String roleName = workspaceUser.getWorkspaceRole().getName();
        if (!(isExistsAuthority(roleName, WorkspaceRoleName.ROLE_OWNER.name()) ||
                isExistsAuthority(roleName, WorkspaceRoleName.ROLE_ADMIN.name()))) {
            return new ApiResponse("You don't have authority", false);
        }

        if (categoryRepository.existsByNameAndProjectId(categoryDTO.getName(), categoryDTO.getProjectId())) {
            return new ApiResponse("Category with such a name and project already exists", false);
        }
        Category category = null;
        if (categoryDTO.getId() == null) {
            //ADD new category
            category = new Category(
                    categoryDTO.getName(),
                    projectRepository.findById(categoryDTO.getProjectId()).orElseThrow(() -> new ResourceNotFoundException("projetc id")),
                    categoryDTO.getAccessType(),
                    categoryDTO.isArchived(),
                    categoryDTO.getColor()
            );
        } else {
            //EDIT existing category
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDTO.getId());
            if (optionalCategory.isPresent()) {
                category = optionalCategory.get();
                category.setName(categoryDTO.getName());
                category.setProject(projectRepository.findById(categoryDTO.getProjectId()).orElseThrow(() -> new ResourceNotFoundException("projetc id")));
                category.setAccessType(categoryDTO.getAccessType());
                category.setArchived(categoryDTO.isArchived());
                category.setColor(categoryDTO.getColor());
            }
        }

        assert category != null;
        categoryRepository.save(category);

        return new ApiResponse("Successfully done", true);
    }

    @Override
    public ApiResponse deleteCategory(Long cId, User user) {
        try {
            categoryRepository.deleteById(cId);
            return new ApiResponse("Category deleted", true);
        } catch (Exception exception) {
            return new ApiResponse("Error", false);
        }
    }

    @Override
    public ApiResponse addCategoryUser(Long wId, CategoryUserDTO categoryUserDTO, User user) {
        WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(wId, user.getId()).get();
        String roleName = workspaceUser.getWorkspaceRole().getName();
        if (!(isExistsAuthority(roleName, WorkspaceRoleName.ROLE_OWNER.name()) ||
                isExistsAuthority(roleName, WorkspaceRoleName.ROLE_ADMIN.name()))) {
            return new ApiResponse("You don't have authority", false);
        }

        CategoryUser categoryUser = new CategoryUser(
                categoryUserDTO.getName(),
                categoryRepository.findById(categoryUserDTO.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("category Id")),
                userRepository.findById(categoryUserDTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("user Id")),
                categoryUserDTO.getTaskPermission()
        );
        categoryUserRepository.save(categoryUser);

        return new ApiResponse("User assign to category", true);
    }

    @Override
    public ApiResponse deleteCategoryUser(Long categoryUserId, User user) {
        try {
            categoryUserRepository.deleteById(categoryUserId);
            return new ApiResponse("Category User deleted", true);
        } catch (Exception exception) {
            return new ApiResponse("Error", false);
        }
    }

    @Override
    public ApiResponse getAllCategoriesByUserId(UUID uId, User user) {
        List<CategoryUser> categoryUsers = categoryUserRepository.findAllByUserId(uId);
        List<CategoryDTO> categoryDTOList = categoryUsers.stream().map(categoryUser -> mapCategoryUserToCategoryDTO(categoryUser.getCategory())).collect(Collectors.toList());
        return new ApiResponse("Category list", true, categoryDTOList);
    }

    private CategoryDTO mapCategoryUserToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        categoryDTO.setProjectId(category.getProject().getId());
        categoryDTO.setAccessType(category.getAccessType());
        categoryDTO.setColor(category.getColor());
        return categoryDTO;
    }
}
