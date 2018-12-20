package com.example.daohuyen.common.employee.models.response;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "informationshop")
public class InformationShop {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;
    private String description;
    private String address;
    private String hotline;
    private Date createdDate;

    public InformationShop() {
    }

    public InformationShop(String description, String address, String hotline, Date createdDate) {
        this.description = description;
        this.address = address;
        this.hotline = hotline;
        this.createdDate = createdDate;
    }

    @PrePersist
    public void onPrepersist(){
        this.createdDate = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
