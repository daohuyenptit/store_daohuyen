package com.example.daohuyen.common.bill.models.data;

import com.example.daohuyen.common.customer.models.data.Customer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "bill")
public class Bill {
    public static final String CREATED_DATE = "createDate";
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;
    private int total;
    private Date createDate;
    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    @OneToMany(mappedBy = "bill" , fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private Set<LotProduct> lotProducts;
    private int permit;
    private String receiver;
    private String phone;
    private String address_receive;
    private String transport;
    private int price_transport;
    private String pay;

    public Bill() {
    }

    public Bill(Customer customer) {
        this.customer = customer;
    }

    public Bill(Customer customer, Set<LotProduct> lotProducts,int total,int permit,String receiver,
                String phone,String address_receive,String transport,int price_transport,String pay  ) {
        this.customer = customer;
        this.lotProducts = lotProducts;
        this.total = total;
        this.permit=permit;
        this.receiver=receiver;
        this.phone=phone;
        this.address_receive=address_receive;
        this.transport=transport;
        this.price_transport=price_transport;
        this.pay=pay;

    }

    @PrePersist
    public void onPrepersist(){
        this.createDate = new Date();
    }
    public static String getCreatedDate() {
        return CREATED_DATE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<LotProduct> getLotProducts() {
        return lotProducts;
    }

    public void setLotProducts(Set<LotProduct> lotProducts) {
        this.lotProducts = lotProducts;
    }

    public int getPermit() {
        return permit;
    }

    public void setPermit(int permit) {
        this.permit = permit;
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
}
