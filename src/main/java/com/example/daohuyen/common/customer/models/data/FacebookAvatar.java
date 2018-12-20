package com.example.daohuyen.common.customer.models.data;

public class FacebookAvatar {
    public static final String TYPE = "type";
    public static final String LARGE = "large";
    public static final String REDIRECT = "redirect";

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
