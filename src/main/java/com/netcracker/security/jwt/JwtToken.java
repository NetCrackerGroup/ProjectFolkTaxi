package com.netcracker.security.jwt;


public class JwtToken {
    private String tokenAsString;
    private String email;

    public JwtToken(String tokenAsString, String email) {
        this.tokenAsString = tokenAsString;
        this.email = email;
    }

    public String getTokenAsString() {
        return tokenAsString;
    }

    public String getUserName() {
        return email;
    }

    @Override
    public String toString() {
        return "JwtToken{" +
                "tokenAsString='" + tokenAsString + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
