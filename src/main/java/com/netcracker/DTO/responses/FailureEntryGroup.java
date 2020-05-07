package com.netcracker.DTO.responses;

public class FailureEntryGroup extends StatusResponse{
    private String message;

    public FailureEntryGroup(String status, String msg) {
        super(status);
        this.message = msg;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
