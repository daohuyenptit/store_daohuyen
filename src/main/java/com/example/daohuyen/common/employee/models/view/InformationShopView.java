package com.example.daohuyen.common.employee.models.view;

import com.example.daohuyen.common.employee.models.response.InformationShop;

public class InformationShopView {
    private String description;
    private String address;
    private String hotline;

    public InformationShopView() {
    }
    public InformationShopView(InformationShop informationShop) {
        this.description = informationShop.getDescription();
        this.address = informationShop.getAddress();
        this.hotline = informationShop.getHotline();
    }

    public InformationShopView(String description, String address, String hotline) {
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
