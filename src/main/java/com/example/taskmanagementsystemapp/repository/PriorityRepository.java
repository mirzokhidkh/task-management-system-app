package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.Comment;
import com.example.taskmanagementsystemapp.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/priority", collectionResourceRel = "list")
public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
