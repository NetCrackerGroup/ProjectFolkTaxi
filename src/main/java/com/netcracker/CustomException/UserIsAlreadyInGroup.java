package com.netcracker.CustomException;

public class UserIsAlreadyInGroup extends Exception {

    public UserIsAlreadyInGroup(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}


