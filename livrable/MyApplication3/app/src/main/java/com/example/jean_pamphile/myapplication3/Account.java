package com.example.jean_pamphile.myapplication3;

public class Account {
    private String username;
    private String password;
    private String type;

    public Account(String username, String password, String type){
        this.username = username;
        this.password = password;
        this.type = type;
    }
    public Account(String username, String password){

    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getType(){
        return type;
    }

}
