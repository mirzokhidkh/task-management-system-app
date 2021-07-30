package com.example.taskmanagementsystemapp.service;

import com.example.taskmanagementsystemapp.entity.User;
import com.example.taskmanagementsystemapp.payload.ApiResponse;
import com.example.taskmanagementsystemapp.payload.TaskAttachmentDTO;
import com.example.taskmanagementsystemapp.payload.TaskDTO;
import com.example.taskmanagementsystemapp.payload.TaskUserDTO;

import java.util.UUID;

public interface TaskService {
    ApiResponse addOrEditTask(TaskDTO taskDTO, User user);

    ApiResponse deleteTask(UUID taskId, User user);

    ApiResponse changeStatus(Long statusId, UUID taskId, User user, Long wId);

    ApiResponse addOrDeleteTaskAttachment(TaskAttachmentDTO taskAttachmentDTO, User user);

    ApiResponse addOrDeleteTaskUser(TaskUserDTO taskUserDTO, User user);

}
