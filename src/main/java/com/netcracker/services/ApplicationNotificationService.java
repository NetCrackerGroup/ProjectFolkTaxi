package com.netcracker.services;

import com.netcracker.entities.Notification;
import com.netcracker.repositories.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netcracker.entities.User;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

@Service
public class ApplicationNotificationService {

    Logger LOG = LoggerFactory.getLogger(ApplicationNotificationService.class);

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private AuthUserComponent authUserComponent;

    public Collection<Notification> getNotificationsUser() {
        User user =  authUserComponent.getUser();
        LOG.debug("Method getNotificationsUser() user : {} " , user);
        if ( user != null )
        {
            Collection<Notification> notifications = notificationRepository.findByUser(user);
            notifications = notifications.stream()
                    .filter(x -> !x.isWasWatched())
                    .collect(Collectors.toCollection(LinkedList::new));
            notifications.forEach(x -> x.setWasWatched(true));
            notifications.forEach(notificationRepository::save);
            return notifications;
        }
        LOG.error("Not user!");
        return null;
    }


    public Collection<Notification> getAllNotifications() {
        User user =  authUserComponent.getUser();
        LOG.debug("Method getNotificationsUser() user : {} " , user);
        if ( user != null ) {
            Collection<Notification> notifications = notificationRepository.findByUser(user);
            return notifications;
        }
        LOG.error("Not user");
        return null;
    }

    public int getCountTopicNotification() {
        User user = authUserComponent.getUser();
        LOG.debug("Method getCountTopicNotification() user : {} " , user);
        if ( user != null ) {
            return (int) notificationRepository
                    .findByUser(user)
                    .stream()
                    .filter( n -> !n.isWasWatched())
                    .count();
        }
        LOG.error("Not user!");
        return 0;
    }
}
