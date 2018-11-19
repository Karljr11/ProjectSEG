package com.example.jean_pamphile.myapplication3;

import java.util.List;

public class Admin extends Account {

    private String type = "";
    private double hourlysalary;
    private List<Service> services;
    private Service service;

    public Admin(String username, String password) {
        super(username, password);
    }

    public void makeService(Service service){
        services.add(service);
    }
}
