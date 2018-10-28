package com.example.daohuyen.common.bill.models.data;

import com.example.daohuyen.common.customer.models.data.Customer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "bill")
public class Bill {
    public static final String CREATED_DATE = "createdDate";
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

    public Bill() {
    }

    public Bill(Customer customer) {
        this.customer = customer;
    }

    public Bill(Customer customer, Set<LotProduct> lotProducts,int total) {
        this.customer = customer;
        this.lotProducts = lotProducts;
        this.total = total;

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
}
