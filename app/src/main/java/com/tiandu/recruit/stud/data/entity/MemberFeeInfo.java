package com.tiandu.recruit.stud.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/9 12:43
 * 修改人：chendequnn
 * 修改时间：2017/11/9 12:43
 * 修改备注：
 */
public class MemberFeeInfo implements Serializable {

    private int iTotalDisplayRecords;
    private int iTotalRecords;
    private double dRemark;

    public double getdRemark() {
        return dRemark;
    }

    public void setdRemark(double dRemark) {
        this.dRemark = dRemark;
    }

    private List<AaDataBean> aaData;

    public int getITotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setITotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public int getITotalRecords() {
        return iTotalRecords;
    }

    public void setITotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public List<AaDataBean> getAaData() {
        return aaData;
    }

    public void setAaData(List<AaDataBean> aaData) {
        this.aaData = aaData;
    }

    public static class AaDataBean {
        /**
         * ChildFee : 137.76
         * DT_RowId : 61.0
         * ID : 61.0
         * IDNumber :
         * MemberID : T001
         * MemberName : 管理员1
         * ParentFee : 34.44
         * ParentID : 0000
         * ParentIDNumber :
         * ParentName : 天都根节点
         * ParentRealName :
         * PlanID : AgentFee_201712
         * RealName :
         * SelfFee : 103.32
         * StartDate : 2017-12-01
         * TakeFee : 0.0
         * TotalFee : 137.76
         */

        private double ChildFee;
        private double DT_RowId;
        private double ID;
        private String IDNumber;
        private String MemberID;
        private String MemberName;
        private double ParentFee;
        private String ParentID;
        private String ParentIDNumber;
        private String ParentName;
        private String ParentRealName;
        private String PlanID;
        private String RealName;
        private double SelfFee;
        private String StartDate;
        private double TakeFee;
        private double TotalFee;
        private double FinalFee;

        public double getFinalFee() {
            return FinalFee;
        }

        public void setFinalFee(double finalFee) {
            FinalFee = finalFee;
        }

        public double getChildFee() {
            return ChildFee;
        }

        public void setChildFee(double ChildFee) {
            this.ChildFee = ChildFee;
        }

        public double getDT_RowId() {
            return DT_RowId;
        }

        public void setDT_RowId(double DT_RowId) {
            this.DT_RowId = DT_RowId;
        }

        public double getID() {
            return ID;
        }

        public void setID(double ID) {
            this.ID = ID;
        }

        public String getIDNumber() {
            return IDNumber;
        }

        public void setIDNumber(String IDNumber) {
            this.IDNumber = IDNumber;
        }

        public String getMemberID() {
            return MemberID;
        }

        public void setMemberID(String MemberID) {
            this.MemberID = MemberID;
        }

        public String getMemberName() {
            return MemberName;
        }

        public void setMemberName(String MemberName) {
            this.MemberName = MemberName;
        }

        public double getParentFee() {
            return ParentFee;
        }

        public void setParentFee(double ParentFee) {
            this.ParentFee = ParentFee;
        }

        public String getParentID() {
            return ParentID;
        }

        public void setParentID(String ParentID) {
            this.ParentID = ParentID;
        }

        public String getParentIDNumber() {
            return ParentIDNumber;
        }

        public void setParentIDNumber(String ParentIDNumber) {
            this.ParentIDNumber = ParentIDNumber;
        }

        public String getParentName() {
            return ParentName;
        }

        public void setParentName(String ParentName) {
            this.ParentName = ParentName;
        }

        public String getParentRealName() {
            return ParentRealName;
        }

        public void setParentRealName(String ParentRealName) {
            this.ParentRealName = ParentRealName;
        }

        public String getPlanID() {
            return PlanID;
        }

        public void setPlanID(String PlanID) {
            this.PlanID = PlanID;
        }

        public String getRealName() {
            return RealName;
        }

        public void setRealName(String RealName) {
            this.RealName = RealName;
        }

        public double getSelfFee() {
            return SelfFee;
        }

        public void setSelfFee(double SelfFee) {
            this.SelfFee = SelfFee;
        }

        public String getStartDate() {
            return StartDate;
        }

        public void setStartDate(String StartDate) {
            this.StartDate = StartDate;
        }

        public double getTakeFee() {
            return TakeFee;
        }

        public void setTakeFee(double TakeFee) {
            this.TakeFee = TakeFee;
        }

        public double getTotalFee() {
            return TotalFee;
        }

        public void setTotalFee(double TotalFee) {
            this.TotalFee = TotalFee;
        }
    }
}