package com.example.taskmanagementsystemapp.controller;

import com.example.taskmanagementsystemapp.entity.User;
import com.example.taskmanagementsystemapp.payload.*;
import com.example.taskmanagementsystemapp.security.CurrentUser;
import com.example.taskmanagementsystemapp.service.ProjectService;
import com.example.taskmanagementsystemapp.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/workspace")
public class ProjectController {
    @Autowired
    ProjectService projectService;


    @PostMapping
    public HttpEntity<?> addProject(@Valid @RequestBody ProjectDTO projectDTO, @CurrentUser User user) {
        ApiResponse apiResponse = projectService.addProject(projectDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping
    public HttpEntity<?> deleteProject(@RequestParam Long projectId, @CurrentUser User user) {
        ApiResponse apiResponse = projectService.deleteProject(projectId, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> addProjectUser(@Valid @RequestBody ProjectUserDTO projectUserDTO, @CurrentUser User user) {
        ApiResponse apiResponse = projectService.addProjectUser(projectUserDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping
    public HttpEntity<?> editProjectUser(@RequestParam Long projectUserId, @Valid @RequestBody ProjectUserDTO projectUserDTO, @CurrentUser User user) {
        ApiResponse apiResponse = projectService.editProjectUser(projectUserId, projectUserDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping
    public HttpEntity<?> deleteProjectUser(@RequestParam Long projectUserId, @CurrentUser User user) {
        ApiResponse apiResponse = projectService.deleteProjectUser(projectUserId, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }

}
