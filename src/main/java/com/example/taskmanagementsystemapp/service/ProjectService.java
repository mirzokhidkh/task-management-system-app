package com.example.taskmanagementsystemapp.service;

import com.example.taskmanagementsystemapp.entity.User;
import com.example.taskmanagementsystemapp.payload.ApiResponse;
import com.example.taskmanagementsystemapp.payload.ProjectDTO;
import com.example.taskmanagementsystemapp.payload.ProjectUserDTO;

public interface ProjectService {

    ApiResponse addProject(ProjectDTO projectDTO, User user);

    ApiResponse deleteProject(Long projectId, User user);

    ApiResponse addProjectUser(ProjectUserDTO projectUserDTO, User user);

    ApiResponse editProjectUser(Long projectUserId, ProjectUserDTO projectUserDTO, User user);

    ApiResponse deleteProjectUser(Long projectUserId, User user);


}
