package com.netcracker.controllers;


import com.netcracker.DTO.ChatDto;
import com.netcracker.DTO.mappers.ChatMapper;
import com.netcracker.entities.Chat;
import com.netcracker.entities.Group;
import com.netcracker.entities.Message;
import com.netcracker.entities.Route;
import com.netcracker.services.ChatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.netcracker.entities.Message.comparatordate;

@RestController
@RequestMapping("/Chat")
public class ChatsController {


    private static final Logger LOG = LoggerFactory.getLogger(ChatsController.class);


    @Autowired
    private ChatMapper chatMapper;
    @Autowired
    private ChatsService chatsService;


    @PostMapping("")
    public void createNewChat(@RequestParam Route route,
                               @RequestParam Group group
                               ) {
        LOG.debug("[ createChat(route: {}, group : {}", route,group);

         chatsService.createNewChat(route, group);


    }

    @GetMapping("/{id}")
    public ChatDto getChatById(@PathVariable(value = "id")String chatId){
        LOG.info("[getChatById : {}", chatId);

        Chat chat = chatsService.getChatById(Long.decode(chatId));
        ChatDto chatDto = chatMapper.toDto(chat);

        LOG.info("] return : {}", chat);

        return chatDto;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public Iterable<Chat> getAllChats(){
        LOG.info("[getAllChats");

        Iterable<Chat> chats = chatsService.getAllChats();

        LOG.info("] return : {}", chats);

        return chats;
    }



    @DeleteMapping("/{id}")
    public void deleteChat(@PathVariable(value = "id")Long chatId){

        LOG.info("[ deleteChat(chatId : {})", chatId);

        chatsService.deleteChat(chatId);

        LOG.info("]");
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/findchat/{groupId}")
    public ChatDto getChatByGroup(@PathVariable(value = "groupId")String groupId){
        LOG.info("[getChatByGroup : {}", groupId);

        Chat chat = chatsService.findChatByGroup(Long.decode(groupId));

        ChatDto chatDto = chatMapper.toDto(chat);
        LOG.info("] return : {}", chat);

        return chatDto;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/findRouteChat/{routeId}")
    public ChatDto getChatByRoute(@PathVariable(value = "routeId")String routeId){
        LOG.info("[getChatByRoute : {}", routeId);

        Chat chat = chatsService.findChatByRoute(Long.decode(routeId));

        ChatDto chatDto = chatMapper.toDto(chat);
        LOG.info("] return : {}", chat);

        return chatDto;
    }



}
