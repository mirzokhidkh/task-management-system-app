package com.example.taskmanagementsystemapp.entity;

import com.example.taskmanagementsystemapp.entity.enums.DependencyType;
import com.example.taskmanagementsystemapp.entity.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskDependency extends AbsLongEntity {
    @ManyToOne
    private Task task;

    @ManyToOne
    private Task dependencyTask;

    @Enumerated(EnumType.STRING)
    private DependencyType dependencyType;
}
