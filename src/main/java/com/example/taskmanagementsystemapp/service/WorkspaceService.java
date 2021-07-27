package com.example.taskmanagementsystemapp.service;

import com.example.taskmanagementsystemapp.entity.User;
import com.example.taskmanagementsystemapp.entity.Workspace;
import com.example.taskmanagementsystemapp.payload.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.UUID;


public interface WorkspaceService {

    ApiResponse addWorkspace(WorkspaceDTO workspaceDTO, User user);

    ApiResponse editWorkspace(Long id, WorkspaceDTO workspaceDTO,User user);

    ApiResponse changeOwnerWorkspace(Long id, UUID ownerId, User user);

    ApiResponse deleteWorkspace(Long id);

    ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDTO memberDTO,User user);

    ApiResponse joinToWorkspace(Long id, User user);

    ApiResponse getMembersAndGuests(Long id,User user);

    ApiResponse getAll(User user);

    ApiResponse addRole(WorkspaceRoleDTO workspaceRoleDTO,User user);

    ApiResponse addPermission(WorkspacePermissionDTO workspacePermissionDTO,User user);

    ApiResponse removePermission(WorkspacePermissionDTO workspacePermissionDTO,User user);
}
