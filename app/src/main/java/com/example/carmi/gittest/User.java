package com.example.carmi.gittest;

import java.util.ArrayList;
import java.util.List;

public abstract class User {

    private String name;
    private String phoneNumber;
    private boolean Access;
    List<Appointment> appointments;

    public User(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.appointments = new ArrayList<Appointment>();
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

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
