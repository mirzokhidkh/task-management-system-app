package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/tag", collectionResourceRel = "list")
public interface TagRepository extends JpaRepository<Tag, Long> {
}
