package com.netcracker.services.Channels;

import com.netcracker.entities.Recipient;
import com.netcracker.entities.User;
import com.netcracker.entities.InfoContent;

import javax.mail.MessagingException;

public interface Deliverable {
    void deliver(InfoContent message , Recipient recipient) throws MessagingException;
}
