package com.example.taskmanagementsystemapp.payload;

import com.example.taskmanagementsystemapp.entity.enums.OperationType;
import lombok.Data;

import java.util.UUID;

@Data
public class TaskUserDTO {
    private UUID id;

    private UUID taskId;

    private UUID userId;

    private OperationType operationType;

    private Long workspaceId;

}
