package com.netcracker.repositories;

import com.netcracker.entities.Message;

public class MessageRepository extends AbstractRepository<Message>{

	public MessageRepository() {
        super(Message.class);}
	
}
