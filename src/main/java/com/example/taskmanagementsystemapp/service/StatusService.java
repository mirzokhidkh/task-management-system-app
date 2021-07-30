package com.example.taskmanagementsystemapp.service;

import com.example.taskmanagementsystemapp.entity.User;
import com.example.taskmanagementsystemapp.payload.ApiResponse;
import com.example.taskmanagementsystemapp.payload.StatusDTO;

public interface StatusService {
    ApiResponse addOrEditStatus(StatusDTO statusDTO, User user);
    ApiResponse deleteStatus(Long statusId, User user);

}
