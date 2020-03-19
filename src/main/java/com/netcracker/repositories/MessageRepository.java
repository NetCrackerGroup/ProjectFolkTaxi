package com.netcracker.repositories;

import com.netcracker.entities.Chat;
import com.netcracker.entities.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findAllByChat(Chat chat);
    Page<Message>  findAllByChat(Chat chat, Pageable pageable);


	
}
