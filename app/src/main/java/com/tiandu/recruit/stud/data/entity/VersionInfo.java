package com.tiandu.recruit.stud.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zz on 16/9/25.
 * zz
 */

public class VersionInfo implements Serializable{


    private List<DataBean> data;

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<DataBean> getData() {
        return data;
    }

    public static class DataBean {
        /**
         * ID : 2.0
         * VersionNo : 1.0.2
         * UpdateContent : 修改界面
         * UpdateDate : 2017-10-11
         * FileName : 20171105renrenzhao-20171119195320.apk
         * FileSize : 3.2MB
         * Creator : admin
         * CreateTime : 2017-11-19 18:34:08
         * Status : 01
         * StatusName : 已发布
         */

        private double ID;
        private String VersionNo;
        private String UpdateContent;
        private String UpdateDate;
        private String FileName;
        private String FileSize;
        private String Creator;
        private String CreateTime;
        private String Status;
        private String StatusName;

        public void setID(double ID) {
            this.ID = ID;
        }

        public void setVersionNo(String VersionNo) {
            this.VersionNo = VersionNo;
        }

        public void setUpdateContent(String UpdateContent) {
            this.UpdateContent = UpdateContent;
        }

        public void setUpdateDate(String UpdateDate) {
            this.UpdateDate = UpdateDate;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }

        public void setFileSize(String FileSize) {
            this.FileSize = FileSize;
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

        public String getVersionNo() {
            return VersionNo;
        }

        public String getUpdateContent() {
            return UpdateContent;
        }

        public String getUpdateDate() {
            return UpdateDate;
        }

        public String getFileName() {
            return FileName;
        }

        public String getFileSize() {
            return FileSize;
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
}
