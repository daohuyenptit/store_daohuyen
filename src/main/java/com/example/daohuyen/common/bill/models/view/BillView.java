package com.example.daohuyen.common.bill.models.view;

import com.example.daohuyen.common.bill.models.data.Bill;
import com.example.daohuyen.common.bill.models.data.LotProduct;
import com.example.daohuyen.common.historyOrder.models.view.LotproductView;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class BillView {
    private String id;
    private Set<LotproductView> lotProducts;
    private long createDate;
    private int total;

    public BillView() {
    }

    public BillView(Bill bill) {
        this.id = bill.getId();
        this.lotProducts = new HashSet<>();
        for (LotProduct lotProduct: bill.getLotProducts()) {
            lotProducts.add(new LotproductView(lotProduct));

        }
        this.createDate=bill.getCreateDate().getTime();
        this.total=bill.getTotal();
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
}
