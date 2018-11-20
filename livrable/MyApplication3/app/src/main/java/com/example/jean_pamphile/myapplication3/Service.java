package com.example.jean_pamphile.myapplication3;

public class Service {
    private String id;
    private String type;
    private double hourlysalary;

    public Service(){

    }

    public Service(String id, String type, double hourlysalary){
        this.id = id;
        this.type = type;
        this.hourlysalary = hourlysalary;
    }

    public Service(String type, double hourlysalary){
        this.type = type;
        this.hourlysalary = hourlysalary;
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

    public String getType(){
        return type;
    }

    public void setHourlysalary(double hourlysalary) {
        this.hourlysalary = hourlysalary;
    }

    public double getHourlysalary(){
        return hourlysalary;
    }
}


