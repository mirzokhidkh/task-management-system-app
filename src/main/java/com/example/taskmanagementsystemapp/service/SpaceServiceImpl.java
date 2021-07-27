package com.example.taskmanagementsystemapp.service;

import com.example.taskmanagementsystemapp.entity.Space;
import com.example.taskmanagementsystemapp.entity.SpaceUser;
import com.example.taskmanagementsystemapp.entity.User;
import com.example.taskmanagementsystemapp.entity.WorkspaceUser;
import com.example.taskmanagementsystemapp.entity.enums.WorkspaceRoleName;
import com.example.taskmanagementsystemapp.payload.ApiResponse;
import com.example.taskmanagementsystemapp.payload.SpaceDTO;
import com.example.taskmanagementsystemapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.taskmanagementsystemapp.utils.CommonUtils.isExistsAuthority;

@Service
public class SpaceServiceImpl implements SpaceService {
    @Autowired
    SpaceRepository spaceRepository;
    @Autowired
    SpaceUserRepository spaceUserRepository;
    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    IconRepository iconRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    WorkspaceUserRepository workspaceUserRepository;

    @Override
    public ApiResponse addSpace(SpaceDTO spaceDTO, User user) {
        WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(spaceDTO.getWorkspaceId(), user.getId()).get();
        String roleName = workspaceUser.getWorkspaceRole().getName();
        if (!(isExistsAuthority(roleName, WorkspaceRoleName.ROLE_OWNER.name()) ||
                isExistsAuthority(roleName, WorkspaceRoleName.ROLE_ADMIN.name()))) {
            return new ApiResponse("You don't have authority", false);
        }

        if (spaceRepository.existsByNameAndOwnerId(spaceDTO.getName(), spaceDTO.getOwnerId())) {
            return new ApiResponse("Space with such a name and owner already exists", false);
        }

        User spaceOwner = userRepository.findById(spaceDTO.getOwnerId()).orElseThrow(() -> new ResourceNotFoundException("id"));

        Space space = new Space(
                spaceDTO.getName(),
                spaceDTO.getColor(),
                workspaceRepository.findById(spaceDTO.getWorkspaceId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                iconRepository.findById(spaceDTO.getIconId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                attachmentRepository.findById(spaceDTO.getAvatarId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                spaceOwner,
                spaceDTO.getAccessType()
        );
        Space savedSpace = spaceRepository.save(space);

        SpaceUser spaceUser = new SpaceUser(
                savedSpace,
                spaceOwner
        );

        spaceUserRepository.save(spaceUser);

        return new ApiResponse("Space saved", true);
    }

    @Override
    public ApiResponse deleteSpace(Long spaceId, User user) {
        WorkspaceUser workspaceUser = workspaceUserRepository.findByUserId(user.getId());
        String roleName = workspaceUser.getWorkspaceRole().getName();
        if (!(isExistsAuthority(roleName, WorkspaceRoleName.ROLE_OWNER.name()) ||
                isExistsAuthority(roleName, WorkspaceRoleName.ROLE_ADMIN.name()))) {
            return new ApiResponse("You don't have authority", false);
        }

        try {
            spaceRepository.deleteById(spaceId);
            return new ApiResponse("Deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }
}
