package com.netcracker.services;


import com.netcracker.DTO.GroupDto;
import com.netcracker.entities.Group;
import org.springframework.stereotype.Component;
import com.netcracker.entities.User;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.LinkedList;

@Component
public class GroupMapper extends AbstractMapper<Group, GroupDto>{

    public GroupMapper() {
        super(Group.class, GroupDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Group.class, GroupDto.class).addMapping(Group :: getUsers, GroupDto :: setUsers).setPostConverter(
                context -> {
                    LOG.debug("Collection");
                    Group group = context.getSource();
                    GroupDto groupDto = context.getDestination();
                    Collection<Long> idUsers = new LinkedList<Long>();
                    for ( User user : group.getUsers()){
                        idUsers.add(user.getUserId());
                    }
                    groupDto.setUsers(idUsers);
                    return groupDto;
                }
        );
        mapper.createTypeMap(User.class, Long.class).setPostConverter(context -> {
            LOG.debug("User in Long");
            Long id_user = context.getDestination();
            User user = context.getSource();
            id_user = user.getUserId();
            return context.getDestination();
        });
    }
}
