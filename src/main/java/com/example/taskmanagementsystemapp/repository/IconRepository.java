package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.Icon;
import com.example.taskmanagementsystemapp.entity.SpaceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "/icon", collectionResourceRel = "list")
public interface IconRepository extends JpaRepository<Icon, Long> {
}
