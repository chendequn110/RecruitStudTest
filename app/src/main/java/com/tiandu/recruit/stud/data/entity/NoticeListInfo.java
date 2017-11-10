package com.tiandu.recruit.stud.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/10 13:43
 * 修改人：chendequnn
 * 修改时间：2017/11/10 13:43
 * 修改备注：
 */
public class NoticeListInfo implements Serializable {

    /**
     * ID : 6.0
     * Title : 幼儿园开学通知
     * ArticleContent :
     * Creator : admin
     * CreateTime : 2017-11-16
     * Status : 00
     * StatusName : 未发布
     */

    private double ID;
    private String Title;
    private String ArticleContent;
    private String Creator;
    private String CreateTime;
    private String Status;
    private String StatusName;

    public void setID(double ID) {
        this.ID = ID;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setArticleContent(String ArticleContent) {
        this.ArticleContent = ArticleContent;
    }

    public void setCreator(String Creator) {
        this.Creator = Creator;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
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

    public String getTitle() {
        return Title;
    }

    public String getArticleContent() {
        return ArticleContent;
    }

    public String getCreator() {
        return Creator;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public String getStatus() {
        return Status;
    }

    public String getStatusName() {
        return StatusName;
    }
}