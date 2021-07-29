package com.example.taskmanagementsystemapp.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class SpaceMemberDTO {
    private UUID id;

    private Long spaceId;
}
