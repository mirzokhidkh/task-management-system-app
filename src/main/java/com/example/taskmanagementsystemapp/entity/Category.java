package com.example.taskmanagementsystemapp.entity;


import com.example.taskmanagementsystemapp.entity.enums.TaskPermission;
import com.example.taskmanagementsystemapp.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends AbsEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Project project;

    @Column(nullable = false)
    private String accessType;

    @Column(nullable = false)
    private boolean archived;


    @Column(nullable = false)
    private String color;

}
