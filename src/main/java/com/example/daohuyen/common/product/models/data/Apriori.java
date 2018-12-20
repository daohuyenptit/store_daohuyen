package com.example.daohuyen.common.product.models.data;

import javax.persistence.*;

@Entity
@Table(name = "apriori_product")
public class Apriori {
    @Id
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idinput")
    private Input input;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idproduct")
    private Product product;

    public Apriori() {
    }

    public Apriori(int id, Input input, Product product) {
        this.id = id;
        this.input = input;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
