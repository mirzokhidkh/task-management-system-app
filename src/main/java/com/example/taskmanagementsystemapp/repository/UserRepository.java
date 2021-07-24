package com.example.taskmanagementsystemapp.repository;

import com.example.taskmanagementsystemapp.entity.WorkspaceRole;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskmanagementsystemapp.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);


    @Query(value = "select * from users u " +
            "join workspace_user wu on u.id=wu.user_id " +
            "where wu.workspace_role_id in (?1)",
            nativeQuery = true)
    List<User> findMembersAndGuestsFromWorkspace(Collection<UUID> uuids);
}
