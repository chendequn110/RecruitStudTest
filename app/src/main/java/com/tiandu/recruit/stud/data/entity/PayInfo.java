package com.tiandu.recruit.stud.data.entity;

/**
 * Created by Jerome on 16/9/15.
 * Email :jeromekai8@gmail.com
 */
public class PayInfo {

    private int resId;
    private String name;
    private String content;
    private boolean enable;
    private boolean isVisibility;

    public boolean isVisibility() {
        return isVisibility;
    }

    public void setVisibility(boolean visibility) {
        isVisibility = visibility;
    }

    /**
     * 支付宝
     */

    private String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }


    /**
     * 支付详情
     */


        /**
         * hours : 1
         * part : 21
         * orderNum : 20170419112304346866
         * money : 85.0
         * endDate : 2017-04-19 11:23:12.0
         * price : 85.0
         * startDate : 2017-04-19 11:23:04.0
         * ispay : 0
         */

        private double hours;
        private int part;
        private String orderNum;
        private double money;
        private String endDate;
        private double price;
        private String startDate;
        private int ispay;

        public double getHours() {
            return hours;
        }

        public void setHours(double hours) {
            this.hours = hours;
        }

        public int getPart() {
            return part;
        }

        public void setPart(int part) {
            this.part = part;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public int getIspay() {
            return ispay;
        }

        public void setIspay(int ispay) {
            this.ispay = ispay;
        }

}
