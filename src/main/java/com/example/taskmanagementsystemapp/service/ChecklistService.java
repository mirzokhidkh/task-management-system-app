package com.example.taskmanagementsystemapp.service;

import com.example.taskmanagementsystemapp.entity.User;
import com.example.taskmanagementsystemapp.payload.ApiResponse;
import com.example.taskmanagementsystemapp.payload.CheckListDTO;
import com.example.taskmanagementsystemapp.payload.CheckListItemDTO;

public interface ChecklistService {
    ApiResponse addOrEditChecklist(CheckListDTO checkListDTO,Long wId, User user);
    ApiResponse deleteChecklist(Long chId, User user);
    ApiResponse addOrEditChecklistItem(CheckListItemDTO checkListItemDTO,Long wId, User user);
    ApiResponse deleteChecklistItem(Long chId, User user);


}
