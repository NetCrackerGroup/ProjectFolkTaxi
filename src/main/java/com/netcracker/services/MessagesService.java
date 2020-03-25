package com.netcracker.services;


import com.netcracker.DTO.MessageDto;
import com.netcracker.DTO.mappers.MessageMapper;
import com.netcracker.entities.*;
import com.netcracker.repositories.ChatRepository;
import com.netcracker.repositories.MessageRepository;
import com.netcracker.repositories.UserRepository;
import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
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
    @Autowired
    private UserRepository userRepository;

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

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails)auth.getPrincipal();
        User user = userRepository.findUserByEmail(userDetail.getUsername());

        Collection<Group> groups = user.getGroups();
        Collection<Route> routes = user.getRoutes();

        Chat chat = chatsService.getChatById(chatId);

        Group group = chat.getGroup();
        LOG.debug("[ getgroup( :{}", group);

        Route route = chat.getRoute();
        LOG.debug("[ getById(:{}", routes);
        LOG.debug("[ getgrmany(:{}", groups);
       // LOG.debug("[ boolgr(:{}",groups.contains(group) );
       // LOG.debug("[ boolrr(:{}",routes.contains(route) );
        Collection<Long> idgroups = new ArrayList<Long>();
        Collection<Long> idroutes = new ArrayList<Long>();
        for ( Group gr: user.getGroups()){
            idgroups.add(gr.getGroupId());
        }
        for ( Route ro: user.getRoutes()){
            idroutes.add(ro.getRouteId());
        }
       // LOG.debug("[ bool3(:{}",idgroups.contains(group.getGroupId()) );
       // LOG.debug("[ bool4(:{}",idroutes.contains(route.getRouteId()) );
       // LOG.debug("[ bool5(:{}",idgroups.contains(group.getGroupId())| idroutes.contains(route.getRouteId()) );





        boolean Exgroup = false;
        boolean Exroute = false;

        if(group != null){
            if(idgroups.contains(group.getGroupId())){
                Exgroup = true;
            }
        }
        if(route != null){
            if(idroutes.contains(route.getRouteId())){
                Exroute = true;
            }
        }

        LOG.debug("[ bool1( :{}", Exgroup);
        LOG.debug("[ bool2( :{}", Exroute);
        LOG.debug("[ bool3( :{}", Exgroup|Exroute);



        if(Exgroup|Exroute) {

            Message message = new Message(text, LocalDate.now(), chat);
            message.setUser(user);
            messageRepository.save(message);
            return message;
        }
        else {
            throw new IllegalArgumentException("Can't change this chat");

        }

    }
    public Page<Message> getPageMessage(Long chatId, int page){

        Chat chat = chatsService.getChatById(chatId);
        Pageable pageable =  PageRequest.of(page,2, Sort.Direction.DESC, "messageId");
        Page<Message> pag = messageRepository.findAllByChat(chat,pageable);

        return pag;

    }

}
