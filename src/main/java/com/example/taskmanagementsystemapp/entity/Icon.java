package com.example.taskmanagementsystemapp.entity;

import com.example.taskmanagementsystemapp.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Icon extends AbsEntity {
    @Column(nullable = false)
    private String name;

    @OneToOne
    private Attachment attachment;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String initialLetter;

    @Column(nullable = false)
    private String icon;







}
