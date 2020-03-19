package com.netcracker.repositories;

import com.netcracker.entities.Group;
import com.netcracker.entities.GroupNotification;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface GroupNotificationRepository extends CrudRepository<GroupNotification, Long> {

    Optional<GroupNotification> findGroupNotificationByGroup(Group group);
    Collection<GroupNotification> findGroupNotificationsByGroup(Group group);
}
