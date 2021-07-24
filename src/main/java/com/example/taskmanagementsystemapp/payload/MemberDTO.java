package com.example.taskmanagementsystemapp.payload;

import lombok.Data;
import com.example.taskmanagementsystemapp.entity.enums.AddType;

import java.util.UUID;

@Data
public class MemberDTO {
    private UUID id;

    private UUID roleId;

    private AddType addType;//ADD, EDIT, REMOVE
}
