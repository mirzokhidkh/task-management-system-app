package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.Space;
import com.example.taskmanagementsystemapp.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {
    boolean existsByNameAndOwnerId(String name, UUID owner_id);
}
