package com.netcracker.services;


import com.netcracker.DTO.GroupDto;
import com.netcracker.entities.Chat;
import com.netcracker.entities.Group;
import com.netcracker.entities.Message;
import com.netcracker.entities.Route;
import com.netcracker.repositories.ChatRepository;
import com.netcracker.repositories.GroupRepository;
import com.netcracker.repositories.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.netcracker.entities.Message.comparatordate;


@Service
public class ChatsService {
    private static final Logger LOG = LoggerFactory.getLogger(ChatsService.class);

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private RouteService routeService;



    public void createNewChat(
                              Route route,
                              Group group){
        LOG.debug("[ createChat(route: {}, group : {}", route, group);

        Chat chat = new Chat(route, group);

        chatRepository.save(chat);


        LOG.debug("] (chatId:{})", chat.getChatId());

    }

    public Chat getChatById(Long chatId){

        LOG.debug("[ getById(chatId :{}", chatId);

        Chat chat = chatRepository.findById(chatId).orElse(null);


        LOG.debug("](return : {})", chat);

        return chat;

    }

    public Iterable<Chat> getAllChats(){

        LOG.debug("[ getAllChats");

        Iterable<Chat> chats= chatRepository.findAll();

        LOG.debug("](return : {})",chats);

        return chats;

    }

    public void deleteChat(Long chatId){
        LOG.debug("[ deleteChat(chatId : {})", chatId);

        chatRepository.deleteById(chatId);
    }

    public Chat findChatByGroup(Long groupId){

        LOG.debug("[ findChatByGroup(chatId :{}", groupId);

        Group group = groupService.getGroupById(groupId);
        Chat chat = chatRepository.findByGroup(group);

        LOG.debug("](return : {})", chat);

        return chat;
    }

    public Chat findChatByRoute(Long routeId){

        LOG.debug("[ findChatByRoute(routeId :{}", routeId);


        Route route = routeService.getRoutesBy(routeId);
        Chat chat = chatRepository.findByRoute(route);

        LOG.debug("](return : {})", chat);

        return chat;
    }

}
