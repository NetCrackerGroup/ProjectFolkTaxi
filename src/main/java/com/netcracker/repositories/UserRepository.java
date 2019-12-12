package com.netcracker.repositories;

import com.netcracker.entities.User;

public class UserRepository extends AbstractRepository<User> {
    public UserRepository() {
        super(User.class);
    }
}
