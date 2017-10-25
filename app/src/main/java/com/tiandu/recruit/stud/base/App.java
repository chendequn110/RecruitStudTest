package com.tiandu.recruit.stud.base;

import android.app.Application;

import com.mob.MobSDK;
import com.tiandu.recruit.stud.base.utils.SpUtil;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/10/23 18:10
 * 修改人：chendequnn
 * 修改时间：2017/10/23 18:10
 * 修改备注：
 */
public class App extends Application {
    private static App mApp;
    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        SpUtil.init(this);
        MobSDK.init(this,"21b8bf737736e","0fde79dbdd1c144c4a2398b78e6767ec");

//        JPushInterface.setDebugMode(false);
//        JPushInterface.init(this);

    }
    public static App getAppContext() {
        return mApp;
    }
}