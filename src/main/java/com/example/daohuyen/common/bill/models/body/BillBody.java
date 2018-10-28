package com.example.daohuyen.common.bill.models.body;

import java.util.Set;

public class BillBody {
    private String customerID;
    private Set<LotProductBody> lotProductBodies;

    public BillBody() {
    }

    public BillBody(String customerID, Set<LotProductBody> lotProductBodies) {
        this.customerID = customerID;
        this.lotProductBodies = lotProductBodies;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Set<LotProductBody> getLotProductBodies() {
        return lotProductBodies;
    }

    public void setLotProductBodies(Set<LotProductBody> lotProductBodies) {
        this.lotProductBodies = lotProductBodies;
    }
}
