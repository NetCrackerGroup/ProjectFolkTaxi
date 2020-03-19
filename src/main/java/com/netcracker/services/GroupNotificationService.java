package com.netcracker.services;

import com.netcracker.entities.*;
import com.netcracker.repositories.GroupNotificationRepository;
import com.netcracker.services.Channels.Deliverable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Collection;
import java.util.Optional;

@Service
public class GroupNotificationService implements Deliverable {

    private static final Logger LOG = LoggerFactory.getLogger(GroupNotificationService.class);

    private GroupNotificationRepository groupNotificationRepository;

    @Autowired
    public void setGroupNotificationRepository( GroupNotificationRepository groupNotificationRepository,
                                                GroupService groupService) {
        this.groupNotificationRepository = groupNotificationRepository;
    }


    public Iterable<GroupNotification> getGroupNotificationsByGroup(Group group) {

        LOG.debug("Get all notifications of group : {}" , group);

        Iterable<GroupNotification> groupNotifications = groupNotificationRepository.findGroupNotificationsByGroup(group);

        return groupNotifications;
    }

    @Override
    public void deliver(InfoContent message, Recipient destination) throws MessagingException {

    }
}
