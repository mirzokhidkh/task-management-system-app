package com.example.taskmanagementsystemapp.entity;


import com.example.taskmanagementsystemapp.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TimeTracked extends AbsEntity {
    @ManyToOne
    private  Task task;

    @Column(nullable = false)
    private Date startedDate;

    @Column(nullable = false)
    private Date stoppedDate;

}
