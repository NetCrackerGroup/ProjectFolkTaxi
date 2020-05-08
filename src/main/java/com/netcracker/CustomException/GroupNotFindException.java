package com.netcracker.CustomException;

public class GroupNotFindException extends Exception {

    public GroupNotFindException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
