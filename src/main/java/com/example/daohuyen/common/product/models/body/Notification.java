package com.example.daohuyen.common.product.models.body;

public class Notification {
    private String billID;
    private String customerID;

    public Notification() {
    }

    public Notification(String billID, String customerID) {
        this.billID = billID;
        this.customerID = customerID;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
}
