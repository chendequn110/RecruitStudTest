package com.tiandu.recruit.stud.data.entity;

import java.io.Serializable;

/**
 * Created by cdq on 2017/10/28.
 */

public class LoginInfo  implements Serializable {
    private boolean success;
    private String  info;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
