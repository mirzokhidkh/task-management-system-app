package com.example.taskmanagementsystemapp.payload;

import com.example.taskmanagementsystemapp.entity.enums.WorkspaceRoleName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WorkspaceRoleDTO {
    @NotNull
    private Long workspaceId;

    @NotNull
    private String name;

    private WorkspaceRoleName extendsRole;

}
