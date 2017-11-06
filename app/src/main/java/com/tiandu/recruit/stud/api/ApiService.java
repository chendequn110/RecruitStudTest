package com.tiandu.recruit.stud.api;


import com.tiandu.recruit.stud.data.entity.RegisterInfo;
import com.tiandu.recruit.stud.data.entity.Response;
import com.tiandu.recruit.stud.data.entity.UserInfo;

import java.util.List;

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
    Observable<Response<List<UserInfo>>> doLogin(@Path("value") String url, @Query("Mobile") String Mobile, @Query("Password") String Password);

    /**
     * 注册
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<RegisterInfo>>> doRegister(@Path("value") String url, @Query("Mobile") String Mobile, @Query("Password") String Password);

    /**
     * 忘记密码
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<RegisterInfo>>> doResetPwd(@Path("value") String url, @Query("Mobile") String Mobile, @Query("Password") String Password);
}

