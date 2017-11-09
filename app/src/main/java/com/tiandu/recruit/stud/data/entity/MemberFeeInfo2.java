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
public class MemberFeeInfo2 implements Serializable {


    private List<DataBean> data;

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<DataBean> getData() {
        return data;
    }

    public static class DataBean {
        /**
         * sEcho : null
         * iTotalRecords : 12
         * iTotalDisplayRecords : 12
         * aaData : [{"DT_RowId":61,"ID":61,"PlanID":"AgentFee_201712","StartDate":"2017-12-01","MemberID":"T001","MemberName":"管理员1","IDNumber":"","RealName":"","TakeFee":0,"ChildFee":137.76,"TotalFee":137.76,"SelfFee":103.32,"ParentFee":34.44,"ParentID":"0000","ParentName":"天都根节点","ParentRealName":"","ParentIDNumber":""},{"DT_RowId":62,"ID":62,"PlanID":"AgentFee_201712","StartDate":"2017-12-01","MemberID":"zhao1","MemberName":"赵大","IDNumber":"","RealName":"","TakeFee":100.5,"ChildFee":150.23,"TotalFee":250.73,"SelfFee":188.05,"ParentFee":62.68,"ParentID":"T001","ParentName":"管理员1","ParentRealName":"","ParentIDNumber":""},{"DT_RowId":63,"ID":63,"PlanID":"AgentFee_201712","StartDate":"2017-12-01","MemberID":"li4","MemberName":"李四","IDNumber":"123456789012345678","RealName":"","TakeFee":200.5,"ChildFee":0,"TotalFee":200.5,"SelfFee":150.38,"ParentFee":50.12,"ParentID":"zhao1","ParentName":"赵大","ParentRealName":"","ParentIDNumber":""},{"DT_RowId":64,"ID":64,"PlanID":"AgentFee_201712","StartDate":"2017-12-01","MemberID":"qian2","MemberName":"钱二","IDNumber":"","RealName":"","TakeFee":300.33,"ChildFee":0,"TotalFee":300.33,"SelfFee":225.25,"ParentFee":75.08,"ParentID":"T001","ParentName":"管理员1","ParentRealName":"","ParentIDNumber":""},{"DT_RowId":65,"ID":65,"PlanID":"AgentFee_201712","StartDate":"2017-12-01","MemberID":"zhou5","MemberName":"周五","IDNumber":"","RealName":"","TakeFee":400.44,"ChildFee":0,"TotalFee":400.44,"SelfFee":300.33,"ParentFee":100.11,"ParentID":"zhao1","ParentName":"赵大","ParentRealName":"","ParentIDNumber":""},{"DT_RowId":34,"ID":34,"PlanID":"AgentFee_201711","StartDate":"2017-11-01","MemberID":"T001","MemberName":"管理员1","IDNumber":"","RealName":"","TakeFee":0,"ChildFee":112.73,"TotalFee":112.73,"SelfFee":84.55,"ParentFee":28.18,"ParentID":"0000","ParentName":"天都根节点","ParentRealName":"","ParentIDNumber":""},{"DT_RowId":35,"ID":35,"PlanID":"AgentFee_201711","StartDate":"2017-11-01","MemberID":"zhao1","MemberName":"赵大","IDNumber":"","RealName":"","TakeFee":100.5,"ChildFee":50.12,"TotalFee":150.62,"SelfFee":112.97,"ParentFee":37.65,"ParentID":"T001","ParentName":"管理员1","ParentRealName":"","ParentIDNumber":""},{"DT_RowId":36,"ID":36,"PlanID":"AgentFee_201711","StartDate":"2017-11-01","MemberID":"li4","MemberName":"李四","IDNumber":"123456789012345678","RealName":"","TakeFee":200.5,"ChildFee":0,"TotalFee":200.5,"SelfFee":150.38,"ParentFee":50.12,"ParentID":"zhao1","ParentName":"赵大","ParentRealName":"","ParentIDNumber":""},{"DT_RowId":37,"ID":37,"PlanID":"AgentFee_201711","StartDate":"2017-11-01","MemberID":"qian2","MemberName":"钱二","IDNumber":"","RealName":"","TakeFee":300.33,"ChildFee":0,"TotalFee":300.33,"SelfFee":225.25,"ParentFee":75.08,"ParentID":"T001","ParentName":"管理员1","ParentRealName":"","ParentIDNumber":""},{"DT_RowId":39,"ID":39,"PlanID":"AgentFee_201710","StartDate":"2017-10-01","MemberID":"T001","MemberName":"管理员1","IDNumber":"","RealName":"","TakeFee":0,"ChildFee":37.65,"TotalFee":37.65,"SelfFee":28.24,"ParentFee":9.41,"ParentID":"0000","ParentName":"天都根节点","ParentRealName":"","ParentIDNumber":""},{"DT_RowId":40,"ID":40,"PlanID":"AgentFee_201710","StartDate":"2017-10-01","MemberID":"zhao1","MemberName":"赵大","IDNumber":"","RealName":"","TakeFee":100.5,"ChildFee":50.12,"TotalFee":150.62,"SelfFee":112.97,"ParentFee":37.65,"ParentID":"T001","ParentName":"管理员1","ParentRealName":"","ParentIDNumber":""},{"DT_RowId":41,"ID":41,"PlanID":"AgentFee_201710","StartDate":"2017-10-01","MemberID":"li4","MemberName":"李四","IDNumber":"123456789012345678","RealName":"","TakeFee":200.5,"ChildFee":0,"TotalFee":200.5,"SelfFee":150.38,"ParentFee":50.12,"ParentID":"zhao1","ParentName":"赵大","ParentRealName":"","ParentIDNumber":""}]
         */

        private Object sEcho;
        private int iTotalRecords;
        private int iTotalDisplayRecords;
        private List<AaDataBean> aaData;

        public void setSEcho(Object sEcho) {
            this.sEcho = sEcho;
        }

        public void setITotalRecords(int iTotalRecords) {
            this.iTotalRecords = iTotalRecords;
        }

        public void setITotalDisplayRecords(int iTotalDisplayRecords) {
            this.iTotalDisplayRecords = iTotalDisplayRecords;
        }

        public void setAaData(List<AaDataBean> aaData) {
            this.aaData = aaData;
        }

        public Object getSEcho() {
            return sEcho;
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
             * DT_RowId : 61.0
             * ID : 61.0
             * PlanID : AgentFee_201712
             * StartDate : 2017-12-01
             * MemberID : T001
             * MemberName : 管理员1
             * IDNumber :
             * RealName :
             * TakeFee : 0.0
             * ChildFee : 137.76
             * TotalFee : 137.76
             * SelfFee : 103.32
             * ParentFee : 34.44
             * ParentID : 0000
             * ParentName : 天都根节点
             * ParentRealName :
             * ParentIDNumber :
             */

            private double DT_RowId;
            private double ID;
            private String PlanID;
            private String StartDate;
            private String MemberID;
            private String MemberName;
            private String IDNumber;
            private String RealName;
            private double TakeFee;
            private double ChildFee;
            private double TotalFee;
            private double SelfFee;
            private double ParentFee;
            private String ParentID;
            private String ParentName;
            private String ParentRealName;
            private String ParentIDNumber;

            public void setDT_RowId(double DT_RowId) {
                this.DT_RowId = DT_RowId;
            }

            public void setID(double ID) {
                this.ID = ID;
            }

            public void setPlanID(String PlanID) {
                this.PlanID = PlanID;
            }

            public void setStartDate(String StartDate) {
                this.StartDate = StartDate;
            }

            public void setMemberID(String MemberID) {
                this.MemberID = MemberID;
            }

            public void setMemberName(String MemberName) {
                this.MemberName = MemberName;
            }

            public void setIDNumber(String IDNumber) {
                this.IDNumber = IDNumber;
            }

            public void setRealName(String RealName) {
                this.RealName = RealName;
            }

            public void setTakeFee(double TakeFee) {
                this.TakeFee = TakeFee;
            }

            public void setChildFee(double ChildFee) {
                this.ChildFee = ChildFee;
            }

            public void setTotalFee(double TotalFee) {
                this.TotalFee = TotalFee;
            }

            public void setSelfFee(double SelfFee) {
                this.SelfFee = SelfFee;
            }

            public void setParentFee(double ParentFee) {
                this.ParentFee = ParentFee;
            }

            public void setParentID(String ParentID) {
                this.ParentID = ParentID;
            }

            public void setParentName(String ParentName) {
                this.ParentName = ParentName;
            }

            public void setParentRealName(String ParentRealName) {
                this.ParentRealName = ParentRealName;
            }

            public void setParentIDNumber(String ParentIDNumber) {
                this.ParentIDNumber = ParentIDNumber;
            }

            public double getDT_RowId() {
                return DT_RowId;
            }

            public double getID() {
                return ID;
            }

            public String getPlanID() {
                return PlanID;
            }

            public String getStartDate() {
                return StartDate;
            }

            public String getMemberID() {
                return MemberID;
            }

            public String getMemberName() {
                return MemberName;
            }

            public String getIDNumber() {
                return IDNumber;
            }

            public String getRealName() {
                return RealName;
            }

            public double getTakeFee() {
                return TakeFee;
            }

            public double getChildFee() {
                return ChildFee;
            }

            public double getTotalFee() {
                return TotalFee;
            }

            public double getSelfFee() {
                return SelfFee;
            }

            public double getParentFee() {
                return ParentFee;
            }

            public String getParentID() {
                return ParentID;
            }

            public String getParentName() {
                return ParentName;
            }

            public String getParentRealName() {
                return ParentRealName;
            }

            public String getParentIDNumber() {
                return ParentIDNumber;
            }
        }
    }
}