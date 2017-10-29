package com.tiandu.recruit.stud.data.entity;

import java.io.Serializable;

/**
 * Created by Jerome on 16/9/18.
 * Email :jeromekai8@gmail.com
 */
public class UserInfo implements Serializable {

    private String icon;
    private String token;
    private String stuNum;
    private String inscode;


    private String photourl;
    private String name;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getInscode() {
        return inscode;
    }

    public void setInscode(String inscode) {
        this.inscode = inscode;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
