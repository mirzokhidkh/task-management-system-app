package com.example.taskmanagementsystemapp.entity;


import com.example.taskmanagementsystemapp.entity.enums.TaskPermission;
import com.example.taskmanagementsystemapp.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProjectUser extends AbsEntity {
    @ManyToOne
    private Project project;

    @ManyToOne
    private User user;


    @Enumerated(EnumType.STRING)
    private TaskPermission taskPermission;
}
