package com.netcracker.services;


import com.netcracker.DTO.MessageDto;
import com.netcracker.entities.Chat;
import com.netcracker.entities.Message;
import com.netcracker.repositories.ChatRepository;
import com.netcracker.repositories.MessageRepository;
import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static com.netcracker.entities.Message.comparatordate;

@Service
public class MessagesService {

    private static final Logger LOG = LoggerFactory.getLogger(MessagesService.class);

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatsService chatsService;

    @Autowired
    private MessageMapper messageMapper;

    public Long createNewMessage(String text,
                                 Chat chat){
        LOG.debug("[ createMessage(text: {},  chat : {}", text, chat);

        Message message = new Message(text,LocalDate.now(),chat);
        messageRepository.save(message);


        LOG.debug("] (messageId:{})", message.getMessageId());
        return message.getMessageId();
    }

    public Message getMessageById(Long messageId){

        LOG.debug("[ getById(messageId :{}", messageId);

        Message message = messageRepository.findById(messageId).orElse(null);


        LOG.debug("](return : {})", message);


        return message;

    }
/*
    public Iterable<Message> getAllMessages(){

        LOG.debug("[ getAllMessages");

        Iterable<Message> messages = messageRepository.findAll();

        LOG.debug("](return : {})",messages);

        return messages;

    }
    */

    public void deleteMessage(Long messageId){
        LOG.debug("[ deleteMessage(messageId : {})", messageId);

        messageRepository.deleteById(messageId);
    }
     public List<Message> getMessages(Long chatId){
         Chat chat = chatsService.getChatById(chatId);
         List<Message> list = messageRepository.findAllByChat(chat);
         list.sort(comparatordate);


        return list;
    }

    public Message sendMessage(Long chatId,String text) {
        Chat chat = chatsService.getChatById(chatId);
        Message message = new Message(text, LocalDate.now(), chat);
        messageRepository.save(message);

        return message;
    }
    public Page<Message> getPageMessage(Long chatId, int page){
        Chat chat = chatsService.getChatById(chatId);
        Pageable pageable =  PageRequest.of(page,2, Sort.Direction.DESC, "messageId");
        Page<Message> pag = messageRepository.findAllByChat(chat,pageable);

        return pag;

    }

}
