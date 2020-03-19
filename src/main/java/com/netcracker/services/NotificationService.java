package com.netcracker.services;

import com.netcracker.entities.Channel;
import com.netcracker.entities.Group;
import com.netcracker.models.CategoryNotification;
import com.netcracker.services.Channels.Deliverable;
import com.netcracker.entities.InfoContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netcracker.entities.User;

import javax.mail.MessagingException;
import javax.swing.*;
import java.util.Objects;

@Service
public class NotificationService <E>{

    private static final Logger LOG = LoggerFactory.getLogger(NotificationService.class);

    private CategoryNotificationService categoryNotificationService;
    private InfoContentService  infoContentService;

    @Autowired
    public void setEmailService( CategoryNotificationService categoryNotificationService,
                                 InfoContentService infoContentService){
        this.categoryNotificationService = categoryNotificationService;
        this.infoContentService = infoContentService;
    }

    public void notify(
            CategoryNotification categoryNotification,
            String key,
            E destination) throws MessagingException
    {
        Deliverable deliverable = categoryNotificationService.getChannel(categoryNotification);
        InfoContent message = infoContentService.getInfoContentByKey(key);
        if (!Objects.isNull(message) && !Objects.isNull(deliverable)) {
            if (destination.getClass() == User.class) {
                User temp = (User) destination;
                deliverable.deliver(message, temp );
            }
            if ( destination.getClass() == Group.class ) {
                Group temp = (Group) destination;
                deliverable.deliver(message, destination);
            }
        }
        else LOG.error("DON'T notificated!");
    }
}