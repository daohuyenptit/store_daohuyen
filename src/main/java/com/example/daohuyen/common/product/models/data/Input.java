package com.example.daohuyen.common.product.models.data;

import javax.persistence.*;

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
