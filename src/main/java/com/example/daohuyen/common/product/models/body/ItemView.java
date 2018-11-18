package com.example.daohuyen.common.product.models.body;

import java.util.Set;

public class ItemView {
    private Set<ItemBody> itemBodies;
    private int amount;
    private int accountProduct;
    private int total;

    public ItemView() {
    }

    public ItemView(Set<ItemBody> itemBodies, int amount, int accountProduct, int total) {
        this.itemBodies = itemBodies;
        this.amount = amount;
        this.accountProduct=accountProduct;
        this.total = total;
    }

    public int getAccountProduct() {
        return accountProduct;
    }

    public void setAccountProduct(int accountProduct) {
        this.accountProduct = accountProduct;
    }

    public Set<ItemBody> getItemBodies() {
        return itemBodies;
    }

    public void setItemBodies(Set<ItemBody> itemBodies) {
        this.itemBodies = itemBodies;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
