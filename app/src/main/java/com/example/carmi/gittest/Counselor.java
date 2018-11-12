package com.example.carmi.gittest;

public class Counselor extends User implements UserSchedule {

    public Counselor(String name, String phoneNumber) {
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
