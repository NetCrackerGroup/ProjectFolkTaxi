package com.netcracker.services.Channels;

import com.netcracker.entities.Chat;
import com.netcracker.entities.InfoContent;
import com.netcracker.entities.Message;
import com.netcracker.entities.Recipient;
import com.netcracker.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.time.LocalDate;

public class ChatSenderService implements Deliverable {

    private Chat chat;
    private MessageRepository messageRepository;

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
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public ChatSenderService(Chat chat) {
        this.chat = chat;
    }

    @Override
    public void deliver(InfoContent message, Recipient recipient) throws MessagingException {
        Message mes = new Message();
        mes.setChat(this.chat);
        mes.setDateOfSending(LocalDate.now());
        mes.setText(message.getText());
    }
}
