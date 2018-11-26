package com.example.carmi.gittest;

public class Counselor extends User implements UserSchedule {

    public Counselor(String name, String phoneNumber) {
        super(name, phoneNumber);
        this.setAccess(true);
    }

    @Override
    public void schuduleAppointment(Appointment appointment) {
        appointment.setConfirmed(true);
    }

    @Override
    public void makeAppointment(Appointment appointment) {
        appointment.setTaken(false);
        appointment.setConfirmed(false);
        appointment.setBishopricMember(this.getName());

    }

    @Override
    public void cancelAppointment(Appointment appointment) {
        appointment.setName(null);
        appointment.setPhoneNumber(null);
        appointment.setTaken(false);
    }
}
