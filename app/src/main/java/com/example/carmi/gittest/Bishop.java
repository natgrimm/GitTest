package com.example.carmi.gittest;

public class Bishop extends User implements UserSchedule {


    public Bishop(String name, String phoneNumber) {
        super(name, phoneNumber);
        this.setAccess(true);
    }

    @Override
    public void schuduleAppointment() {

    }

    @Override
    public void makeAppointment() {

    }
}
