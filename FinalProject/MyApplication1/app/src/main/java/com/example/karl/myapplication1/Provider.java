package com.example.karl.myapplication1;

public class Provider extends Account {
    private String email;
    private String address;
    private String telephone;
    private String compagnieName;

    public Provider(String username, String password) {
        super(username, password);
    }
    public Provider(String email, String address, String telephone, String compagnieName){
        this.telephone = telephone;
        this.email = email;
        this.address = address;
        this.compagnieName = compagnieName;

    }
    public String getEmail(){
        return email;
    }
    public String getAddress(){
        return address;
    }
    public String getTelephone(){
        return telephone;
    }
    public String getCompagnieName(){
        return compagnieName;
    }
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setCompagnieName(String compagnieName){
        this.compagnieName = compagnieName;
    }

}