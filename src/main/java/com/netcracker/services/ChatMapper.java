package com.netcracker.services;

import com.netcracker.DTO.ChatDto;
import com.netcracker.DTO.GroupDto;
import com.netcracker.entities.Chat;
import com.netcracker.entities.Group;
import com.netcracker.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.LinkedList;

@Component
public class ChatMapper extends AbstractMapper<Chat, ChatDto> {

    public ChatMapper() {
        super(Chat.class, ChatDto.class);
    }
/*
    @Autowired
    private GroupMapper groupMapper;
    @PostConstruct
    public void setupMapper(){
        mapper.createTypeMap(Chat.class, ChatDto.class).addMapping(Chat :: getGroup, ChatDto :: setGroup).setPostConverter(
                context -> {

                    Chat chat = context.getSource();
                    ChatDto chatDto = context.getDestination();
                    Collection<Long> idGroups = new LinkedList<Long>();
                    Group group = chat.getGroup();
                    Long groupId = group.getGroupId();
                   GroupDto groupDto =  groupMapper.toDto(group);
                   chatDto.setGroup(groupDto);

                    return chatDto;
                }
        );
    }*/
}
