package com.netcracker.models;

public class DomenUser {

    private String domenName;
    private String domenPassword;

    public DomenUser(){}

    public String getDomenName() {
        return domenName;
    }

    public DomenUser setDomenName(String domenName) {
        this.domenName = domenName;
        return this;
    }

    public String getDomenPassword() {
        return domenPassword;
    }

    public DomenUser setDomenPassword(String domenPassword) {
        this.domenPassword = domenPassword;
        return this;
    }
}
