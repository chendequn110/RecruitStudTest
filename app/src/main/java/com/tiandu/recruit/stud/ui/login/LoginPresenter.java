package com.tiandu.recruit.stud.ui.login;


import android.util.Log;

import com.google.gson.Gson;
import com.tiandu.recruit.stud.api.exception.MessageFactory;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.data.entity.UserInfo;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.functions.Action1;

/**
 * Created by Jerome on 16/9/17.
 * Email :jeromekai8@gmail.com
 */
public class LoginPresenter extends LoginContract.Persenter {


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void userLogin(String mobole, String password) {
        mRxManage.add(mModel.doLogin(mobole, password)
                .subscribe(new Action1<List<UserInfo.DataBean>>() {
                    @Override
                    public void call(List<UserInfo.DataBean> userInfos) {
                        SpUtil.setToken(userInfos.get(0).getToken());
                        SpUtil.setMemberID(userInfos.get(0).getMemberID());
                        mView.loginSuccess("登录成功");
                    }
                }, e -> {
                    mView.showError(MessageFactory.getMessage(e));
                })
        );
    }


//    @Override
//    public void userLogin(String uName, String uPasswd) {
//        mModel.doLogin(uName,uPasswd).enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                String data= null;
//                try {
//                    data = response.body().string();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Gson gson=new Gson();
//                LoginInfo loginInfo=gson.fromJson(data,LoginInfo.class);
//                if(loginInfo.isSuccess()){
//                    mView.loginSuccess("登录成功");
//                }else{
//                    mView.showError(loginInfo.getInfo());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d("cdq", "onFailure: "+t.getMessage());
//                mView.showError("请求失败");
//
//            }
//        });
//        Api.getInstance().movieService.userBusiness(C.USER_LOGIN_PATH,uName,uPasswd).enqueue(new Callback<ResponseBody>() {
//
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                String data= null;
//                try {
//                    data = response.body().string();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Gson gson=new Gson();
//                LoginInfo loginInfo=gson.fromJson(data,LoginInfo.class);
//                if(loginInfo.isSuccess()){
//                    mView.loginSuccess("登录成功");
//                }else{
//                    mView.showError(loginInfo.getInfo());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d("cdq", "onFailure: "+t.getMessage());
//                mView.showError("请求失败");
//
//            }
//        });
//        Api.getInstance().movieService.userBusiness(C.USER_LOGIN_PATH, uName, uPasswd).enqueue(new Callback<LoginInfo>() {
//            @Override
//            public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<LoginInfo> call, Throwable t) {
//
//            }
//        });
//        mRxManage.add(mModel.doLogin(uName, uPasswd).subscribe(new Action1<LoginInfo>() {
//                    @Override
//                    public void call(LoginInfo loginInfo) {
//                        if (loginInfo.isSuccess()) {
//                            mView.loginSuccess("登录成功");
//                        } else {
//                            mView.showError(loginInfo.getInfo());
//                        }
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable e) {
//                        mView.showError(MessageFactory.getMessage(e));
//                    }
//                })
//                .subscribe(loginInfo -> {
////                    SpUtil.saveUser(loginInfo);
////                    mRxManage.post(C.EVENT_LOGIN, loginInfo);
//                    mView.loginSuccess("登录成功");
//                }, e -> {
//                    mView.showError(MessageFactory.getMessage(e));
//                }
//                )
//        );
//    }

}
