package com.netcracker.UseCases;

import com.netcracker.CustomException.GroupNotFindException;
import com.netcracker.CustomException.UserIsAlreadyInGroup;
import com.netcracker.DTO.responses.FailureEntryGroup;
import com.netcracker.DTO.responses.StatusResponse;
import com.netcracker.DTO.responses.SuccessEntryGroup;
import com.netcracker.entities.Group;
import com.netcracker.services.AuthUserComponent;
import com.netcracker.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.netcracker.entities.User;


@Component
public class EntryGroupUseCase {

    private AuthUserComponent authUserComponent;
    private GroupService groupService;

    @Autowired
    public EntryGroupUseCase(
            GroupService groupService,
            AuthUserComponent authUserComponent
    ) {
        this.groupService = groupService;
        this.authUserComponent = authUserComponent;
    }

    public StatusResponse addUserToGroup (String linkGroup) {
        try{
            Group group = groupService.findGroupByLink(linkGroup);
            User user = authUserComponent.getUser();
            if (user == null ) {
                return new FailureEntryGroup("failure", "Не авторизован");
            }
            groupService.addUserToGroup(user, group);
            return new SuccessEntryGroup("success", group.getGroupId());
        }
        catch (UserIsAlreadyInGroup ex) {
            return new FailureEntryGroup("failure", ex.getMessage());
        }
        catch (GroupNotFindException ex) {
            return new FailureEntryGroup("failure", ex.getMessage());
        }
    }
}
