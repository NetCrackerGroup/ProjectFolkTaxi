package com.netcracker.services.Channels;

import com.netcracker.entities.InfoMap;
import com.netcracker.entities.Recipient;
import com.netcracker.entities.User;
import com.netcracker.entities.InfoContent;

import javax.mail.MessagingException;
import java.util.Collection;

public interface Deliverable {
    void deliver(InfoContent message , Recipient recipient, Collection<InfoMap> infoMaps) throws Exception;
}
