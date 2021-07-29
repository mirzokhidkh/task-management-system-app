package com.example.taskmanagementsystemapp.service;

import com.example.taskmanagementsystemapp.entity.*;
import com.example.taskmanagementsystemapp.entity.enums.OperationType;
import com.example.taskmanagementsystemapp.entity.enums.WorkspacePermissionName;
import com.example.taskmanagementsystemapp.entity.enums.WorkspaceRoleName;
import com.example.taskmanagementsystemapp.payload.*;
import com.example.taskmanagementsystemapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.taskmanagementsystemapp.entity.enums.WorkspaceRoleName.ROLE_MEMBER;
import static com.example.taskmanagementsystemapp.utils.CommonUtils.isExistsAuthority;


@Service
public class WorkspaceServiceImpl implements WorkspaceService {
    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    WorkspaceUserRepository workspaceUserRepository;
    @Autowired
    WorkspaceRoleRepository workspaceRoleRepository;
    @Autowired
    WorkspacePermissionRepository workspacePermissionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MailService mailService;

    @Override
    public ApiResponse addWorkspace(WorkspaceDTO workspaceDTO, User user) {
        //WORKSPACE OCHDIK
        if (workspaceRepository.existsByOwnerIdAndName(user.getId(), workspaceDTO.getName()))
            return new ApiResponse("Sizda bunday nomli ishxona mavjud", false);
        Workspace workspace = new Workspace(
                workspaceDTO.getName(),
                workspaceDTO.getColor(),
                user,
                workspaceDTO.getAvatarId() == null ? null : attachmentRepository.findById(workspaceDTO.getAvatarId()).orElseThrow(() -> new ResourceNotFoundException("attachment"))
        );

        workspaceRepository.save(workspace);

        //WORKSPACE ROLE OCHDIK
        WorkspaceRole ownerRole = workspaceRoleRepository.save(new WorkspaceRole(
                workspace,
                WorkspaceRoleName.ROLE_OWNER.name(),
                null
        ));
        WorkspaceRole adminRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_ADMIN.name(), null));
        WorkspaceRole memberRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, ROLE_MEMBER.name(), null));
        WorkspaceRole guestRole = workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_GUEST.name(), null));


        //OWERGA HUQUQLARNI BERYAPAMIZ
        WorkspacePermissionName[] workspacePermissionNames = WorkspacePermissionName.values();
        List<WorkspacePermission> workspacePermissions = new ArrayList<>();

        for (WorkspacePermissionName workspacePermissionName : workspacePermissionNames) {
            WorkspacePermission workspacePermission = new WorkspacePermission(
                    ownerRole,
                    workspacePermissionName);
            workspacePermissions.add(workspacePermission);
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_ADMIN)) {
                workspacePermissions.add(new WorkspacePermission(
                        adminRole,
                        workspacePermissionName));
            }
            if (workspacePermissionName.getWorkspaceRoleNames().contains(ROLE_MEMBER)) {
                workspacePermissions.add(new WorkspacePermission(
                        memberRole,
                        workspacePermissionName));
            }
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_GUEST)) {
                workspacePermissions.add(new WorkspacePermission(
                        guestRole,
                        workspacePermissionName));
            }

        }
        workspacePermissionRepository.saveAll(workspacePermissions);

        //WORKSPACE USER OCHDIK
        workspaceUserRepository.save(new WorkspaceUser(
                workspace,
                user,
                ownerRole,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())

        ));

        return new ApiResponse("Ishxona saqlandi", true);
    }

    @Override
    public ApiResponse editWorkspace(Long id, WorkspaceDTO workspaceDTO, User user) {
        WorkspaceUser workspaceUser = workspaceUserRepository
                .findByWorkspaceIdAndUserId(id, user.getId()).orElseThrow(() -> new ResourceNotFoundException("id"));

        String roleName = workspaceUser.getWorkspaceRole().getName();
        if (!isExistsAuthority(roleName, WorkspaceRoleName.ROLE_OWNER.name())) {
            return new ApiResponse("You don't have authority", false);
        }
        Workspace editingWorkspace = workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id"));
        editingWorkspace.setName(workspaceDTO.getName());
        editingWorkspace.setColor(workspaceDTO.getColor());
        editingWorkspace.setAvatar(attachmentRepository
                .findById(workspaceDTO.getAvatarId()).orElseThrow(() -> new ResourceNotFoundException("Avatar id")));
        workspaceRepository.save(editingWorkspace);
        return new ApiResponse("Workspace edited", true);
    }

    @Override
    public ApiResponse changeOwnerWorkspace(Long id, UUID ownerId, User user) {
        WorkspaceUser workspaceUser = workspaceUserRepository
                .findByWorkspaceIdAndUserId(id, user.getId()).orElseThrow(() -> new ResourceNotFoundException("id"));

        String roleName = workspaceUser.getWorkspaceRole().getName();
        if (!isExistsAuthority(roleName, WorkspaceRoleName.ROLE_OWNER.name())) {
            return new ApiResponse("You don't have authority", false);
        }
        User newOwner = userRepository.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("id"));

        workspaceUser.setUser(newOwner);
        workspaceUserRepository.save(workspaceUser);

        Workspace editingWorkspace = workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id"));
        editingWorkspace.setOwner(newOwner);
        workspaceRepository.save(editingWorkspace);
        return new ApiResponse("Workspace owner edited", true);
    }

    @Override
    public ApiResponse deleteWorkspace(Long id) {

        try {
            workspaceRepository.deleteById(id);
            return new ApiResponse("O'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik", false);
        }
    }

    @Override
    public ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDTO memberDTO, User user) {
        WorkspaceUser curWorkspaceUser = workspaceUserRepository.findByUserId(user.getId());
        String roleName = curWorkspaceUser.getWorkspaceRole().getName();
        if (!isExistsAuthority(roleName, WorkspaceRoleName.ROLE_OWNER.name())) {
            return new ApiResponse("You don't have authority", false);
        }
        User member = userRepository.findById(memberDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("id"));
        if (memberDTO.getOperationType().equals(OperationType.ADD)) {
            WorkspaceUser newWorkspaceUser = new WorkspaceUser(
                    workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id")),
                    member,
                    workspaceRoleRepository.findById(memberDTO.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                    new Timestamp(System.currentTimeMillis()),
                    null
            );
            workspaceUserRepository.save(newWorkspaceUser);

            //TODO EMAILGA INVITE XABAR YUBORISH
            String text = "Confirm => \n http://localhost:8080/api/workspace/join?id=" + id;
            mailService.sendEmail(member.getEmail(), "Confirm account", text);

        } else if (memberDTO.getOperationType().equals(OperationType.EDIT)) {
            WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, memberDTO.getId()).orElseGet(WorkspaceUser::new);
            workspaceUser.setWorkspaceRole(workspaceRoleRepository.findById(memberDTO.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")));
            workspaceUserRepository.save(workspaceUser);
        } else if (memberDTO.getOperationType().equals(OperationType.REMOVE)) {
            workspaceUserRepository.deleteByWorkspaceIdAndUserId(id, memberDTO.getId());
        }
        return new ApiResponse("Successfully done", true);
    }

    @Override
    public ApiResponse joinToWorkspace(Long id, User user) {
        Optional<WorkspaceUser> optionalWorkspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, user.getId());
        if (optionalWorkspaceUser.isPresent()) {
            WorkspaceUser workspaceUser = optionalWorkspaceUser.get();
            workspaceUser.setDateJoined(new Timestamp(System.currentTimeMillis()));
            workspaceUserRepository.save(workspaceUser);
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("Error", false);
    }

    @Override
    public ApiResponse getMembersAndGuests(Long wId, User user) {
        WorkspaceUser workspaceUser = workspaceUserRepository.findByUserId(user.getId());
        String roleName = workspaceUser.getWorkspaceRole().getName();
        if (!(isExistsAuthority(roleName, WorkspaceRoleName.ROLE_OWNER.name()) ||
                isExistsAuthority(roleName, WorkspaceRoleName.ROLE_ADMIN.name()))) {
            return new ApiResponse("You don't have authority", false);
        }

        List<WorkspaceUser> workspaceUsers = workspaceUserRepository.findAllByWorkspaceId(wId);

//        List<MemberDTO> members = new ArrayList<>();
//        for (WorkspaceUser currentWorkspaceUser : workspaceUsers) {
//            MemberDTO memberDTO = mapWorkspaceUserToMemberDTO(currentWorkspaceUser);
//            members.add(memberDTO);
//        }

        List<MemberDTO> members = workspaceUsers.stream().map(this::mapWorkspaceUserToMemberDTO).collect(Collectors.toList());
        return new ApiResponse("Users", true, members);
    }

    @Override
    public ApiResponse getMyWorkspaces(User user) {
        WorkspaceUser workspaceUser = workspaceUserRepository.findByUserId(user.getId());
        String roleName = workspaceUser.getWorkspaceRole().getName();
        if (!(isExistsAuthority(roleName, WorkspaceRoleName.ROLE_OWNER.name()) ||
                isExistsAuthority(roleName, WorkspaceRoleName.ROLE_ADMIN.name()))) {
            return new ApiResponse("You don't have authority", false);
        }

        List<WorkspaceUser> workspaceUsers = workspaceUserRepository.findAllByUserId(user.getId());
        List<WorkspaceDTO> workspaceList = workspaceUsers.stream().map(workspaceUser1 -> mapWorkspaceUserToWorkspaceDTO(workspaceUser1.getWorkspace())).collect(Collectors.toList());

        return new ApiResponse("Users", true, workspaceList);
    }


    @Override
    public ApiResponse addRole(WorkspaceRoleDTO workspaceRoleDTO, User user) {
        WorkspaceUser workspaceUser = workspaceUserRepository.findByUserId(user.getId());
        String roleName = workspaceUser.getWorkspaceRole().getName();
        if (!(isExistsAuthority(roleName, WorkspaceRoleName.ROLE_OWNER.name()) ||
                isExistsAuthority(roleName, WorkspaceRoleName.ROLE_ADMIN.name()))) {
            return new ApiResponse("You don't have authority", false);
        }

        if (workspaceRoleRepository.existsByNameAndWorkSpaceId(workspaceRoleDTO.getName(), workspaceRoleDTO.getWorkspaceId())) {
            return new ApiResponse("Role with such a name already exists", false);
        }
        WorkspaceRole workspaceRole = new WorkspaceRole(
                workspaceRepository.findById(workspaceRoleDTO.getWorkspaceId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                workspaceRoleDTO.getName(),
                workspaceRoleDTO.getExtendsRole()
        );
        workspaceRoleRepository.save(workspaceRole);
        List<WorkspacePermission> workspacePermissions = workspacePermissionRepository
                        .findAllByWorkspaceRole_NameAndWorkspaceRole_WorkSpaceId(
                                workspaceRole.getName(),
                                workspaceRole.getWorkSpace().getId());
        List<WorkspacePermission> workspacePermissionList = new ArrayList<>();
        for (WorkspacePermission workspacePermission : workspacePermissions) {
            WorkspacePermission newWorkspacePermission=new WorkspacePermission(
                    workspaceRole,
                    workspacePermission.getPermission()
            );
            workspacePermissionList.add(newWorkspacePermission);
        }
        workspacePermissionRepository.saveAll(workspacePermissionList);

        return new ApiResponse("Role added", true);
    }

    @Override
    public ApiResponse addOrRemovePermissionToRole(WorkspaceRoleDTO workspaceRoleDTO, User user) {
        WorkspaceUser workspaceUser = workspaceUserRepository.findByUserId(user.getId());
        String roleName = workspaceUser.getWorkspaceRole().getName();
        if (!(isExistsAuthority(roleName, WorkspaceRoleName.ROLE_OWNER.name()) ||
                isExistsAuthority(roleName, WorkspaceRoleName.ROLE_ADMIN.name()))) {
            return new ApiResponse("You don't have authority", false);
        }


        WorkspaceRole workspaceRole = workspaceRoleRepository.findById(workspaceRoleDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("id"));
        Optional<WorkspacePermission> optionalWorkspacePermission = workspacePermissionRepository.findByWorkspaceRoleIdAndPermission(workspaceRole.getId(), workspaceRoleDTO.getPermissionName());
        if (workspaceRoleDTO.getOperationType().equals(OperationType.ADD)) {
            if (optionalWorkspacePermission.isPresent()) {
                return new ApiResponse("Already exists", false);
            }
            WorkspacePermission workspacePermission = new WorkspacePermission(workspaceRole, workspaceRoleDTO.getPermissionName());
            workspacePermissionRepository.save(workspacePermission);
            return new ApiResponse("Added", true);
        } else if (workspaceRoleDTO.getOperationType().equals(OperationType.REMOVE)) {
            optionalWorkspacePermission
                    .ifPresent(workspacePermission -> workspacePermissionRepository.deleteById(workspacePermission.getId()));
            return new ApiResponse("Removed", true);
        }
        return new ApiResponse("This command doesn't exist", true);
    }


    public MemberDTO mapWorkspaceUserToMemberDTO(WorkspaceUser workspaceUser) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(workspaceUser.getUser().getId());
        memberDTO.setFullName(workspaceUser.getUser().getFullName());
        memberDTO.setEmail(workspaceUser.getUser().getEmail());
        memberDTO.setRoleName(workspaceUser.getWorkspaceRole().getName());
        memberDTO.setLastActiveTime(workspaceUser.getUser().getLastActiveTime());
        return memberDTO;
    }

    public WorkspaceDTO mapWorkspaceUserToWorkspaceDTO(Workspace workspace) {
        WorkspaceDTO workspaceDTO = new WorkspaceDTO();
        workspaceDTO.setId(workspace.getId());
        workspaceDTO.setName(workspace.getName());
        workspaceDTO.setColor(workspace.getColor());
        Attachment avatar = workspace.getAvatar();
        workspaceDTO.setAvatarId(avatar != null ? avatar.getId() : null);
        workspaceDTO.setInitialLetter(workspace.getInitialLetter());
        return workspaceDTO;
    }

}
