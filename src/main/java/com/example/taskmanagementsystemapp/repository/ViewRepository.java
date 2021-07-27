package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/view",collectionResourceRel = "list")
public interface ViewRepository extends JpaRepository<View, Long> {
}
