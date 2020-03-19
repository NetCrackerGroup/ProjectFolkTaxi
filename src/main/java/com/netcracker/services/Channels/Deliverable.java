package com.netcracker.services.Channels;

import com.netcracker.entities.User;
import com.netcracker.entities.InfoContent;

import javax.mail.MessagingException;

public interface Deliverable <T> {
    void deliver(InfoContent message, T destination) throws MessagingException;
}
