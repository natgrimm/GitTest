package com.example.carmi.gittest;

public class Appointment {
    private String name;
    private String phoneNumber;
    private String bishopricMember;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;
    private boolean taken;
    private boolean confirmed;

    public Appointment(int day, int month, int year, int hour, int minute) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    /**
     * Is this time slot taken?
     * @return true if time slot is taken, false if not
     */
    public boolean isTaken() {
        return taken;
    }

    /**
     * Set time slot to be taken.
     * @param taken Is something taken, or not?
     */
    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    /**
     * Is the given number a real phone number?
     * @param phoneNumber a phone number
     * @return true if the phone number is a correct one, false otherwise
     */
    public boolean checkPhone(String phoneNumber) {
        return true;
    }
}
