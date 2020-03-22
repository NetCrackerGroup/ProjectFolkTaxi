package com.netcracker.services;

import com.netcracker.entities.*;
import com.netcracker.models.CategoryNotification;
import com.netcracker.repositories.MessageRepository;
import com.netcracker.services.Channels.Deliverable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netcracker.entities.Recipient;

import javax.mail.MessagingException;
import javax.swing.*;
import java.util.Objects;

@Service
public class NotificationService {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationService.class);



    @Autowired
    public void setEmailService(){
    }

    /**
     *
     * @param infoContent - шаблон сообщениея, берем из infoContentRepository, ищем по строковуму ключю (так как более
     *                    понятен, чем просто число), если нет подходящего добавлеям в базу
     * @param deliverable - тип канала связи
     * @param recipient сюда кидаем либо Group, либо User (дефолтные)
     * @throws MessagingException
     */
    public void notify(
            InfoContent infoContent,
            Deliverable deliverable,
            Recipient recipient
    ) throws MessagingException {
        deliverable.deliver(infoContent, recipient);
    }
}