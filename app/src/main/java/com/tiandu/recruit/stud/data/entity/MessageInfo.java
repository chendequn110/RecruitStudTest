package com.tiandu.recruit.stud.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2018/1/11 14:30
 * 修改人：chendequnn
 * 修改时间：2018/1/11 14:30
 * 修改备注：
 */
public class MessageInfo implements Serializable {


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
         * ID : 6.0
         * Title : 消息1
         * Creator : admin
         * CreateTime : 2017-11-16
         * Status : 00
         * StatusName : 未发布
         */

        private int ID;
        private String Title;
        private String Creator;
        private String PublishDate;
        private String Status;
        private String StatusName;

        public String getPublishDate() {
            return PublishDate;
        }

        public void setPublishDate(String publishDate) {
            PublishDate = publishDate;
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

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
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
    }
}