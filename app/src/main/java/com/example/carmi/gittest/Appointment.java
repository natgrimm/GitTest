package com.example.carmi.gittest;

public class Appointment {
    private String name;
    private String phoneNumber;
    private String bishopricMember;
    private int day;
    private int month;
    private int year;
    private double time;
    private boolean taken;

    public Appointment(int day, int month, int year, double time) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.time = time;
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

    public String getBishopricMember() {
        return bishopricMember;
    }

    public void setBishopricMember(String bishopricMember) {
        this.bishopricMember = bishopricMember;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public boolean checkPhone(String phoneNumber) {
        return true;
    }
}
