package com.netcracker.Controllers;

import com.netcracker.DTO.GroupDto;
import com.netcracker.entities.Group;
import com.netcracker.services.GroupMapper;
import com.netcracker.services.GroupService;
import com.netcracker.services.TypeGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("group")
public class GroupController {

    private static final Logger LOG = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupMapper groupMapper;


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

        Iterable<GroupDto> groups = groupService.getAllGroups();

        return groups;
    }

    @GetMapping("/{id}")
    public GroupDto getGroupById (@PathVariable(name = "id") String id) {

        Group group = groupService.getGroupById(Long.decode(id));
        //MessageDto messageDto = messageMapper.toDto(message);
        GroupDto groupDTo = groupMapper.toDto(group);

        return groupDTo;
    }



    @PostMapping(value = "")
    public GroupDto createGroup (@RequestParam(name = "name") String name,
                              @RequestParam(name = "link") String link)
    {
        GroupDto group = groupService.createGroup(name, link);

        LOG.debug("get group with id - {}", group.getGroupId());

        return group;
    }
}