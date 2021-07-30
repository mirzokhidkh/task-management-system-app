package com.example.taskmanagementsystemapp.service;

import com.example.taskmanagementsystemapp.entity.*;
import com.example.taskmanagementsystemapp.entity.enums.WorkspaceRoleName;
import com.example.taskmanagementsystemapp.payload.ApiResponse;
import com.example.taskmanagementsystemapp.payload.ProjectDTO;
import com.example.taskmanagementsystemapp.payload.ProjectUserDTO;
import com.example.taskmanagementsystemapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.taskmanagementsystemapp.utils.CommonUtils.isExistsAuthority;


@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProjectUserRepository projectUserRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SpaceRepository spaceRepository;
    @Autowired
    SpaceUserRepository spaceUserRepository;
    @Autowired
    WorkspaceUserRepository workspaceUserRepository;


    @Override
    public ApiResponse addProject(ProjectDTO projectDTO, User user) {
        if (projectRepository.existsByNameAndSpaceId(projectDTO.getName(), projectDTO.getSpaceId())) {
            return new ApiResponse("Project with such a name and space already exists", false);
        }
        if (spaceUserRepository.existsBySpaceIdAndMemberId(projectDTO.getSpaceId(), user.getId())) {
            return new ApiResponse("User is not belong to this project", false);
        }

        WorkspaceUser workspaceUser = workspaceUserRepository.findByUserId(user.getId());
        String roleName = workspaceUser.getWorkspaceRole().getName();
        if (!(isExistsAuthority(roleName, WorkspaceRoleName.ROLE_OWNER.name()) ||
                isExistsAuthority(roleName, WorkspaceRoleName.ROLE_ADMIN.name()))) {
            return new ApiResponse("You don't have authority", false);
        }

        if (projectRepository.existsByNameAndSpaceId(projectDTO.getName(), projectDTO.getSpaceId())) {
            return new ApiResponse("Project with such a name and space id already exists ", false);
        }

        Project project = new Project(
                projectDTO.getName(),
                spaceRepository.findById(projectDTO.getSpaceId()).orElseThrow(() -> new ResourceNotFoundException("space ID")),
                projectDTO.getAccessType(),
                projectDTO.isArchived(),
                projectDTO.getColor()
        );

        projectRepository.save(project);

        return new ApiResponse("Project saved", true);
    }

    @Override
    public ApiResponse editProject(Long projectId, ProjectDTO projectDTO, User user) {
        if (projectRepository.existsByNameAndSpaceIdAndIdNot(projectDTO.getName(), projectDTO.getSpaceId(), projectId)) {
            return new ApiResponse("Project with such a name and space already exists", false);
        }
        Project editingProject = projectRepository.findById(projectId).get();
        editingProject.setName(projectDTO.getName());
        editingProject.setSpace(spaceRepository.findById(projectDTO.getSpaceId()).orElseThrow(() -> new ResourceNotFoundException("space ID")));
        editingProject.setAccessType(projectDTO.getAccessType());
        editingProject.setArchived(projectDTO.isArchived());
        editingProject.setColor(projectDTO.getColor());
        projectRepository.save(editingProject);

        return new ApiResponse("Project edited", true);
    }

    @Override
    public ApiResponse deleteProject(Long projectId, User user) {
        WorkspaceUser workspaceUser = workspaceUserRepository.findByUserId(user.getId());
        String roleName = workspaceUser.getWorkspaceRole().getName();
        if (!(isExistsAuthority(roleName, WorkspaceRoleName.ROLE_OWNER.name()) ||
                isExistsAuthority(roleName, WorkspaceRoleName.ROLE_ADMIN.name()))) {
            return new ApiResponse("You don't have authority", false);
        }

        try {
            projectRepository.deleteById(projectId);
            return new ApiResponse("Deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }

    @Override
    public ApiResponse addProjectUser( ProjectUserDTO projectUserDTO, User user) {

        WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(projectUserDTO.getWorkspaceId(), user.getId()).get();
        String roleName = workspaceUser.getWorkspaceRole().getName();
        if (!(isExistsAuthority(roleName, WorkspaceRoleName.ROLE_OWNER.name()) ||
                isExistsAuthority(roleName, WorkspaceRoleName.ROLE_ADMIN.name()))) {
            return new ApiResponse("You don't have authority", false);
        }

        User newMember = workspaceUserRepository.findByUserId(projectUserDTO.getUserId()).getUser();

        ProjectUser projectUser = new ProjectUser(
                projectRepository.findById(projectUserDTO.getProjectId()).orElseThrow(() -> new ResourceNotFoundException("project id")),
                newMember,
                projectUserDTO.getTaskPermission()
        );

        projectUserRepository.save(projectUser);

        return new ApiResponse("User added", true);
    }

    @Override
    public ApiResponse editProjectUser(Long projectUserId, ProjectUserDTO projectUserDTO, User user) {
        WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(projectUserDTO.getWorkspaceId(), user.getId()).get();
        String roleName = workspaceUser.getWorkspaceRole().getName();
        if (!(isExistsAuthority(roleName, WorkspaceRoleName.ROLE_OWNER.name()) ||
                isExistsAuthority(roleName, WorkspaceRoleName.ROLE_ADMIN.name()))) {
            return new ApiResponse("You don't have authority", false);
        }

        if (projectUserRepository.existsByProjectIdAndUserIdAndIdNot(projectUserDTO.getProjectId(), projectUserDTO.getUserId(), projectUserId)) {
            return new ApiResponse("Project user with such project id and user id already exists ", false);
        }

        Optional<ProjectUser> optionalProjectUser = projectUserRepository.findById(projectUserId);

        ProjectUser editingProjectUser = optionalProjectUser.get();
        editingProjectUser.setProject(projectRepository.findById(projectUserDTO.getProjectId()).orElseThrow(() -> new ResourceNotFoundException("project id")));
        User newMember = workspaceUserRepository.findByUserId(projectUserDTO.getUserId()).getUser();
        editingProjectUser.setUser(newMember);
        editingProjectUser.setTaskPermission(projectUserDTO.getTaskPermission());

        projectUserRepository.save(editingProjectUser);
        return new ApiResponse("User edited", true);
    }

    @Override
    public ApiResponse deleteProjectUser(Long projectUserId, User user) {
        WorkspaceUser workspaceUser = workspaceUserRepository.findByUserId(user.getId());
        String roleName = workspaceUser.getWorkspaceRole().getName();
        if (!(isExistsAuthority(roleName, WorkspaceRoleName.ROLE_OWNER.name()) ||
                isExistsAuthority(roleName, WorkspaceRoleName.ROLE_ADMIN.name()))) {
            return new ApiResponse("You don't have authority", false);
        }

        try {
            projectUserRepository.deleteById(projectUserId);
            return new ApiResponse("Deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }
}
