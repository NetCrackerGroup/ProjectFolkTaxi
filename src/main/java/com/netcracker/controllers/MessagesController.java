package com.netcracker.controllers;


import com.netcracker.DTO.MessageDto;
import com.netcracker.entities.Chat;
import com.netcracker.entities.Message;
import com.netcracker.repositories.MessageRepository;
import com.netcracker.services.MessageMapper;
import com.netcracker.services.MessagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Messages")
public class MessagesController {

    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private MessagesService messagesService;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageMapper messageMapper;


    @PostMapping("")
    public Long createNewMessage(@RequestParam String text,
                                 @RequestParam Chat chat) {
        LOG.debug("[ createMessage(text: {},  chat : {}", text,  chat);

        Long messageId = messagesService.createNewMessage(text, chat);
        LOG.debug("] (messageId:{})", messageId);
        return messageId;
    }

    @GetMapping("/{id}")
    public MessageDto getMessageById(@PathVariable(value = "id") Long id) {
        LOG.info("[getMessageById : {}", id);

        Message message = messagesService.getMessageById(id);
        MessageDto messageDto = messageMapper.toDto(message);

        LOG.info("] return : {}", messageDto);

        return messageDto;
    }

    /*@GetMapping("")
    public Iterable<Message> getAllMessages() {
        LOG.info("[getAllRoutes");

        Iterable<Message> messages = messagesService.getAllMessages();

        LOG.info("] return : {}", messages);

        return messages;
    }*/

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable(value = "id") Long messageId) {

        LOG.info("[ deleteRoute(routeId : {})", messageId);

        messagesService.deleteMessage(messageId);

        LOG.info("]");
    }
    //juhfybxbnm
    //человек только состоящий

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/chat/{id}")
    public List<MessageDto> getMessages(@PathVariable(value = "id") Long chatId) {
        LOG.info("[ getMessages : {}", chatId);
        List<Message> messages = messagesService.getMessages(chatId);
        List<MessageDto>newlist= new ArrayList<MessageDto>();
        for (Message message: messages) {
            newlist.add(messageMapper.toDto(message));
        }

        LOG.info("] return : {}",newlist);
        return newlist;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping ("/send/{id}")
    public  MessageDto sendMessage(@PathVariable(value="id")Long chatId,@RequestParam(value="text")String text) {
        LOG.info("[ sendMessage : {}", chatId);

        Message message = messagesService.sendMessage(chatId, text);
        MessageDto messageDto = messageMapper.toDto(message);
        LOG.info("] return : {}", message);
        return messageDto;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/messages/{chatId}/pages")
    public Page<MessageDto> getPage(@PathVariable(value = "chatId") Long chatId, @RequestParam int page){
        Page<Message> pagemes;
        pagemes = messagesService.getPageMessage(chatId,page);
        Pageable pageable =  PageRequest.of(page,2, Sort.Direction.DESC, "messageId");
        Page<MessageDto>newpages = messageMapper.maoPagetoDto(pageable,pagemes);


       // model.addAttribute("page",page);
       // model.addAttribute("data",pagemes);

       // model.addAttribute("url","/page/messages");

        return newpages;

    }

    }

