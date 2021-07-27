package com.example.taskmanagementsystemapp.payload;

import com.example.taskmanagementsystemapp.entity.enums.AddType;
import lombok.Data;

import java.util.UUID;

@Data
public class SpaceMemberDTO {
    private UUID id;

    private Long spaceId;
}
