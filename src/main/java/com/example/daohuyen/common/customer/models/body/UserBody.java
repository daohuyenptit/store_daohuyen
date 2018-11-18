package com.example.daohuyen.common.customer.models.body;

import io.swagger.annotations.ApiModelProperty;

public class UserBody {
    @ApiModelProperty(notes = "user",position = 1)
    private String username;
    @ApiModelProperty(notes = "pass",position = 2)
    private String password;

    public UserBody() {
    }

    public UserBody(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
