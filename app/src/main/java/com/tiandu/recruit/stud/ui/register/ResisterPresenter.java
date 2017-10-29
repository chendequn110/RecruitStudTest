package com.tiandu.recruit.stud.ui.register;


import com.google.gson.Gson;
import com.tiandu.recruit.stud.base.utils.Logger;
import com.tiandu.recruit.stud.data.entity.RegisterInfo;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cdq on 2017/10/29.
 */

public class ResisterPresenter extends RegisterContract.Persenter {


    @Override
    public void userRegister(String actionType, String userId, String userName, String password, String bindMobile) {
           mModel.doRegister(actionType,userId,userName,password,bindMobile).enqueue(new Callback<ResponseBody>() {
               @Override
               public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                   String data= null;
                   try {
                       data = response.body().string();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   Gson gson=new Gson();
                   RegisterInfo registerInfo=gson.fromJson(data,RegisterInfo.class);
                   if(registerInfo.isSuccess()){
                       mView.loginSuccess("注册成功");
                   }else{
                       mView.showError(registerInfo.getInfo());
                   }
               }

               @Override
               public void onFailure(Call<ResponseBody> call, Throwable t) {
                   Logger.d("请求失败");
                   mView.showError("请求失败");
               }
           });
    }
}
