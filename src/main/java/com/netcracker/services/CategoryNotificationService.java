package com.netcracker.services;


import com.netcracker.entities.Channel;
import com.netcracker.models.CategoryNotification;
import com.netcracker.services.Channels.Deliverable;
import com.netcracker.services.Channels.EmailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryNotificationService {


    private static final Logger LOG = LoggerFactory.getLogger(CategoryNotificationService.class);

    private EmailServiceImpl emailService;
    private GroupNotificationService groupNotificationService;

    @Autowired public void setEmailService(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }
    @Autowired public void setGroupNotificationService (GroupNotificationService groupNotificationService) {
        this.groupNotificationService = groupNotificationService;
    }

    public CategoryNotificationService() {}

    public Deliverable getChannel (CategoryNotification categoryNotification) {
        Deliverable deliverable = null;

        LOG.debug("CategoryName : {}", categoryNotification.name());

        switch (categoryNotification.name()) {
            case "GROUP":
                deliverable = this.groupNotificationService;
                break;
            case "SUNBSCRIPTION":

                break;

            case "PERSONAL" :
                deliverable = emailService;
                break;
        }
        return deliverable;
    }
}
