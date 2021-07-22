package com.example.taskmanagementsystemapp.entity;

import com.example.taskmanagementsystemapp.entity.enums.Permission;
import com.example.taskmanagementsystemapp.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Workspace extends AbsEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @ManyToOne(optional = false)
    private User owner;

    @Column(nullable = false)
    private String initialLetter;

    @OneToOne
    private Attachment avatar;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Permission> permissionList;



}
