package com.example.taskmanagementsystemapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProjectDTO {
    @NotNull
    private String name;

    @NotNull
    private Long spaceId;

    private String accessType = "PUBLIC";

    private boolean archived;

    private String color;


}
