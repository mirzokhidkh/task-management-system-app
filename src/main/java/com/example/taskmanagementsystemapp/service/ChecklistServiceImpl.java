package com.example.taskmanagementsystemapp.service;

import com.example.taskmanagementsystemapp.entity.*;
import com.example.taskmanagementsystemapp.entity.enums.WorkspaceRoleName;
import com.example.taskmanagementsystemapp.payload.ApiResponse;
import com.example.taskmanagementsystemapp.payload.CheckListDTO;
import com.example.taskmanagementsystemapp.payload.CheckListItemDTO;
import com.example.taskmanagementsystemapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.example.taskmanagementsystemapp.utils.CommonUtils.isExistsAuthority;

@Service
public class ChecklistServiceImpl implements ChecklistService {
    @Autowired
    CheckListRepository checkListRepository;
    @Autowired
    CheckListItemRepository checkListItemRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    WorkspaceUserRepository workspaceUserRepository;

    @Override
    public ApiResponse addOrEditChecklist(CheckListDTO checkListDTO, Long wId, User user) {
        checkAuthority(wId, user.getId());

        if (checkListRepository.existsByNameAndTaskId(checkListDTO.getName(), checkListDTO.getTaskId())) {
            return new ApiResponse("Check list with such a name and task id already exists", false);
        }
        CheckList checkList = null;
        Task task = taskRepository.findById(checkListDTO.getTaskId()).orElseThrow(() -> new ResourceNotFoundException("task id"));
        if (checkListDTO.getId() == null) {
            checkList = new CheckList(
                    checkListDTO.getName(),
                    task
            );
        } else {
            Optional<CheckList> optionalCheckList = checkListRepository.findById(checkListDTO.getId());
            if (optionalCheckList.isPresent()) {
                checkList = optionalCheckList.get();
                checkList.setName(checkListDTO.getName());
                checkList.setTask(task);
            } else {
                return new ApiResponse("Check list not found", false);
            }
        }
        checkListRepository.save(checkList);
        return new ApiResponse("Check list saved", true);
    }

    @Override
    public ApiResponse deleteChecklist(Long chId, User user) {
        try {
            checkListRepository.deleteById(chId);
            return new ApiResponse("Check list deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }

    @Override
    public ApiResponse addOrEditChecklistItem(CheckListItemDTO checkListItemDTO, Long wId, User user) {
        checkAuthority(wId, user.getId());

        CheckListItem checkListItem = null;
        CheckList checkList = checkListRepository.findById(checkListItemDTO.getCheckListId()).orElseThrow(() -> new ResourceNotFoundException("Check list item id"));
        User assignedUser = userRepository.findById(checkListItemDTO.getAssignedUserId()).orElseThrow(() -> new ResourceNotFoundException("Assigned user id"));

        if (checkListItemDTO.getId() == null) {
            checkListItem = new CheckListItem(
                    checkListItemDTO.getName(),
                    checkList,
                    checkListItemDTO.isResolved(),
                    assignedUser
            );
        } else {
            Optional<CheckListItem> optionalCheckListItem = checkListItemRepository.findById(checkListItemDTO.getId());
            if (optionalCheckListItem.isPresent()) {
                checkListItem = optionalCheckListItem.get();
                checkListItem.setName(checkListItemDTO.getName());
                checkListItem.setCheckList(checkList);
                checkListItem.setResolved(checkListItemDTO.isResolved());
                checkListItem.setAssignedUser(assignedUser);
            } else {
                return new ApiResponse("Check list item  not found", false);
            }
        }
        checkListItemRepository.save(checkListItem);
        return new ApiResponse("Check list item saved", true);
    }

    @Override
    public ApiResponse deleteChecklistItem(Long chId, User user) {
        try {
            checkListItemRepository.deleteById(chId);
            return new ApiResponse("Check list item deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }

    public ApiResponse checkAuthority(Long wId, UUID uId) {
        WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(wId, uId).get();
        String roleName = workspaceUser.getWorkspaceRole().getName();
        if (!(isExistsAuthority(roleName, WorkspaceRoleName.ROLE_OWNER.name()) ||
                isExistsAuthority(roleName, WorkspaceRoleName.ROLE_ADMIN.name()))) {
            return new ApiResponse("You don't have authority", false);
        }
        return null;
    }
}
