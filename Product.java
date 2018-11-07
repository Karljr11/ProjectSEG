package com.example.karl.serviceapp;

class Product {
    private String _username;
    private String _password;

    public Product() {
    }
    public Product(String _username, String _password) {
        _username = _username;
        _password = _password;
    }
    public void setUsername(String _username) {
        _username = _username;
    }
    public String getUsername() {
        return _username;
    }
    public void setPassword(String _password) {
        _password = _password;
    }
    public String getPassword() {
        return _password;
    }
}
