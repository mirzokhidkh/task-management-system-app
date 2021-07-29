package com.example.taskmanagementsystemapp.service;

import com.example.taskmanagementsystemapp.entity.User;
import com.example.taskmanagementsystemapp.payload.ApiResponse;
import com.example.taskmanagementsystemapp.payload.MemberDTO;
import com.example.taskmanagementsystemapp.payload.WorkspaceDTO;
import com.example.taskmanagementsystemapp.payload.WorkspaceRoleDTO;

import java.util.UUID;


public interface WorkspaceService {

    ApiResponse addWorkspace(WorkspaceDTO workspaceDTO, User user);

    ApiResponse editWorkspace(Long id, WorkspaceDTO workspaceDTO,User user);

    ApiResponse changeOwnerWorkspace(Long id, UUID ownerId, User user);

    ApiResponse deleteWorkspace(Long id);

    ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDTO memberDTO,User user);

    ApiResponse joinToWorkspace(Long id, User user);

    ApiResponse getMembersAndGuests(Long id,User user);

    ApiResponse getMyWorkspaces(User user);

    ApiResponse addRole(WorkspaceRoleDTO workspaceRoleDTO,User user);

    ApiResponse addOrRemovePermissionToRole(WorkspaceRoleDTO workspaceRoleDTO,User user);
}
