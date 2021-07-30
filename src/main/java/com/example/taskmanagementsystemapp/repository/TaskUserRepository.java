package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.TaskUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "/taskUser", collectionResourceRel = "list")
public interface TaskUserRepository extends JpaRepository<TaskUser, UUID> {
}
