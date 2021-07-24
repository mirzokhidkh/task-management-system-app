package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.WorkspaceRole;
import com.example.taskmanagementsystemapp.entity.enums.WorkspacePermissionName;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskmanagementsystemapp.entity.WorkspacePermission;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface WorkspacePermissionRepository extends JpaRepository<WorkspacePermission, UUID> {
    WorkspacePermission findByWorkspaceRoleIdAndPermission(UUID workspaceRole_id, WorkspacePermissionName permission);


    @Query(value = "select id from workspace_permission where workspace_role_id=?1 and permission=?2", nativeQuery = true)
    UUID getId(UUID workspaceRole_id, String permission);
}
