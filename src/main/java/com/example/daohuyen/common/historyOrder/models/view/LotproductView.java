package com.example.daohuyen.common.historyOrder.models.view;

import com.example.daohuyen.common.bill.models.data.LotProduct;

public class LotproductView {
    private String id;
    private String name;
    private int price;
    private String logoUrl;
    private int amount;

    public LotproductView() {
    }

    public LotproductView(String id, String name, int price, String logoUrl, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.logoUrl = logoUrl;
        this.amount = amount;
    }

    public LotproductView(LotProduct lotProduct) {
        this.id = lotProduct.getId();
        this.name = lotProduct.getProduct().getName();
        this.price = lotProduct.getProduct().getPrice();
        this.logoUrl = lotProduct.getProduct().getLogoUrl();
        this.amount=lotProduct.getAmount();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
