package com.tiandu.recruit.stud.data.entity;

/**
 * Created by Jerome on 16/9/19.
 * Email :jeromekai8@gmail.com
 */
public class Response<T> {


    private  T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
