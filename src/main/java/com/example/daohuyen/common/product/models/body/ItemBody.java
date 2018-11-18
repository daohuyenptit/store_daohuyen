package com.example.daohuyen.common.product.models.body;

public class ItemBody {
    private String name;
    private long amount;

    public ItemBody() {
    }

    public ItemBody(String name, long amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
