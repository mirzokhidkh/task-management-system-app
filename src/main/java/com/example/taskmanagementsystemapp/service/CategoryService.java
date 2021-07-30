package com.example.taskmanagementsystemapp.service;

import com.example.taskmanagementsystemapp.entity.User;
import com.example.taskmanagementsystemapp.payload.ApiResponse;
import com.example.taskmanagementsystemapp.payload.CategoryDTO;
import com.example.taskmanagementsystemapp.payload.CategoryUserDTO;

import java.util.UUID;

public interface CategoryService {
    ApiResponse addOrEditCategory(CategoryDTO categoryDTO, User user);

    ApiResponse deleteCategory(Long cId, User user);

    ApiResponse addCategoryUser(Long wId, CategoryUserDTO categoryUserDTO, User user);

    ApiResponse deleteCategoryUser(Long categoryUserId, User user);

    ApiResponse getAllCategoriesByUserId(UUID uId, User user);
}
