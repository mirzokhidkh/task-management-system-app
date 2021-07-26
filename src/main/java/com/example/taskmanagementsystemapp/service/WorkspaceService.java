package com.example.taskmanagementsystemapp.service;

import com.example.taskmanagementsystemapp.entity.User;
import com.example.taskmanagementsystemapp.entity.Workspace;
import com.example.taskmanagementsystemapp.payload.*;

import java.util.List;
import java.util.UUID;


public interface WorkspaceService {

    ApiResponse addWorkspace(WorkspaceDTO workspaceDTO, User user);

    ApiResponse editWorkspace(Long id, WorkspaceDTO workspaceDTO);

    ApiResponse changeOwnerWorkspace(Long id, UUID ownerId, User user);

    ApiResponse deleteWorkspace(Long id);

    ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDTO memberDTO);

    ApiResponse joinToWorkspace(Long id, User user);

    List<User> getMembersAndGuests(Long id);

    List<Workspace> getAll();

    ApiResponse addRole(WorkspaceRoleDTO workspaceRoleDTO);

    ApiResponse addPermission(WorkspacePermissionDTO workspacePermissionDTO);

    ApiResponse removePermission(WorkspacePermissionDTO workspacePermissionDTO);
}
