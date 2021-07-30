package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
}
