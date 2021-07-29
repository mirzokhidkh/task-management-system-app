package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.WorkspacePermission;
import com.example.taskmanagementsystemapp.entity.enums.WorkspacePermissionName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkspacePermissionRepository extends JpaRepository<WorkspacePermission, UUID> {
    Optional<WorkspacePermission> findByWorkspaceRoleIdAndPermission(UUID workspaceRole_id, WorkspacePermissionName permission);

    List<WorkspacePermission> findAllByWorkspaceRole_NameAndWorkspaceRole_WorkSpaceId(String workspaceRole_name, Long workspaceRole_workSpace_id);

    @Query(value = "select id from workspace_permission where workspace_role_id=?1 and permission=?2", nativeQuery = true)
    UUID getId(UUID workspaceRole_id, String permission);
}
