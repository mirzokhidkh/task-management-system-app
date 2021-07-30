package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.CheckList;
import com.example.taskmanagementsystemapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/checkList", collectionResourceRel = "list")
public interface CheckListRepository extends JpaRepository<CheckList, Long> {
}
