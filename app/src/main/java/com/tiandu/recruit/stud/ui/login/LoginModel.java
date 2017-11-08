package com.tiandu.recruit.stud.ui.login;

import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.UserInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by cdq on 2017/10/28.
 */


public class LoginModel implements LoginContract.Model {
    @Override
    public Observable<List<UserInfo>> doLogin(String mobile, String password) {
            return Api.getInstance().movieService.doLogin(C.USER_LOGIN_PATH, mobile, password)
                    .compose(RxSchedulers.io_main()).compose(RxSchedulers.sTransformer());
    }
//    @Override
//    public Call<ResponseBody> doLogin(String userId, String uPasswd) {
//        return Api.getInstance().movieService.doLogin(C.USER_LOGIN_PATH,userId,uPasswd);
//    }
}