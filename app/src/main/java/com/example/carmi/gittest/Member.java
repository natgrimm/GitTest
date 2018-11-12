package com.example.carmi.gittest;

public class Member extends User implements UserSchedule {

    public Member(String name, String phoneNumber) {
        super(name, phoneNumber);
        this.setAccess(false);
    }

    @Override
    public void schuduleAppointment() {

    }

    @Override
    public void makeAppointment() {

    }
}
