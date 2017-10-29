package com.tiandu.recruit.stud.api;


import com.tiandu.recruit.stud.data.entity.RegisterInfo;
import com.tiandu.recruit.stud.data.entity.Response;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {


    /**
     * 登录
     *
     * @param url
     * @return LoginInfo
     */
    @GET("{value}")
    Call<ResponseBody> doLogin(@Path("value") String url, @Query("UserID") String userName, @Query("Password") String passwd);

    /**
     * 注册
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Call<ResponseBody> doRegister(@Path("value") String url, @Query("actionType") String Reg, @Query("UserID") String UserID, @Query("UserName") String UserName, @Query("Password") String Password, @Query("BindMobile") String BindMobile);

}

