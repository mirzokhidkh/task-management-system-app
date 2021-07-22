package com.example.taskmanagementsystemapp.entity;

import com.example.taskmanagementsystemapp.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkspaceUser extends AbsEntity {

    @ManyToOne(optional = false)
    private Workspace workspace;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private WorkspaceRole workspaceRole;

    private Date dataJoined;





}
