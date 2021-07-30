package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByNameAndProjectId(String name, Long project_id);
}
