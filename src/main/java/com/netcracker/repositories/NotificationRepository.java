package com.netcracker.repositories;

import com.netcracker.entities.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.netcracker.entities.User;

import java.util.Collection;
import java.util.Optional;


@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {

    Collection<Notification> findByUser(User user);

}
