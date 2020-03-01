package com.netcracker.DTO;

import com.netcracker.entities.Chat;
import com.netcracker.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class MessageDto {
    private static final Logger LOG = LoggerFactory.getLogger(MessageDto.class);


    private Long messageId;
    private String text;
    private LocalDate dateOfSending;
    private Chat chat;
    private UserDto user;

   public MessageDto(){

   }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDateOfSending() {
        return dateOfSending;
    }

    public void setDateOfSending(LocalDate dateOfSending) {
        this.dateOfSending = dateOfSending;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "messageId=" + messageId +
                ", text='" + text + '\'' +
                ", dateOfSending=" + dateOfSending +
                ", chat=" + chat +
                ", user=" + user +
                '}';
    }
}
