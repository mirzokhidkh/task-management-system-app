package com.example.taskmanagementsystemapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class SpaceDTO {
    @NotNull
    private String name;

    private String color;

    @NotNull
    private Long workspaceId;

    private Long iconId;

    private UUID avatarId;

    @NotNull
    private UUID ownerId;

    private String accessType = "PUBLIC";


}
