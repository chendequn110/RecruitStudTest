package com.tiandu.recruit.stud.data.entity;

import java.io.Serializable;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/17 15:40
 * 修改人：chendequnn
 * 修改时间：2017/11/17 15:40
 * 修改备注：
 */
public class ChildMenberInfo implements Serializable {


//    private List<DataBean> data;
//
//    public void setData(List<DataBean> data) {
//        this.data = data;
//    }
//
//    public List<DataBean> getData() {
//        return data;
//    }
//
//    public static class DataBean {
        /**
         * ID : 9.0
         * MemberID : qian2
         * MemberName : 钱二
         * BindMobile :
         * BindEmail :
         * BindWechat :
         * BindQQ :
         * RealName :
         * IDNumber :
         * BankName : null
         * BankAccount : null
         * Gender :
         * BirthDate :
         * Province : null
         * City : null
         * ParentID : T001
         * Password : null
         * LastLoginTime : -
         * Status : 00
         * StatusName : 待认证
         */

        private double ID;
        private String MemberID;
        private String MemberName;
        private String BindMobile;
        private String BindEmail;
        private String BindWechat;
        private String BindQQ;
        private String RealName;
        private String IDNumber;
        private Object BankName;
        private Object BankAccount;
        private String Gender;
        private String BirthDate;
        private Object Province;
        private Object City;
        private String ParentID;
        private Object Password;
        private String LastLoginTime;
        private String Status;
        private String StatusName;

    public String getChildMemberNum() {
        return ChildMemberNum;
    }

    public void setChildMemberNum(String childMemberNum) {
        ChildMemberNum = childMemberNum;
    }

    private String ChildMemberNum
                ;

        public void setID(double ID) {
            this.ID = ID;
        }

        public void setMemberID(String MemberID) {
            this.MemberID = MemberID;
        }

        public void setMemberName(String MemberName) {
            this.MemberName = MemberName;
        }

        public void setBindMobile(String BindMobile) {
            this.BindMobile = BindMobile;
        }

        public void setBindEmail(String BindEmail) {
            this.BindEmail = BindEmail;
        }

        public void setBindWechat(String BindWechat) {
            this.BindWechat = BindWechat;
        }

        public void setBindQQ(String BindQQ) {
            this.BindQQ = BindQQ;
        }

        public void setRealName(String RealName) {
            this.RealName = RealName;
        }

        public void setIDNumber(String IDNumber) {
            this.IDNumber = IDNumber;
        }

        public void setBankName(Object BankName) {
            this.BankName = BankName;
        }

        public void setBankAccount(Object BankAccount) {
            this.BankAccount = BankAccount;
        }

        public void setGender(String Gender) {
            this.Gender = Gender;
        }

        public void setBirthDate(String BirthDate) {
            this.BirthDate = BirthDate;
        }

        public void setProvince(Object Province) {
            this.Province = Province;
        }

        public void setCity(Object City) {
            this.City = City;
        }

        public void setParentID(String ParentID) {
            this.ParentID = ParentID;
        }

        public void setPassword(Object Password) {
            this.Password = Password;
        }

        public void setLastLoginTime(String LastLoginTime) {
            this.LastLoginTime = LastLoginTime;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public void setStatusName(String StatusName) {
            this.StatusName = StatusName;
        }

        public double getID() {
            return ID;
        }

        public String getMemberID() {
            return MemberID;
        }

        public String getMemberName() {
            return MemberName;
        }

        public String getBindMobile() {
            return BindMobile;
        }

        public String getBindEmail() {
            return BindEmail;
        }

        public String getBindWechat() {
            return BindWechat;
        }

        public String getBindQQ() {
            return BindQQ;
        }

        public String getRealName() {
            return RealName;
        }

        public String getIDNumber() {
            return IDNumber;
        }

        public Object getBankName() {
            return BankName;
        }

        public Object getBankAccount() {
            return BankAccount;
        }

        public String getGender() {
            return Gender;
        }

        public String getBirthDate() {
            return BirthDate;
        }

        public Object getProvince() {
            return Province;
        }

        public Object getCity() {
            return City;
        }

        public String getParentID() {
            return ParentID;
        }

        public Object getPassword() {
            return Password;
        }

        public String getLastLoginTime() {
            return LastLoginTime;
        }

        public String getStatus() {
            return Status;
        }

        public String getStatusName() {
            return StatusName;
        }
//    }
}