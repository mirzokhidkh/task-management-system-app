package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "/taskHistory", collectionResourceRel = "list")
public interface TaskHistoryRepository extends JpaRepository<TaskHistory, UUID> {
}
