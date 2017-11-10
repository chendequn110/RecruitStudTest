package com.tiandu.recruit.stud.api;


import com.tiandu.recruit.stud.data.entity.FeeChildInfo;
import com.tiandu.recruit.stud.data.entity.JobInfo;
import com.tiandu.recruit.stud.data.entity.JobListInfo;
import com.tiandu.recruit.stud.data.entity.MeInfo;
import com.tiandu.recruit.stud.data.entity.MemberFeeInfo;
import com.tiandu.recruit.stud.data.entity.NoticeInfo;
import com.tiandu.recruit.stud.data.entity.NoticeListInfo;
import com.tiandu.recruit.stud.data.entity.RegisterInfo;
import com.tiandu.recruit.stud.data.entity.Response;
import com.tiandu.recruit.stud.data.entity.UserInfo;
import com.tiandu.recruit.stud.data.entity.VersionInfo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {


    /**
     * 版本检测
     */
    @GET("{value}")
    Observable<Response<VersionInfo>> getVersionInfo(@Path("value") String url, @Query("type") int type);


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

    /**
     * 修改密码
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<RegisterInfo>>> doModifyPwd(@Path("value") String url, @Query("Mobile") String Mobile, @Query("Password") String Password,@Query("NewPassword") String NewPassword,@Query("Token") String Token);

    /**
     * 获取会员资料
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<MeInfo>>> getUserInfo(@Path("value") String url, @Query("Mobile") String Mobile, @Query("Token") String Token);

    /**
     * 更新会员资料
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<MeInfo>>> upDataUserInfo(@Path("value") String url, @Query("actionType") String actionType, @Query("Token") String Token);

    /**
     * 获取代理费
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<MemberFeeInfo>>> getMemberFee(@Path("value") String url, @Query("MemberID") String MemberID, @Query("Token") String Token);

    /**
     * 获取分支金额
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<FeeChildInfo>>> getFeeChild(@Path("value") String url, @Query("MemberID") String MemberID, @Query("PlanID") String PlanID,@Query("Token") String Token);

    /**
     * 获取招聘信息
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<JobInfo>>> getJobInfo(@Path("value") String url, @Query("Token") String Token);
    /**
     * 获取招聘详情
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<JobListInfo>>> getJobListInfo(@Path("value") String url, @Query("ID") int ID, @Query("Token") String Token);
    /**
     * 获取通知信息
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<NoticeInfo>>> getNoticeInfo(@Path("value") String url, @Query("Token") String Token);

    /**
     * 获取通知详情
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<NoticeListInfo>>> getNoticeListInfo(@Path("value") String url,@Query("ID") int ID, @Query("Token") String Token);
}

