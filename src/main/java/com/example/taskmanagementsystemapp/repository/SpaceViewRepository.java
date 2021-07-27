package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.SpaceView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/spaceView",collectionResourceRel = "list")
public interface SpaceViewRepository extends JpaRepository<SpaceView, Long> {
}
