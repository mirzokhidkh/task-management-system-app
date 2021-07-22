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
public class WorkspaceRole extends AbsEntity {

    @ManyToOne(optional = false)
    private Workspace workSpace;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String extendsRole;



}
