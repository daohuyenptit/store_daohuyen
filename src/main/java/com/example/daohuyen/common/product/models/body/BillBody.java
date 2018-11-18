package com.example.daohuyen.common.product.models.body;

import java.util.Set;

public class BillBody {
    private String customerID;
    private Set<LotProductBody> lotProductBodies;
    private String receiver;
    private String phone;
    private String address_receive;
    private String transport;
    private int price_transport;
    private String pay;
    

    public BillBody() {
    }

    public BillBody(String customerID, Set<LotProductBody> lotProductBodies, String receiver,String phone, String address_receive,
                    String transport,int price_transport, String pay) {
        this.customerID = customerID;
        this.lotProductBodies = lotProductBodies;
        this.receiver = receiver;
        this.phone=phone;
        this.address_receive = address_receive;
        this.transport = transport;
        this.price_transport=price_transport;
        this.pay = pay;
    }

    public int getPrice_transport() {
        return price_transport;
    }

    public void setPrice_transport(int price_transport) {
        this.price_transport = price_transport;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress_receive() {
        return address_receive;
    }

    public void setAddress_receive(String address_receive) {
        this.address_receive = address_receive;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Set<LotProductBody> getLotProductBodies() {
        return lotProductBodies;
    }

    public void setLotProductBodies(Set<LotProductBody> lotProductBodies) {
        this.lotProductBodies = lotProductBodies;
    }
}
