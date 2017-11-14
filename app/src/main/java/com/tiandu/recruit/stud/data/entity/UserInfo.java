package com.tiandu.recruit.stud.data.entity;

import java.io.Serializable;

/**
 * Created by Jerome on 16/9/18.
 * Email :jeromekai8@gmail.com
 */
public class UserInfo implements Serializable {


    public static class DataBean {
        /**
         * Token : A6BD43A5EB8262B36768E48944DED00E
         * MemberID : M000007
         */

        private String Token;
        private String MemberID;

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }

        public String getMemberID() {
            return MemberID;
        }

        public void setMemberID(String MemberID) {
            this.MemberID = MemberID;
        }
    }
}
