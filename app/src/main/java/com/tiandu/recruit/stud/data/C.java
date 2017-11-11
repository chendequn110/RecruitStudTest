package com.tiandu.recruit.stud.data;

/**
 * Created by baixiaokang on 16/4/23.
 */
public class C {
    public static final int HOME_LEARNCAR = 1;
    public static final String LOGIN_ACCOUNT = "login_account";

    /**
     * Unknown network class
     */
    public static final int NETWORK_CLASS_UNKNOWN = 0;

    /**
     * wifi net work
     */
    public static final int NETWORK_WIFI = 1;

    /**
     * "2G" networks
     */
    public static final int NETWORK_CLASS_2_G = 2;

    /**
     * "3G" networks
     */
    public static final int NETWORK_CLASS_3_G = 3;

    /**
     * "4G" networks
     */
    public static final int NETWORK_CLASS_4_G = 4;



    public static final String USER_LOGIN_PATH = "MemberLogin";
    public static final String USER_REGISTER_PATH = "MemberReg";
    public static final String USER_RESETPWD_PATH = "MemberResetPwd";
    public static final String USER_MODIFYPWD_PATH = "MemberChgPwd";
    public static final String USER_USERINFO_PATH = "GetMemberInfo";
    public static final String USER_UPDATAUSERINFO_PATH = "UpdateMemberInfo";
    public static final String USER_GETMEMBERFEE = "MemberFeeList";
    public static final String USER_FEECHILD = "AgentFeeChildList";
    public static final String USER_JOBINFO = "JobList";
    public static final String USER_JOBLISTINFO = "JobDetail";
    public static final String USER_NOTICE = "NoticeList";
    public static final String USER_NOTICELIST = "NoticeDetail";
    public static final String USER_MESSAGECODE = "SendSMSAuthCode";





    public static final String USER_SMS_URL = "getSms";
    public static final String LEARN_TIME_URL = "stuhours";
    public static final String USER_RESET_PASSWD_URL = "update";
    public static final String LEARN_NOTE_URL = "TrainRecord";
    public static final String LEARN_DETAIL_URL = "timedetails";
    public static final String EVALU_LIST_URL = "getComments";

    public static final String ORDER_DETAIL_URL = "orderdetails";
    //解决不升级支付到平台问题
    public static final double ORDER_DETAIL_VERSION = 1.1;
    public static final String HOUR_PRICE_URL = "subjecthoursPrice";
    public static final String TIME_DETAIL_URL = "timedetails";

    public static final String EVENT_LOGIN = "event_login";
    public static final String EVENT_REGISTER= "event_register";

    public static final String USER_PHONE_KEY = "phone";
    public static final String USER_PASSWD_KEY = "password";
    public static final String USER_CODE_KEY = "code";
    public static final String LEARN_PAGE_KEY = "Page";
    public static final String LEARN_SUBJECT_KEY = "Subject";
    public static final String LEARN_TOKEN_KEY = "Token";
    public static final String ORDER_NUM_KEY = "OrderNum";
    public static final String USER_STUNUM_KEY = "stuNum";
    public static final String USER_STUTOKEN_KEY = "token";
    public static final String USER_STUPAGE_KEY = "page";

    //订单评价
    public static final String COMMENT_COACHNUM_KEY = "coachNum";
    public static final String COMMENT_TEACH_SCORE = "teachLevelScore";
    public static final String COMMENT_TEACH_STATE_SCORE = "teachStaeScore";
    public static final String COMMENT_CAR_SCORE = "carStateScore";
    public static final String COMMENT_ORDER_NUM = "orderNum";
    public static final String COMMENT_CONTEXT = "commentContext";
    public static final String COMMENT_CONTENT = "commentContent";

    //阶段评价
    public static final String COMMENT_SUBJECT = "subject";
    public static final String COMMENT_DESCMETHOD = "descMethod";
    public static final String COMMENT_DESCQUALITY = "descQuality";
    public static final String COMMENT_DESCENVIRON1 = "descEnviron1";
    public static final String COMMENT_DESCSERVICE = "descService";
    //技能
    public static final String COMMENT_DESCLEVEL = "descLevel";
    public static final String COMMENT_DESCATTITUDE = "descAttitude";
    public static final String COMMENT_DESCCAR = "descCar";
    public static final String COMMENT_DESCENVIRON2 = "descEnviron2";

    public static final String COMMENT_STAGE_CONTENT = "content";
    public static final String COMMENT_LABELARRAY = "labelarray";

    public static final String WEIXIN_APP_ID_KEY = "wx7e5189ed0516d804";

    public static final String APPOINT_DATE_KEY = "date";
    public static final String APPOINT_INSCODE_KEY = "inscode";
    public static final String APPOINT_COACHNUM_KEY ="coachnum";
    public static final String APPOINT_PART_KEY = "part";
    public static final String APPOINT_ISSIMULATE_KEY = "isSimulate";
    public static final String APPOINT_STUNUM_KEY = "stunum";
    public static final String APPOINT_TOKEN_KEY = "token";

    public static final String STATE_PLANNUM_KEY = "plannum";
    public static final String STATE_STUNUM_KEY = "stunum";
    public static final String STATE_COACHNUM_KEY = "coachNum";
    public static final String STATE_TOKEN_KEY = "token";
    public static final String STATE_INSCODE_KEY = "inscode";


    public static final String COACHLIST_INSCODE_KEY = "inscode";
    public static final String COACHLIST_TOKEN_KEY = "token";
    public static final String COACHLIST_ISSIMULATE_KEY = "issimulate";
    public static final String COACHLIST_STUNUM_KEY = "stunum";
    public static final String COACHLIST_COACHNUM_KEY = "coachnum";

    public static final String RESERVED_STUNUM_KEY = "stunum";
    public static final String RESERVED_TOKEN_KEY = "token";
    public static final String RESERVED_INSCODE_KEY = "inscode";
    public static final String RESERVED_PAGE_KEY = "page";

    public static final String DETAILS_STUNUM_KEY = "stunum";
    public static final String DETAILS_TOKEN_KEY = "token";
    public static final String DETAILS_SIMUNUM_KEY = "simunum";
    public static final String DETAILS_PLANNUM_KEY = "plannum";

    public static final String CANCEL_STUNUM_KEY = "stunum";
    public static final String CANCEL_TOKEN_KEY = "token";
    public static final String CANCEL_REASON_KEY = "reason";
    public static final String CANCEL_SIMUNUM_KEY = "simunum";
    public static final String CANCEL_PLANNUM_KEY = "plannum";


    public static final String APPOINT_DETAIL_INFO = "appoint_detail_info";
    public static final String APPOINT_DETAIL = "appoint_detail";
    public static final String APPOINT_DEFEAT_PUNISH = "appoint_defeat_punish";


    public static final String COACHLIST_KEY = "form_coach_list";
    public static final String PAY_PAYTYPE_KEY = "paytype";
    public static final String PAY_PAYNUM_KEY = "number";
    public static final String PAY_PAORDERNUM_KEY = "ordernum";
    public static final String PAY_STATE = "state";

    public static final String PAY_TOSCHOOL_URL = "paytoschool";
    public static final String NET_TEACH_URL = "net_teach_url";

    //TODO
    public static final String NET_TEST_URL = "http://121.40.77.233:6088/questclub/login/index";  //网络教学
    public static final String NET_URL = "http://qz.jiaqu365.com:6088/questclub/login/index";  //网络教学
    //public static final String NET_TEST_URL = "http://120.26.141.169:8080/questclub/login/index";  //网络教学

    public static final String ISTRAIN = "istrain";
    public static final String ISTRAIN_ORDERINFO = "istrain_orderinfo";
    public static final String ISTRAIN_NOTETRAIN = "istrain_notetrain";


    public static final String FACE_SIMITATE_STUDENT = "simustudent*my%studentloginin#";
    public static final String FACE_SIMITATE_OUT_STUDENT = "simustudent*my%studentloginout#";
    public static final String FACE_SIMITATE_OUT_INIT_STUDENT = "simustudent*my%studentlogininit#";
    public static final String FACE_COATH_NUM = "coach_num";
    public static final String FACE_STUDENT_NUM = "student_num";
    public static final String FACE_APPLY_NUM = "apply_num";
    public static final String FACE_LONGITUDE = "longitude";
    public static final String FACE_LATITUDE  = "latitude";
    public static final String FACE_TYPE  = "type";
    public static final String FACE_DT  = "dt";

    public static final String DATABASE_NAME = "signDate";
    public static final String NOT_FROM_ALLEVALUATE = "not_from_allevaluate";
    public static final int MAIN_TO_EVALUATE = 0;
    public static final int ALL_EVALUATE_THEORY = 1;
    public static final int ALL_EVALUATE_SKILL = 2;

    public static final String QUERYSTAGETRAIN = "queryStagetrain";
    public static final String QUERYMYCOMMENT = "queryMyComment";
//登出
    public static final String USER_OUT = "user_out";
    public static final int USER_OUT_STATUS = 1;//登出

}
