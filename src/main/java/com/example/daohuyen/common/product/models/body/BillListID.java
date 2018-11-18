package com.example.daohuyen.common.product.models.body;

import java.util.Set;

public class BillListID {
    Set<String> listID;

    public BillListID() {
    }

    public BillListID(Set<String> listID) {
        this.listID = listID;
    }

    public Set<String> getListID() {
        return listID;
    }

    public void setListID(Set<String> listID) {
        this.listID = listID;
    }
}
