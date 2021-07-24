package com.example.taskmanagementsystemapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskmanagementsystemapp.entity.Workspace;

import java.util.UUID;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    boolean existsByOwnerIdAndName(UUID owner_id, String name);
}
