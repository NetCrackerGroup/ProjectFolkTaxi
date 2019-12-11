package com.netcracker.repositories;

import com.netcracker.entities.Notification;

public class NotifacationRepository extends AbstractRepository<Notification> {

    public NotifacationRepository() {
        super(Notification.class);
    }
}
