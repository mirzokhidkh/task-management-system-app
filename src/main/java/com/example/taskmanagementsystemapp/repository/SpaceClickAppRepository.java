package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.SpaceClickApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/spaceClickApp",collectionResourceRel = "list")
public interface SpaceClickAppRepository extends JpaRepository<SpaceClickApp, Long> {
}
