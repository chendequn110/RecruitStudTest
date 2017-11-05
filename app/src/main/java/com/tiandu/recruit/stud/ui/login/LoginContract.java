package com.tiandu.recruit.stud.ui.login;


import android.icu.util.VersionInfo;

import com.tiandu.recruit.stud.base.BaseModel;
import com.tiandu.recruit.stud.base.BasePresenter;
import com.tiandu.recruit.stud.base.BaseView;
import com.tiandu.recruit.stud.data.entity.LoginInfo;
import com.tiandu.recruit.stud.data.entity.UserInfo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import rx.Observable;

/**
 * Created by Jerome on 16/9/17.
 * Email :jeromekai8@gmail.com
 */
public interface LoginContract {


    interface Model extends BaseModel {

        Observable<List<UserInfo>> doLogin(String mobile, String password);

//        Call<ResponseBody> doLogin(String userId, String uPasswd);
    }

    interface View extends BaseView {

        void loginSuccess(String message);

        void showError(String message);

//        void showVersion(VersionInfo info);
    }

    abstract class Persenter extends BasePresenter<Model, View> {

        @Override
        public void onStart() {

        }

//        public abstract void getVersion(int version);

        public abstract void userLogin(String mobole, String password);
    }


}
