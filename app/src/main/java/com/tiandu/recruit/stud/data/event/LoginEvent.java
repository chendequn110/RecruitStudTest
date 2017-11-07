package com.tiandu.recruit.stud.data.event;

/**
 * Created by Administrator on 2016/11/4.
 */

public class LoginEvent {
    private int isOutLogin;

    public LoginEvent(int isOutLogin) {
        this.isOutLogin = isOutLogin;
    }

    public int getIsOutLogin() {
        return isOutLogin;
    }

    public void setIsOutLogin(int isOutLogin) {
        this.isOutLogin = isOutLogin;
    }
}
