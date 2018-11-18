package com.example.daohuyen.common.product.models.body;
import com.example.daohuyen.common.product.models.data.LotProduct;

public class LotProductBody {
    private String idProduct;
    private int price;
    private int number;

    public LotProductBody() {
    }

    public LotProductBody(LotProduct lotProduct) {
        this.idProduct=lotProduct.getProduct().getId();
        this.number=lotProduct.getAmount();
        this.price=lotProduct.getProduct().getPrice();

    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
