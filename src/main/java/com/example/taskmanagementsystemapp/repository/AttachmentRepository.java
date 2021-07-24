package com.example.taskmanagementsystemapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskmanagementsystemapp.entity.Attachment;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
}
