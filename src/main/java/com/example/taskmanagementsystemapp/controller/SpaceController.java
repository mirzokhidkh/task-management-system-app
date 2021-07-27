package com.example.taskmanagementsystemapp.controller;

import com.example.taskmanagementsystemapp.entity.User;
import com.example.taskmanagementsystemapp.payload.ApiResponse;
import com.example.taskmanagementsystemapp.payload.SpaceDTO;
import com.example.taskmanagementsystemapp.payload.WorkspaceDTO;
import com.example.taskmanagementsystemapp.security.CurrentUser;
import com.example.taskmanagementsystemapp.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/space")
public class SpaceController {

    @Autowired
    SpaceService spaceService;


    @PostMapping
    public HttpEntity<?> addWorkspace(@Valid @RequestBody SpaceDTO spaceDTO, @CurrentUser User user) {
        ApiResponse apiResponse = spaceService.addSpace(spaceDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteWorkspace(@PathVariable Long spaceId, @CurrentUser User user) {
        ApiResponse apiResponse = spaceService.deleteSpace(spaceId, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
