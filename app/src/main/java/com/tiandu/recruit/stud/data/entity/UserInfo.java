package com.tiandu.recruit.stud.data.entity;

import java.io.Serializable;

/**
 * Created by Jerome on 16/9/18.
 * Email :jeromekai8@gmail.com
 */
public class UserInfo implements Serializable {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
