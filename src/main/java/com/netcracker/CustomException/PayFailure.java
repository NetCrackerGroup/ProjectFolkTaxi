package com.netcracker.CustomException;

public class PayFailure extends Exception {

    public PayFailure(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
