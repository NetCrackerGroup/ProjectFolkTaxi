package com.netcracker.repositories;

import com.netcracker.entities.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifacationRepository extends CrudRepository<Notification, Long> {


}
