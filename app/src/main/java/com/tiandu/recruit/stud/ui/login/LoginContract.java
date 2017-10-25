package com.tiandu.recruit.stud.ui.login;


import android.icu.util.VersionInfo;

import com.tiandu.recruit.stud.base.BaseModel;
import com.tiandu.recruit.stud.base.BasePresenter;
import com.tiandu.recruit.stud.base.BaseView;

/**
 * Created by Jerome on 16/9/17.
 * Email :jeromekai8@gmail.com
 */
public interface LoginContract {


    interface Model extends BaseModel {

//        Observable<VersionInfo> getVersion(int version);
//
//        Observable<UserInfo> doLogin(String uName, String uPasswd, String registrationID);
    }

    interface View extends BaseView {

        void loginSuccess(String message);

        void showError(String message);

        void showVersion(VersionInfo info);
    }

    abstract class Persenter extends BasePresenter<Model, View> {

        @Override
        public void onStart() {

        }

        public abstract void getVersion(int version);

        public abstract void userLogin(String uName, String uPasswd, String registrationID);
    }


}
