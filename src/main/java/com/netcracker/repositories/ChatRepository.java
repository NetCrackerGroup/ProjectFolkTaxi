package com.netcracker.repositories;

import com.netcracker.DTO.GroupDto;
import com.netcracker.entities.Chat;

import com.netcracker.entities.Group;
import com.netcracker.entities.Message;
import com.netcracker.entities.Route;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {
    Chat findByRoute(Route route);
    Chat findByGroup(Group group);

}
