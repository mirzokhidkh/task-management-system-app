package com.example.taskmanagementsystemapp.payload;

import com.example.taskmanagementsystemapp.entity.enums.TaskPermission;
import lombok.Data;

import java.util.UUID;

@Data
public class CategoryUserDTO {
    private String name;

    private Long categoryId;

    private UUID userId;

    private TaskPermission taskPermission;
}
