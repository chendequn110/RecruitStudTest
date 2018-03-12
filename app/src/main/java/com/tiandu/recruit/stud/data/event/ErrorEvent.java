package com.tiandu.recruit.stud.data.event;

/**
 * Created by Administrator on 2017/7/11.
 */

public class ErrorEvent {
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ErrorEvent(String error) {

        this.error = error;
    }
}
