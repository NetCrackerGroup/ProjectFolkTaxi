package com.netcracker.controllers;

import com.netcracker.DTO.NotificationDTO;
import com.netcracker.DTO.mappers.NotificationMapper;
import com.netcracker.entities.Notification;
import com.netcracker.services.ApplicationNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notification")
public class ApplicationNotificationController {

    private static final Logger LOG = LoggerFactory.getLogger(ChatsController.class);

    @Autowired
    private ApplicationNotificationService applicationNotificationService;

    @Autowired
    private NotificationMapper notificationMapper;

    public ApplicationNotificationController() {
    }


    @GetMapping("/all")
    public Map<String, Collection<NotificationDTO>> getAllNotifications() {
        LOG.debug("get all notifications");
        Map<String, Collection<NotificationDTO>> maps = new HashMap();
        Collection<NotificationDTO> notifications = applicationNotificationService.getAllNotifications()
                .stream()
                .map(notificationMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
        maps.put("response" , notifications);
        return maps;
    }

    @GetMapping("/currently")
    public Map<String, Collection<NotificationDTO>> getNotifications() {
        LOG.debug("getNotifications");
        Map<String, Collection<NotificationDTO>> maps = new HashMap();

        Collection<NotificationDTO> notifications = applicationNotificationService.getNotificationsUser()
                .stream()
                .map(notificationMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
        maps.put("response" , notifications);
        return maps;
    }

    @GetMapping("/topic")
    public Map<String, Integer> getTopicNotification() {
        LOG.debug("===================================");
        Map<String, Integer> maps = new HashMap<String, Integer>();
        maps.put( "count" , applicationNotificationService.getCountTopicNotification() );
        LOG.debug("===================================");
        return maps;
    }
}
