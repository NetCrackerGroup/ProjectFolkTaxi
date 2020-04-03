package com.netcracker.services.Channels;

import com.netcracker.RenderTemplate.DataParsable;
import com.netcracker.entities.*;
import com.netcracker.repositories.InfoMapRepository;
import com.netcracker.repositories.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;


@Component
public class ApplicationSenderService implements Deliverable {



    private static final Logger LOG = LoggerFactory.getLogger(ApplicationSenderService.class);

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private InfoMapRepository infoMapRepository;


    private DataParsable dataParsable;

    public NotificationRepository getNotifacationRepository() {
        return notificationRepository;
    }

    public ApplicationSenderService()
    {
    }


    public void setDataParsable(DataParsable dataParsable) {
        this.dataParsable = dataParsable;
    }

    public DataParsable getDataParsable() {
        return dataParsable;
    }

    @Override
    public void deliver(InfoContent message, Recipient recipient, Collection<InfoMap> infoMaps) throws MessagingException {
        if (recipient.getClass() != User.class ) {
            LOG.debug("Recipient : {} " , recipient.getClass().getName());
            throw new MessagingException();
        }

        LOG.debug("#######");
        infoMaps.forEach(v -> LOG.debug("InfoMap :   {}", v));
        LOG.debug("##########");
        infoMaps.forEach(infoMapRepository::save);
        LOG.debug("end Save infoMaps");
        User user = (User) recipient;
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        notification.setInfoContent(message);
        notification.setTemplatevalues(infoMaps);
        notification.setWasWatched(false);
        notificationRepository.save(notification);
    }
}
