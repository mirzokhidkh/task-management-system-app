package com.example.taskmanagementsystemapp.controller;

import com.example.taskmanagementsystemapp.entity.User;
import com.example.taskmanagementsystemapp.payload.ApiResponse;
import com.example.taskmanagementsystemapp.payload.CategoryDTO;
import com.example.taskmanagementsystemapp.payload.StatusDTO;
import com.example.taskmanagementsystemapp.security.CurrentUser;
import com.example.taskmanagementsystemapp.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/status")
public class StatusController {
    @Autowired
    StatusService statusService;


    @PostMapping
    public HttpEntity<?> addOrEditStatus(@Valid @RequestBody StatusDTO statusDTO, @CurrentUser User user) {
        ApiResponse apiResponse = statusService.addOrEditStatus(statusDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{sId}")
    public HttpEntity<?> deleteStatus(@PathVariable Long sId, @CurrentUser User user) {
        ApiResponse apiResponse = statusService.deleteStatus(sId, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }

}
