package com.example.daohuyen.common.customer.models.body;

public class CustomerBody {
    private String fullname;
    private String address;
    private String username;
    private String password;

    public CustomerBody() {
    }

    public CustomerBody(String fullname, String address, String username, String password) {
        this.fullname = fullname;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

