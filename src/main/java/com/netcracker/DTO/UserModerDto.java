package com.netcracker.DTO;

public class UserModerDto {
    private Long userId;
    private String fio;
    private String email;
    private String phoneNumber;
    private Long numberOfComplaints;

    public Long getIsBan() {
        return isBan;
    }

    public void setIsBan(Long isBan) {
        this.isBan = isBan;
    }

    private Long isBan;

    public UserModerDto(Long userId, String fio, String email, String phoneNumber, Long numberOfComplaints, Long isBan) {
        this.userId = userId;
        this.fio = fio;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.numberOfComplaints = numberOfComplaints;
        this.isBan = isBan;
    }

    public UserModerDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getNumberOfComplaints() {
        return numberOfComplaints;
    }

    public void setNumberOfComplaints(Long numberOfComplaints) {
        this.numberOfComplaints = numberOfComplaints;
    }
}

