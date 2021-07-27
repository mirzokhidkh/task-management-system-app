package com.example.taskmanagementsystemapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskmanagementsystemapp.entity.Attachment;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
