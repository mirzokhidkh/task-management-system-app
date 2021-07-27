package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long> {
    boolean existsByProjectIdAndUserIdAndIdNot(Long project_id, UUID user_id, Long id);
}
