package com.tiandu.recruit.stud.ui.login;

import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.LoginInfo;
import com.tiandu.recruit.stud.data.entity.Response;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by cdq on 2017/10/28.
 */


public class LoginModel implements LoginContract.Model {
    @Override
    public Call<ResponseBody> doLogin(String userId, String uPasswd) {
        return Api.getInstance().movieService.doLogin(C.USER_LOGIN_PATH,userId,uPasswd);
    }


//    @Override
//    public Call<LoginInfo> doLogin(String userId, String uPasswd) {
//        return Api.getInstance().movieService.userBusiness(C.USER_LOGIN_PATH, userId, uPasswd);
//
//    }
}