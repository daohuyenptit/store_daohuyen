package com.example.daohuyen.common.apriori.models;

import com.example.daohuyen.common.bill.models.data.LotProduct;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "input")
public class Input {
    @Id
    private int id;
    private String inputcate;

    public Input() {
    }

    public Input(int id, String inputcate) {

        this.id = id;
        this.inputcate = inputcate;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInputcate() {
        return inputcate;
    }

    public void setInputcate(String inputcate) {
        this.inputcate = inputcate;
    }


}
