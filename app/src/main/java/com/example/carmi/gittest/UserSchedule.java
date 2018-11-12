package com.example.carmi.gittest;

import java.util.List;

public interface UserSchedule {

    public void schuduleAppointment(Appointment appointment);

    public Appointment makeAppointment(Appointment appointment);

    public void cancelAppointment (Appointment appointment);
}
