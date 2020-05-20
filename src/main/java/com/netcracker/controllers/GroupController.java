package com.netcracker.controllers;

import com.netcracker.DTO.GroupDto;
import com.netcracker.DTO.mappers.GroupMapper;
import com.netcracker.DTO.responses.StatusResponse;
import com.netcracker.UseCases.EntryGroupUseCase;
import com.netcracker.entities.Group;
import com.netcracker.entities.GroupNotification;
import com.netcracker.models.CategoryNotification;
import com.netcracker.services.*;
import com.netcracker.services.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;



@RestController
@RequestMapping("group")
public class GroupController {

    private static final Logger LOG = LoggerFactory.getLogger(GroupController.class);

    private final GroupService groupService;
    private final GroupMapper groupMapper;
    private final EntryGroupUseCase entryGroupUseCase;

    @Autowired
    public GroupController(GroupService groupService, GroupMapper groupMapper, EntryGroupUseCase entryGroupUseCase) {
        this.groupService = groupService;
        this.groupMapper = groupMapper;
        this.entryGroupUseCase = entryGroupUseCase;
    }


    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods" , "GET, PUT, POST, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    }


    @GetMapping ("")
    public Iterable<GroupDto> getAllGroups ()
    {
        LOG.debug("get All Groups!");

        Iterable<Group> groups = groupService.getAllGroups();
        Collection<GroupDto> groupsDto = new LinkedList<>()  ;
        for ( Group group : groups ) {
            groupsDto.add(groupMapper.toDto(group));
        }
        return groupsDto;
    }

    @GetMapping("/{id}")
    public GroupDto getGroupById (@PathVariable(name = "id") Long id) {


        Group group = groupService.getGroupById(id);
        //MessageDto messageDto = messageMapper.toDto(message);
        GroupDto groupDTo = groupMapper.toDto(group);

        return groupDTo;
    }


    @GetMapping("/entergroup/{link}")
    public StatusResponse addUserInGroup(@PathVariable("link") String link) {
        Map<String, GroupDto> json = new HashMap<String, GroupDto>();
        return entryGroupUseCase.addUserToGroup(link);
    }


    @PostMapping(value = "")
    public Map<String, Long> createGroup (  @RequestParam(name = "name") String name,
                                            @RequestParam(name = "link") String typeGroup,
                                            final HttpServletResponse response) throws Exception {
        Group group = groupService.createGroup(name, typeGroup);
        GroupDto groupDto = groupMapper.toDto(group);
       // LOG.debug("get group with id - {}", group.getGroupId());

        Long id;
        if (group == null) {
            id = null;
        }
        else {
            id = group.getGroupId();
        }
        LOG.debug(" ********** sfsdfsdfdsf");
        Map<String, Long> test2 = new HashMap<String, Long>() {{
            put("group_id", id);
        }};
        return test2;
    }

    @PostMapping("/useringroup")
    public Map<String, Boolean> checkUserIsInvolved(@RequestParam(name = "group_id") Long groupId) {
        LOG.debug("#### checkUserIsInvolved #####");
        Boolean userInGroup = groupService.checkUserIsInvolvedInGroup(groupId);

        Map<String, Boolean>  response = new HashMap<String, Boolean>() {{
            put("isInvolved", userInGroup);
        }};
        return response;
    }

    @PostMapping("/act")
    public Map<String, GroupDto> actGroup(@RequestParam(name="groupId") Long groupId ,
                                    @RequestParam(name="essence") String essence) throws Exception {
        LOG.debug("Entry controller ");
        Map<String, GroupDto> response = new HashMap<String, GroupDto>() ;
        Group group;
        switch (essence) {
            case "leave":
                group = groupService.deleteUserInGroup(groupId);
                LOG.debug("Delete : {}" , group);
                response.put("group", groupMapper.toDto(group));
                break;
            case "connect":
                group = groupService.addUserInGroup(groupId);
                LOG.debug("Connect end :  {}", group);
                response.put("group", groupMapper.toDto(group));
                break;
        }
        return response;
    }

    @PutMapping("/deleteUser")
    public Map<String, GroupDto> deleteUserCompletely(@RequestParam(name="groupId") Long groupId, @RequestParam(name="userId")Long userId){
        LOG.info("[ deleteUserCompletely(userId : {})", userId);
        Map<String, GroupDto> response = new HashMap<String, GroupDto>() ;
        Group group;

        group=groupService.deleteUserCompletely(groupId,userId);
        response.put("group", groupMapper.toDto(group));

        LOG.info("]");
        return response;

    }

    @PostMapping("/userismoderator")
    public Map<String, Boolean> checkUserIsModerator(@RequestParam(name = "group_id") Long groupId) {
        LOG.debug("#### checkUserIsModeraror #####");
        Boolean userIsModerator = groupService.checkUserIsModeratorGroup(groupId);

        Map<String, Boolean>  response = new HashMap<String, Boolean>() {{
            put("isModerator", userIsModerator);
        }};
        return response;
    }

}