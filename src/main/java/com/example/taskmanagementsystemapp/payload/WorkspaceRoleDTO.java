package com.example.taskmanagementsystemapp.payload;

import com.example.taskmanagementsystemapp.entity.enums.OperationType;
import com.example.taskmanagementsystemapp.entity.enums.WorkspacePermissionName;
import com.example.taskmanagementsystemapp.entity.enums.WorkspaceRoleName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class WorkspaceRoleDTO {
    private UUID id;

    @NotNull
    private Long workspaceId;

    @NotNull
    private String name;

    private WorkspaceRoleName extendsRole;

    private WorkspacePermissionName permissionName;

    private OperationType operationType;

}
