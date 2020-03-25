package com.netcracker.DTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatDto {
    private static final Logger LOG = LoggerFactory.getLogger(ChatDto.class);

    private Long chatId;


    private RouteDto route;


    private GroupDto group;

    public ChatDto() {
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public RouteDto getRoute() {
        return route;
    }

    public void setRoute(RouteDto route) {
        this.route = route;
    }

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "ChatDto{" +
                "chatId=" + chatId +
                ", route=" + route +
                ", group=" + group +
                '}';
    }
}
