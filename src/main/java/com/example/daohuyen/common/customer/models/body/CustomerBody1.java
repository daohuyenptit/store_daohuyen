package com.example.daohuyen.common.customer.models.body;
import com.example.daohuyen.common.customer.models.data.Customer;

import java.io.Serializable;
import java.util.Date;

public class CustomerBody1 implements Serializable {
    private String fullName;
    private String phone;
    private int genderID;
    private String address;
    private String identityCard;
    private String description;
    private String avatarUrl;
    private long birthday;
    private String email;

    public CustomerBody1() {
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return genderID;
    }

    public void setGender(int gender) {
        this.genderID = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
