package com.netcracker.DTO;

public class ComplainDto {

    private Long complainId;
    private UserModerDto user;
    private UserModerDto adresat;
    private String text;

    public Long getIsBan() {
        return isBan;
    }

    public void setIsBan(Long isBan) {
        this.isBan = isBan;
    }

    private Long isBan;

    public ComplainDto(){}

    public Long getComplainId() {
        return complainId;
    }

    public void setComplainId(Long complainId) {
        this.complainId = complainId;
    }

    public UserModerDto getUser() {
        return user;
    }

    public void setUser(UserModerDto user) {
        this.user = user;
    }

    public UserModerDto getAdresat() {
        return adresat;
    }

    public void setAdresat(UserModerDto adresat) {
        this.adresat = adresat;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
