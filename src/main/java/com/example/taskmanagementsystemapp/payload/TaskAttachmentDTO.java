package com.example.taskmanagementsystemapp.payload;

import com.example.taskmanagementsystemapp.entity.enums.OperationType;
import lombok.Data;

import java.util.UUID;

@Data
public class TaskAttachmentDTO {
    private Long id;

    private UUID taskId;

    private UUID attachmentId;

    private boolean pinCoverImage;

    private OperationType operationType;

    private Long workspaceId;

}
