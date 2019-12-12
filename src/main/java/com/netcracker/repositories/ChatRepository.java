package com.netcracker.repositories;

import com.netcracker.entities.Chat;

public class ChatRepository extends AbstractRepository<Chat> {

	public ChatRepository() {
        super(Chat.class);
    }
}
