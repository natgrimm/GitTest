package com.example.carmi.gittest;

import java.util.List;

public class Member extends User implements UserSchedule {

    public Member(String name, String phoneNumber) {
        super(name, phoneNumber);
        this.setAccess(false);
    }

    @Override
    public void schuduleAppointment(Appointment appointment) {
        appointment.setTaken(true);
    }

    @Override
    public Appointment makeAppointment(Appointment appointment) {
        appointment.setName(this.getName());
        appointment.setPhoneNumber(this.getPhoneNumber());

        return appointment;
    }

    @Override
    public void cancelAppointment(Appointment appointment) {
        appointment.setName(null);
        appointment.setPhoneNumber(null);
        appointment.setTaken(false);
    }
}
