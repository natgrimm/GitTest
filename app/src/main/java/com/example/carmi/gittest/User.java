package com.example.carmi.gittest;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String memberNumber;
    private String password;
    private int Access;

    public User() {
        this.Access = 3;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccess() {
        return Access;
    }

    public void setAccess(int access) {
        Access = access;
    }
}
