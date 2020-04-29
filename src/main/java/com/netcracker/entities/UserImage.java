package com.netcracker.entities;

public class UserImage {

    private String userImageName;

    private String userImageSource;

    public UserImage(String userImageName, String userImageSource) {
        this.userImageName = userImageName;
        this.userImageSource = userImageSource;
    }

    public String getUserImageName() {
        return userImageName;
    }

    public void setUserImageName(String userImageName) {
        this.userImageName = userImageName;
    }

    public String getUserImageSource() {
        return userImageSource;
    }

    public void setUserImageSource(String userImageSource) {
        this.userImageSource = userImageSource;
    }
}
