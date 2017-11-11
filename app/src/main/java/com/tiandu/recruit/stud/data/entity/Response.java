package com.tiandu.recruit.stud.data.entity;


/**
 * Created by Jerome on 16/9/19.
 * Email :jeromekai8@gmail.com
 */
public class Response<T> {


    /**
     * code : true
     * message : 成功
     * data : []
     */

    private boolean code;
    private String message;
    private T data;

    public boolean isCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
