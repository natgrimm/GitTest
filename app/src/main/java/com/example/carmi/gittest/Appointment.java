package com.example.carmi.gittest;

public class Appointment {
    private String name;
    private String phoneNumber;
    private int day;
    private int month;
    private int year;
    private int time;
    private boolean taken = false;

    public Appointment() {
        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.time = time;
    }

    public boolean checkPhone(String phoneNumber) {
        return true;
    }
}
