package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.Project;
import com.example.taskmanagementsystemapp.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsByNameAndSpaceId(String name, Long space_id);
    boolean existsByNameAndSpaceIdAndIdNot(String name, Long space_id, Long id);
}
