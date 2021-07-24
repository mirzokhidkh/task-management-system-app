package com.example.taskmanagementsystemapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskmanagementsystemapp.entity.WorkspaceRole;

import java.util.UUID;

public interface WorkspaceRoleRepository extends JpaRepository<WorkspaceRole, UUID> {
}
