package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.TimeTracked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/timeTracked", collectionResourceRel = "list")
public interface TimeTrackedRepository extends JpaRepository<TimeTracked, Long> {
}
