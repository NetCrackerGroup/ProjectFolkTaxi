package com.netcracker.services.Channels;

import com.netcracker.config.DomenUserConfig;
import com.netcracker.entities.Chat;
import com.netcracker.entities.InfoContent;
import com.netcracker.entities.Message;
import com.netcracker.entities.Recipient;
import com.netcracker.models.DomenUser;
import com.netcracker.repositories.MessageRepository;
import com.netcracker.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.time.LocalDate;

@Component
public class ChatSenderService implements Deliverable {

    private static final Logger LOG = LoggerFactory.getLogger(ChatSenderService.class);


    private Chat chat;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DomenUserConfig domenUserConfig;

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public MessageRepository getMessageRepository() {
        return messageRepository;
    }

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository,
                                     UserRepository userRepository,
                                     DomenUserConfig domenUserConfig) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.domenUserConfig = domenUserConfig;
    }


    @Override
    public void deliver(InfoContent message, Recipient recipient) throws MessagingException {
        LOG.debug("Delivere info by chat");
        Message mes = new Message();
        LOG.debug("messageRepository : {}" , messageRepository);
        LOG.debug("userRepository  : {} " , userRepository);
        LOG.debug("domeUserConfig : {}", domenUserConfig);
        LOG.debug("domen user :  {} " , domenUserConfig.getDomenUser());
        LOG.debug("User : {} ", userRepository.findUserByEmail(domenUserConfig.getDomenUser().getDomenName()));
        mes.setUser( userRepository.findUserByEmail(domenUserConfig.getDomenUser().getDomenName()));
        mes.setChat(this.chat);
        mes.setDateOfSending(LocalDate.now());
        mes.setText(message.getText());
        messageRepository.save(mes);
    }
}
