package com.tiandu.recruit.stud.data.entity;

import java.io.Serializable;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/12/7 16:56
 * 修改人：chendequnn
 * 修改时间：2017/12/7 16:56
 * 修改备注：
 */
public class AdvertInfo implements Serializable {


        /**
         * ID : 12.0
         * AdvName : 第一个广告
         * Summary : 简介
         * AdvUrl : http://advertise.shanghaiiot.org/upload/ImgAdv/Lighthouse-20171207134326.jpg
         * ExpiredDate : 2017-12-22
         * OrderNo : 1
         * Status : 01
         * StatusName : 已发布
         */

        private double ID;
        private String AdvName;
        private String Summary;
        private String AdvImg;
        private String ExpiredDate;
        private String OrderNo;
        private String Status;
        private String StatusName;

    public String getAdvImg() {
        return AdvImg;
    }

    public void setAdvImg(String advImg) {
        AdvImg = advImg;
    }

    public void setID(double ID) {
            this.ID = ID;
        }

        public void setAdvName(String AdvName) {
            this.AdvName = AdvName;
        }

        public void setSummary(String Summary) {
            this.Summary = Summary;
        }


        public void setExpiredDate(String ExpiredDate) {
            this.ExpiredDate = ExpiredDate;
        }

        public void setOrderNo(String OrderNo) {
            this.OrderNo = OrderNo;
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

        public String getAdvName() {
            return AdvName;
        }

        public String getSummary() {
            return Summary;
        }


        public String getExpiredDate() {
            return ExpiredDate;
        }

        public String getOrderNo() {
            return OrderNo;
        }

        public String getStatus() {
            return Status;
        }

        public String getStatusName() {
            return StatusName;
        }
}