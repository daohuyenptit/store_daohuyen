package com.example.daohuyen.common.product.models.view;


import com.example.daohuyen.common.product.models.data.Product;

public class ProductViewModel {
    private String id;
    private String name;
    private int price;
    private String logoUrl;
    private String des;
    private String categoryID;


    public ProductViewModel() {
    }

    public ProductViewModel(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.logoUrl = product.getLogoUrl();
        this.des=product.getDescription();
        this.categoryID=product.getCategory().getId();
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String cateoryID) {
        this.categoryID = cateoryID;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
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
