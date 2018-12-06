package com.example.karl.myapplication1;

public class Service {
    private String id;
    private String type;
    private double hourlysalary;
    private double rate;

    public Service(){

    }

    public Service(String id, String type, double hourlysalary){
        this.id = id;
        this.type = type;
        this.hourlysalary = hourlysalary;
    }

    public Service(String type, double hourlysalary, double rate){
        this.type = type;
        this.hourlysalary = hourlysalary;
        this.rate = rate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getType(){
        return type;
    }

    public void setHourlysalary(double hourlysalary) {
        this.hourlysalary = hourlysalary;
    }

    public double getHourlysalary(){
        return hourlysalary;
    }

    public double getRate() {
        return rate;
    }
}


