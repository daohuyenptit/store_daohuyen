package com.example.daohuyen.common.bill.models.data;

import com.example.daohuyen.common.product.models.data.Product;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "lotproduct")
public class LotProduct {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;
    @OneToOne
    @JoinColumn(name = "productID")
    private Product product;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "billID")
    private Bill bill;
    private int amount;

    public LotProduct() {
    }

    public LotProduct(int amount) {
        this.amount = amount;
    }

    public LotProduct(Product product, Bill bill, int amount) {
        this.product = product;
        this.bill = bill;
        this.amount = amount;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
