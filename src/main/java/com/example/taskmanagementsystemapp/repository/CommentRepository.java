package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.Comment;
import com.example.taskmanagementsystemapp.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/comment", collectionResourceRel = "list")
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
