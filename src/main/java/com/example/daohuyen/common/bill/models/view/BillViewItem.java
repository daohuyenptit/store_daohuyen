package com.example.daohuyen.common.bill.models.view;

import com.example.daohuyen.common.bill.models.data.Bill;
import com.example.daohuyen.common.bill.models.data.LotProduct;
import com.example.daohuyen.common.historyOrder.models.view.LotproductView;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class BillViewItem {
    private String id;
    private String customerName;
    private Set<LotproductView> lotProducts;// đây

    private long createDate;
    private int total;
    private int permit;
    private String receiver;
    private String phone;
    private String address_receive;
    private String transport;
    private int price_transport;
    private String pay;

    public BillViewItem() {
    }

    public BillViewItem(String id, String customerName,Set<LotProduct> lotProductsSet, Date createDate, int total, int permit,
                        String receiver, String phone, String address_receive,
                        String transport, int price_transport, String pay  ) {
        this.id = id;
        this.customerName=customerName;
        this.lotProducts = new HashSet<>();
        for (LotProduct lotProduct: lotProductsSet) {
            lotProducts.add(new LotproductView(lotProduct));
        }
        this.createDate = createDate.getTime();
        this.total = total;
        this.permit = permit;
        this.receiver = receiver;
        this.phone=phone;
        this.address_receive = address_receive;
        this.transport = transport;
        this.price_transport=price_transport;
        this.pay = pay;
    }

    public BillViewItem(Bill bill) {
        this.id = bill.getId();
        this.customerName=bill.getCustomer().getFullName();
        this.lotProducts = new HashSet<>();
        for (LotProduct lotProduct: bill.getLotProducts()) {
            lotProducts.add(new LotproductView(lotProduct));

        }
        this.createDate=bill.getCreateDate().getTime();
        this.total=bill.getTotal();
        this.permit=bill.getPermit();
        this.receiver = bill.getReceiver();
        this.phone=bill.getPhone();
        this.address_receive = bill.getAddress_receive();
        this.transport = bill.getTransport();
        this.price_transport=bill.getPrice_transport();
        this.pay = bill.getPay();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getPrice_transport() {
        return price_transport;
    }

    public void setPrice_transport(int price_transport) {
        this.price_transport = price_transport;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<LotproductView> getLotProducts() {
        return lotProducts;
    }

    public void setLotProducts(Set<LotproductView> lotProducts) {
        this.lotProducts = lotProducts;
    }

    public int getPermit() {
        return permit;
    }

    public void setPermit(int permit) {
        this.permit = permit;
    }
}
