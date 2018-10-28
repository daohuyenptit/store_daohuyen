package com.example.daohuyen.common.apriori.models;

import com.example.daohuyen.common.bill.models.data.Bill;
import com.example.daohuyen.common.bill.models.data.LotProduct;
import com.example.daohuyen.common.customer.models.data.Customer;
import com.example.daohuyen.common.product.models.data.Category;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "apriori")
public class Associative {
    @Id
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idinput")
    private Input input;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idcategory")
    private Category category;

    public Associative() {
    }

    public Associative(int id, Input input, Category category) {
        this.id = id;
        this.input = input;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
