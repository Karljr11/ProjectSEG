package com.example.karl.myapplication1;

class Services {
    private String type;
    private double hourlysalary;

    public Services(){

    }

    public Services(String type, double hourlysalary){
        this.type = type;
        this.hourlysalary = hourlysalary;
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

