package com.tiandu.recruit.stud.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.data.entity.UserInfo;


public class SpUtil {

    private static final String NIGHT_KEY = "isNight";
    private static final String USER_INFO_KEY = "user_info_key";
    private static final String IS_NON_PAY = "isNonPay";
    private static final String COMM_PAGE_KEY = "page";
    private static final String ORDER_NUM = "order_num";
    private static final String USER_LOGINED_KEY = "isLogin";
    private static final String USER_NAME_KEY = "userName";
    private static final String STUDENT_LOGIN_NUM = "student_login_num";
    private static final String COACH_LOGIN_NUM  = "coach_login_num";

    private static SharedPreferences prefs;

    public static void init(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean isNight() {
        return prefs.getBoolean(NIGHT_KEY, false);
    }

    public static void setNight(Context context, boolean isNight) {
        prefs.edit().putBoolean(NIGHT_KEY, isNight).commit();
        if (context instanceof BaseActivity)
            ((BaseActivity) context).reload();
    }

    public static boolean isNonPay() {
        return prefs.getBoolean(IS_NON_PAY,false);
    }

    public static void setIsNonPay(boolean isPay) {
        prefs.edit().putBoolean(IS_NON_PAY,isPay).commit();
    }

    public static int getPage() {
        return prefs.getInt(COMM_PAGE_KEY,-1);
    }

    public static void setPage(int page) {
        prefs.edit().putInt(COMM_PAGE_KEY, page).commit();
    }

    public static void setOrderNum(String orderNum){prefs.edit().putString(ORDER_NUM,orderNum).commit();}

    public static String getOrderNum(){return prefs.getString(ORDER_NUM,"");}

    public static void isLogined(boolean b) {
        prefs.edit().putBoolean(USER_LOGINED_KEY,b).commit();
    }

    public static boolean isLogined() {
        return prefs.getBoolean(USER_LOGINED_KEY,false);
    }

    public static String getAccount() {
        return prefs.getString(USER_NAME_KEY,"");
    }

    public static void setAccount(String account) {
        prefs.edit().putString(USER_NAME_KEY,account).commit();
    }

    public static UserInfo getUser() {
        return new Gson().fromJson(prefs.getString(USER_INFO_KEY,""),UserInfo.class);
    }

    public static void saveUser(UserInfo model) {
        prefs.edit().putString(USER_INFO_KEY,new Gson().toJson(model)).commit();
    }

    public static void clearAll() {
        prefs.edit().clear().commit();
    }

    public static void setStudentloginNum(String num) {
        prefs.edit().putString(STUDENT_LOGIN_NUM,num).commit();
    }

    public static String getStudentloginNum() {
        return prefs.getString(STUDENT_LOGIN_NUM,"");
    }

    public static void setcoachLoginNum(String num) {
        prefs.edit().putString(COACH_LOGIN_NUM,num).commit();
    }

    public static String getcoachLoginNum() {
        return prefs.getString(COACH_LOGIN_NUM,"");
    }


}
