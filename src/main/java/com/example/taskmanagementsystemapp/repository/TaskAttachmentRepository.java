package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.TaskAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/taskAttachment", collectionResourceRel = "list")
public interface TaskAttachmentRepository extends JpaRepository<TaskAttachment, Long> {
}
