package com.tiandu.recruit.stud.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/10 13:29
 * 修改人：chendequnn
 * 修改时间：2017/11/10 13:29
 * 修改备注：
 */
public class JobInfo implements Serializable {


    /**
     * sEcho : null
     * iTotalRecords : 2
     * iTotalDisplayRecords : 2
     * aaData : [{"ID":1,"Title":"计算机软件行业","CompanyName":"上海集钢电子商务有限公司","MonthPay":"6000-8000元/月","WorkPlace":"上海","Creator":"admin","CreateTime":"2017-11-05","Status":"01","StatusName":"已发布"},{"ID":2,"Title":"培训老师","CompanyName":"北京XXX培训机构","MonthPay":"20000-30000元./月","WorkPlace":"北京","Creator":"admin","CreateTime":"2017-11-05","Status":"00","StatusName":"未发布"}]
     */

    private int iTotalRecords;
    private int iTotalDisplayRecords;
    private List<AaDataBean> aaData;


    public void setITotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public void setITotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public void setAaData(List<AaDataBean> aaData) {
        this.aaData = aaData;
    }


    public int getITotalRecords() {
        return iTotalRecords;
    }

    public int getITotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public List<AaDataBean> getAaData() {
        return aaData;
    }

    public static class AaDataBean {
        /**
         * ID : 1.0
         * Title : 计算机软件行业
         * CompanyName : 上海集钢电子商务有限公司
         * MonthPay : 6000-8000元/月
         * WorkPlace : 上海
         * Creator : admin
         * CreateTime : 2017-11-05
         * Status : 01
         * StatusName : 已发布
         */

        private int ID;
        private String Title;
        private String CompanyName;
        private String MonthPay;
        private String WorkPlace;
        private String Creator;
        private String PublishDate;
        private String Status;
        private String StatusName;
        private String IsApply;
        private String IsTop;
        private String City;
        private String District;

        public String getCity() {
            return City;
        }

        public void setCity(String city) {
            City = city;
        }

        public String getDistrict() {
            return District;
        }

        public void setDistrict(String district) {
            District = district;
        }

        public String getIsApply() {
            return IsApply;
        }

        public void setIsApply(String isApply) {
            IsApply = isApply;
        }

        public String getIsTop() {
            return IsTop;
        }

        public void setIsTop(String isTop) {
            IsTop = isTop;
        }

        public String getPublishDate() {
            return PublishDate;
        }

        public void setPublishDate(String publishDate) {
            PublishDate = publishDate;
        }

        public String getAwardAmt() {
            return AwardAmt;
        }

        public void setAwardAmt(String awardAmt) {
            AwardAmt = awardAmt;
        }

        private String AwardAmt;

        public void setID(int ID) {
            this.ID = ID;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public void setCompanyName(String CompanyName) {
            this.CompanyName = CompanyName;
        }

        public void setMonthPay(String MonthPay) {
            this.MonthPay = MonthPay;
        }

        public void setWorkPlace(String WorkPlace) {
            this.WorkPlace = WorkPlace;
        }

        public void setCreator(String Creator) {
            this.Creator = Creator;
        }


        public void setStatus(String Status) {
            this.Status = Status;
        }

        public void setStatusName(String StatusName) {
            this.StatusName = StatusName;
        }

        public int getID() {
            return ID;
        }

        public String getTitle() {
            return Title;
        }

        public String getCompanyName() {
            return CompanyName;
        }

        public String getMonthPay() {
            return MonthPay;
        }

        public String getWorkPlace() {
            return WorkPlace;
        }

        public String getCreator() {
            return Creator;
        }



        public String getStatus() {
            return Status;
        }

        public String getStatusName() {
            return StatusName;
        }
    }
}