package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.TaskDependency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDependencyRepository extends JpaRepository<TaskDependency, Long> {

}
