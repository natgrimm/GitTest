package com.example.carmi.gittest;

public abstract class User {

    private String name;
    private String phoneNumber;
    private boolean Access;

    public User(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isAccess() {
        return Access;
    }

    public void setAccess(boolean access) {
        Access = access;
    }
}
