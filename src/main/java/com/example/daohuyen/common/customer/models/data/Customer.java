package com.example.daohuyen.common.customer.models.data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;
    private String fullName;
    private String phone;
    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "genderID")
    private Gender genderID;
    private String address;
    private String identityCard;
    private String description;
    private String avatarUrl;
    private Date birthday;
    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "userID")
    private User user;

    public Customer(String id,String fullName, String phone, Gender genderID, String address, String identityCard, String description, String avatarUrl, Date birthday, String email, User user) {
        this.id=id;
        this.fullName = fullName;
        this.phone = phone;
        this.genderID = genderID;
        this.address = address;
        this.identityCard = identityCard;
        this.description = description;
        this.avatarUrl = avatarUrl;
        this.birthday = birthday;
        this.email = email;
        this.user = user;
    }

    public Customer() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGenderID() {
        return genderID;
    }

    public void setGenderID(Gender genderID) {
        this.genderID = genderID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
