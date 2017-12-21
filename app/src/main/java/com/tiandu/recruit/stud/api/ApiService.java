package com.tiandu.recruit.stud.api;


import com.tiandu.recruit.stud.data.entity.AdvertInfo;
import com.tiandu.recruit.stud.data.entity.ChildMenberInfo;
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

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
     * 版本检测
     */
    @GET("{value}")
    Observable<Response<List<VersionInfo.DataBean>>> getVersionInfo(@Path("value") String url, @Query("MemberID") String MemberID, @Query("Token") String Token);

    /**
     * 下载app
     */
    @GET("{value}")
    Call<ResponseBody> appDownload(@Path("value") String url, @Query("MemberID") String MemberID, @Query("VersionNo") String VersionNo, @Query("Token") String Token);


    /**
     * 登录
     *
     * @param url
     * @return LoginInfo
     */
    @GET("{value}")
    Observable<Response<List<UserInfo.DataBean>>> doLogin(@Path("value") String url, @Query("Mobile") String Mobile, @Query("Password") String Password);

    /**
     * 注册
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<RegisterInfo>>> doRegister(@Path("value") String url, @Query("Mobile") String Mobile, @Query("Password") String Password, @Query("AuthCode") String AuthCode);

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
    Observable<Response<List<RegisterInfo>>> doModifyPwd(@Path("value") String url, @Query("MemberID") String MemberID, @Query("Password") String Password,@Query("NewPassword") String NewPassword,@Query("Token") String Token);

    /**
     * 获取会员资料
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<MeInfo>>> getUserInfo(@Path("value") String url, @Query("MemberID") String MemberID, @Query("Token") String Token);

    /**
     * 更新会员资料
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<MeInfo>>> upDataUserInfo(@Path("value") String url, @Query("actionType") String actionType,@Query("MemberID") String MemberID,@Query("MemberName") String MemberName, @Query("BindMobile") String BindMobile,@Query("BindEmail") String BindEmail,@Query("BindWechat") String BindWechat,@Query("BindQQ") String BindQQ,@Query("IDNumber") String IDNumber,@Query("RealName") String RealName,@Query("Gender") String Gender,@Query("BirthDate") String BirthDate,@Query("Province") String Province,@Query("City") String City,@Query("ParentID") String ParentID,@Query("Token") String Token);

    /**
     * 获取代理费
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<MemberFeeInfo>>> getMemberFee(@Path("value") String url, @Query("MemberID") String MemberID, @Query("Token") String Token,@Query("iPage") String iPage);

    /**
     * 获取分支金额
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<FeeChildInfo>>> getFeeChild(@Path("value") String url, @Query("MemberID") String MemberID, @Query("PlanID") String PlanID,@Query("Token") String Token,@Query("iPage") String iPage);

    /**
     * 获取招聘信息
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<JobInfo>>> getJobInfo(@Path("value") String url, @Query("JobType") String JobType,@Query("MemberID") String MemberID, @Query("Token") String Token,@Query("iPage") String iPage);
    /**
     * 获取招聘详情
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<JobListInfo>>> getJobListInfo(@Path("value") String url, @Query("ID") int ID,@Query("MemberID") String MemberID, @Query("Token") String Token);
    /**
     * 获取通知信息
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<NoticeInfo>>> getNoticeInfo(@Path("value") String url,  @Query("MemberID") String MemberID,@Query("Token") String Token,@Query("iPage") String iPage);

    /**
     * 获取通知详情
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<NoticeListInfo>>> getNoticeListInfo(@Path("value") String url,@Query("ID") int ID,@Query("MemberID") String MemberID, @Query("Token") String Token);
    /**
     * 获取短信验证码
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<RegisterInfo>>> getSMSAuthCode(@Path("value") String url,@Query("AuthType") String AuthType, @Query("Mobile") String Mobile);

    /**
     * 绑定手机
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<RegisterInfo>>> bindPhone(@Path("value") String url, @Query("MemberID") String MemberID, @Query("Mobile") String Mobile, @Query("AuthCode") String AuthCode,@Query("Token") String Token);

    /**
     * 绑定邮箱
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<RegisterInfo>>> bindMail(@Path("value") String url, @Query("MemberID") String MemberID, @Query("Email") String Email,@Query("Token") String Token);
    /**
     * 更新会员资料
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<MeInfo>>> upDataUserInfoBase(@Path("value") String url,@Query("MemberID") String MemberID,@Query("MemberName") String MemberName, @Query("Gender") String Gender,@Query("BirthDate") String BirthDate,@Query("Province") String Province,@Query("City") String City,@Query("Token") String Token);
    /**
     * 实名认证
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<MeInfo>>> upDataUserRealInfo(@Path("value") String url,@Query("MemberID") String MemberID,@Query("RealName") String RealName, @Query("IDNumber") String IDNumber,@Query("BankName") String BankName,@Query("BankAccount") String BankAccount,@Query("Token") String Token);

    /**
     * 下级会员
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<ChildMenberInfo>>> getChildMember(@Path("value") String url, @Query("MemberID") String MemberID, @Query("Token") String Token);

    /**
     * 获取广告
     *
     * @param url
     * @return Response
     */
    @GET("{value}")
    Observable<Response<List<AdvertInfo>>> getAdv(@Path("value") String url, @Query("MemberID") String MemberID, @Query("Token") String Token);
    /**
     * 修改头像
     *
     * @param url
     * @return Response
     */
    @Multipart
    @POST("{value}")
    Observable<Response> updateHeadImage(@Path("value") String url, @Query("MemberID") String MemberID, @Query("Token") String Token,@Part MultipartBody.Part part);

}

