package com.example.taskmanagementsystemapp.payload;

import com.example.taskmanagementsystemapp.entity.enums.WorkspacePermissionName;
import com.example.taskmanagementsystemapp.entity.enums.WorkspaceRoleName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class WorkspacePermissionDTO {
    @NotNull
    private UUID workspaceRoleId;

    @NotNull
    private WorkspacePermissionName permission;

}
