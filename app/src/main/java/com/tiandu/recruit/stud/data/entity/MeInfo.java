package com.tiandu.recruit.stud.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/8 16:23
 * 修改人：chendequnn
 * 修改时间：2017/11/8 16:23
 * 修改备注：
 */
public class MeInfo implements Serializable {

    /**
     * code : true
     * message : 成功
     * data : [{"ID":21,"MemberID":"M000007","MemberName":null,"BindMobile":"13812345678","BindEmail":null,"BindWechat":null,"BindQQ":null,"IDNumber":null,"RealName":null,"Gender":null,"BirthDate":null,"Province":null,"City":null,"ParentID":null,"Password":null,"LastLoginTime":"-","MemberStatus":"00","MemberStatusName":"正式"}]
     */


        /**
         * ID : 21.0
         * MemberID : M000007
         * MemberName : null
         * BindMobile : 13812345678
         * BindEmail : null
         * BindWechat : null
         * BindQQ : null
         * IDNumber : null
         * RealName : null
         * Gender : null
         * BirthDate : null
         * Province : null
         * City : null
         * ParentID : null
         * Password : null
         * LastLoginTime : -
         * MemberStatus : 00
         * MemberStatusName : 正式
         */

        private double ID;
        private String MemberID;
        private String MemberName;
        private String BindMobile;
        private String BindEmail;
        private String BindWechat;
        private String BindQQ;
        private String IDNumber;
        private String RealName;
        private String Gender;
        private String BirthDate;
        private String Province;
        private String City;
        private String ParentID;
        private String Password;
        private String LastLoginTime;
        private String MemberStatus;
        private String MemberStatusName;

    public double getID() {
        return ID;
    }

    public void setID(double ID) {
        this.ID = ID;
    }

    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String memberID) {
        MemberID = memberID;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        MemberName = memberName;
    }

    public String getBindMobile() {
        return BindMobile;
    }

    public void setBindMobile(String bindMobile) {
        BindMobile = bindMobile;
    }

    public String getBindEmail() {
        return BindEmail;
    }

    public void setBindEmail(String bindEmail) {
        BindEmail = bindEmail;
    }

    public String getBindWechat() {
        return BindWechat;
    }

    public void setBindWechat(String bindWechat) {
        BindWechat = bindWechat;
    }

    public String getBindQQ() {
        return BindQQ;
    }

    public void setBindQQ(String bindQQ) {
        BindQQ = bindQQ;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String parentID) {
        ParentID = parentID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getLastLoginTime() {
        return LastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        LastLoginTime = lastLoginTime;
    }

    public String getMemberStatus() {
        return MemberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        MemberStatus = memberStatus;
    }

    public String getMemberStatusName() {
        return MemberStatusName;
    }

    public void setMemberStatusName(String memberStatusName) {
        MemberStatusName = memberStatusName;
    }
}