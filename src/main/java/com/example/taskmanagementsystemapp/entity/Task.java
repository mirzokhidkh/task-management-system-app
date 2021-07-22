package com.example.taskmanagementsystemapp.entity;

import com.example.taskmanagementsystemapp.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task extends AbsEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "text")
    private String description;

    @ManyToOne(optional = false)
    private Status status;

    @ManyToOne(optional = false)
    private Category category;

    @ManyToOne(optional = false)
    private Priority priority;

    @ManyToOne(optional = false)
    private Task parentTask;

    @Column(nullable = false)
    private Date startedDate;

    @Column(nullable = false)
    private boolean startTimeHas;

    @Column(nullable = false)
    private Date dueTime;

    @Column(nullable = false)
    private boolean dueTimeHas;

    @Column(nullable = false)
    private Date estimateDate;

    @Column(nullable = false)
    private Date activatedDate;

    @ManyToMany
    private List<Tag> tagList;

    @ManyToMany
    private List<User> userList;

}
