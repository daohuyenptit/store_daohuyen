package com.example.daohuyen.common.customer.models.view;


import com.example.daohuyen.common.customer.models.data.Customer;

import java.util.Date;

public class CustomerView {
    private String id;
    private String account;
    private String password;
    private String fullName;
    private String phone;
    private String gender;
    private String address;
    private String identityCard;
    private String description;
    private String avatarUrl;
    private long birthday;
    private String email;

    public CustomerView(String id, String account, String password, String fullName, String phone, String gender, String address, String identityCard, String description, String avatarUrl, long birthday, String email) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.identityCard = identityCard;
        this.description = description;
        this.avatarUrl = avatarUrl;
        this.birthday = birthday;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public CustomerView() {
    }

    public CustomerView(Customer customer) {
        this.id=customer.getId();
        this.fullName = customer.getFullName();
        this.phone = customer.getPhone();
        if(customer.getGenderID()!=null){
            this.gender= customer.getGenderID().getName();

        }
        this.avatarUrl=customer.getAvatarUrl();
        this.address=customer.getAddress();
        this.email=customer.getEmail();
        this.description=customer.getDescription();
        this.identityCard=customer.getIdentityCard();
        this.account=customer.getUser().getUsername();
        this.password=customer.getUser().getPassword();
        if(customer.getBirthday()!=null){
            setBirthday(customer.getBirthday().getTime());

        }

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
}
