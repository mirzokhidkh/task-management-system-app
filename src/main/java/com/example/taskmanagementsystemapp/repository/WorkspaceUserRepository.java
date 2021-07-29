package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.WorkspaceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkspaceUserRepository extends JpaRepository<WorkspaceUser, UUID> {
    Optional<WorkspaceUser> findByWorkspaceIdAndUserId(Long workspace_id, UUID user_id);

    @Transactional
    @Modifying
    void deleteByWorkspaceIdAndUserId(Long workspace_id, UUID user_id);


    @Query(value = "select wr.name from workspace_user wu \n" +
            "join workspace_role wr on wu.workspace_role_id=wr.id \n" +
            "where wu.id = ?1 ",nativeQuery = true)
    String getRoleName(UUID id);

    WorkspaceUser findByUserId(UUID user_id);

    List<WorkspaceUser> findAllByWorkspaceId(Long workspace_id);

    List<WorkspaceUser> findAllByUserId(UUID user_id);
}
