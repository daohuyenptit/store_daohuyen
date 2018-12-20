package com.example.daohuyen.common.employee.models.body;

import java.util.Date;

public class InformationShopBody {
    private String description;
    private String address;
    private String hotline;

    public InformationShopBody() {
    }

    public InformationShopBody(String description, String address, String hotline) {
        this.description = description;
        this.address = address;
        this.hotline = hotline;
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


}
