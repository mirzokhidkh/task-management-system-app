package com.example.taskmanagementsystemapp.entity;

import com.example.taskmanagementsystemapp.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment extends AbsEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private Long size;

    @Column(nullable = false)
    private String contentType;





}
