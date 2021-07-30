package com.example.taskmanagementsystemapp.payload;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class TaskDTO {
    private UUID id;

    private String name;

    private String description;

    private Long statusId;

    private Long categoryId;

    private Long priorityId;

    private UUID parentTaskId;

    private Date startedDate;

    private boolean startTimeHas;

    private Date dueTime;

    private boolean dueTimeHas;

    private Date estimateDate;

    private Date activatedDate;

    private Long workspaceId;

}
