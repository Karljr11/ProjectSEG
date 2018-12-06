package com.example.karl.myapplication1;

public class DateOrTime {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public DateOrTime(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public DateOrTime(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

}
