package com.example.taskmanagementsystemapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskmanagementsystemapp.entity.WorkspacePermission;

import java.util.UUID;

public interface WorkspacePermissionRepository extends JpaRepository<WorkspacePermission, UUID> {
}
