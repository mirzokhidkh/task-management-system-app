package com.example.taskmanagementsystemapp.entity;


import com.example.taskmanagementsystemapp.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project extends AbsEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Space space;

    @Column(nullable = false)
    private String accessType;

    @Column(nullable = false)
    private boolean archived;

    @Column(nullable = false)
    private String color;
}
