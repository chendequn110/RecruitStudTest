package com.tiandu.recruit.stud.data.entity;

import java.io.Serializable;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/10 13:31
 * 修改人：chendequnn
 * 修改时间：2017/11/10 13:31
 * 修改备注：
 */
public class JobListInfo implements Serializable {
    /**
     * ID : 2.0
     * Title : 培训老师
     * ArticleContent :
     * Creator : admin
     * CreateTime : 2017-11-05
     * Status : 00
     * StatusName : 未发布
     * CompanyName : 北京XXX培训机构
     * MonthPay : 20000-30000元./月
     * WorkPlace : 北京
     */

    private double ID;
    private String Title;
    private String Contents;
    private String Creator;
    private String PublishDate;
    private String Status;
    private String StatusName;
    private String CompanyName;
    private String MonthPay;
    private String WorkPlace;

    public String getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(String publishDate) {
        PublishDate = publishDate;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        Contents = contents;
    }

    public void setID(double ID) {
        this.ID = ID;
    }

    public void setTitle(String Title) {
        this.Title = Title;
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

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public void setMonthPay(String MonthPay) {
        this.MonthPay = MonthPay;
    }

    public void setWorkPlace(String WorkPlace) {
        this.WorkPlace = WorkPlace;
    }

    public double getID() {
        return ID;
    }

    public String getTitle() {
        return Title;
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

    public String getCompanyName() {
        return CompanyName;
    }

    public String getMonthPay() {
        return MonthPay;
    }

    public String getWorkPlace() {
        return WorkPlace;
    }
}