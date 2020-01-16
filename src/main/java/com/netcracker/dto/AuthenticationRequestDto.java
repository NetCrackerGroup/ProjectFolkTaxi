package com.netcracker.dto;

public class AuthenticationRequestDto {
    private String email;
    private String password;

    public void setEmail(String username) {
        this.email = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
