package com.example.taskmanagementsystemapp.service;

import com.example.taskmanagementsystemapp.entity.User;
import com.example.taskmanagementsystemapp.payload.ApiResponse;
import com.example.taskmanagementsystemapp.payload.SpaceDTO;

public interface SpaceService {

    ApiResponse addSpace(SpaceDTO spaceDTO, User user);

    ApiResponse deleteSpace(Long spaceId, User user);

    ApiResponse getViewsBySpaceId(Long sId, User user);


}
