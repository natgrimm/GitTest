package com.example.carmi.gittest;

public class Appointment {
    private String name;
    private int day;
    private int month;
    private int year;
    private int time;
    private boolean taken = false;

    public Appointment(String name, int day, int month, int year, int time) {
        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.time = time;
    }
}
