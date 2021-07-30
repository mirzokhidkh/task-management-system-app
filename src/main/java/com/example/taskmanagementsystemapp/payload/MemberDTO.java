package com.example.taskmanagementsystemapp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import com.example.taskmanagementsystemapp.entity.enums.OperationType;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDTO {
    private UUID id;

    private String fullName;

    private String email;

    private String roleName;

    private Timestamp lastActiveTime;

    private UUID roleId;

    private OperationType operationType;//ADD, EDIT, REMOVE
}
