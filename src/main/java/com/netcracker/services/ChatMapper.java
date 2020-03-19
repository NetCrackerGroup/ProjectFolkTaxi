package com.netcracker.services;

import com.netcracker.DTO.ChatDto;
import com.netcracker.DTO.GroupDto;
import com.netcracker.DTO.RouteDto;
import com.netcracker.entities.Chat;
import com.netcracker.entities.Group;
import com.netcracker.entities.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ChatMapper extends AbstractMapper<Chat, ChatDto> {

    public ChatMapper() {
        super(Chat.class, ChatDto.class);
    }

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private RouteMapper routeMapper;


    @PostConstruct
    public void setupMapper() {
      /* mapper.createTypeMap(Group.class, GroupDto.class).setPostConverter(context -> {
            LOG.debug("Group in Long");
            GroupDto id_group = context.getDestination();
            Group group = context.getSource();
             id_group = groupMapper.toDto(group) ;
            return context.getDestination();
        });*/

        mapper.createTypeMap(Chat.class, ChatDto.class).addMapping(Chat::getRoute, ChatDto::setRoute).setPostConverter(
                context -> {
                    Chat chat = context.getSource();
                    ChatDto chatDto = context.getDestination();
                    Route route = chat.getRoute();
                    RouteDto userRouteDto = routeMapper.toDto(route);
                    chatDto.setRoute(userRouteDto);
                    return chatDto;
                }
        ).addMapping(Chat::getGroup, ChatDto::setGroup).setPostConverter(
                context -> {
                    Chat chat = context.getSource();
                    ChatDto chatDto = context.getDestination();
                    Group group = chat.getGroup();
                    GroupDto groupDto = groupMapper.toDto(group);
                    chatDto.setGroup(groupDto);
                    return chatDto;
                });


      /*  mapper.createTypeMap(Route.class, RouteDto.class).setPostConverter(context -> {
            LOG.debug("Group in Long");
            RouteDto id_route = context.getDestination();
            Route route = context.getSource();
            id_route = routeMapper.toDto(route) ;
            return context.getDestination();
        });*/


    }
}
