package com.tiandu.recruit.stud.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    public static final String X_LC_Id = "";
    public static final String X_LC_Key = "";


    /**
     * 正式库 false,图片url也要修改
     */
    //public static final String API_PRODUCT_URL = "http://121.40.77.233/studentAPI/";


    public static final String API_PRODUCT_URL = "http://qz.jiaqu365.com:6088/studentAPI/";
    public static final String API_PRODUCT_LOGIN_URL = "http://qz.jiaqu365.com:6088/jishi/simustudent/";        //签到接口
    public static final String API_PRODUCT_ZHES_URL = "http://qz.jiaqu365.com:6088/";       //浙商银行

    public static final String IMG_URL ="http://qz.jiaqu365.com:6088/jishi_image/manage/student/";
    public static final String IMG_COACH_URL ="http://qz.jiaqu365.com:6088/jishi_image/manage/coach/";
    public static final String IMG_SIMCOACH_URL ="http://qz.jiaqu365.com:6088/jishi_image/manage/simcoach/";

//    public static final String API_DEV_URL = "http://121.40.77.233:6088/studentAPI/";
//    public static final String API_DEV_LOGIN_URL = "http://121.40.77.233:6088/jishi/simustudent/";        //签到接口+
//    public static final String API_DEV_ZHES_URL = "http://121.40.77.233:6088/";       //浙商银行


    /**
     * 测试库 true
     */

    public static final String API_DEV_URL = "http://121.40.77.233/studentAPI/";
    public static final String API_DEV_LOGIN_URL = "http://121.40.77.233/jishi/simustudent/";
    public static final String API_DEV_ZHES_URL = "http://121.40.77.233/";

//    public static final String API_DEV_URL = "http://192.168.1.96:8080/studentAPI/";
//    public static final String API_DEV_LOGIN_URL = "http://192.168.1.96:8080/jishi/simustudent/";
//    public static final String API_DEV_ZHES_URL = "http://192.168.1.96:8080/";


//    public static final String IMG_URL ="http://192.168.1.96:8080/jishi_image/manage/student/";
//    public static final String IMG_COACH_URL ="http://120.26.141.169:8080/jishi_image/manage/coach/";
//    public static final String IMG_SIMCOACH_URL ="http://120.26.141.169:8080/jishi_image/manage/simcoach/";

    public static final boolean IS_DEV = false;

    public Retrofit retrofit;
    public Retrofit retrofit2;
    public Retrofit retrofit3;
//    public ApiService movieService;
//    public ApiService movieService2;
//    public ApiService movieService3;

//    //构造方法私有
//    private Api() {
//        //Header拦截器
//        Interceptor mInterceptor = (chain) -> chain.proceed(chain.request().newBuilder()
//                .addHeader("X-LC-Id", X_LC_Id)
//                .addHeader("X-LC-Key", X_LC_Key)
//                .addHeader("Content-Type", "application/json")
//                .build());
//
//        //日志拦截器
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        if (IS_DEV) {
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        } else {
//            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
//        }
//
//        //缓存
//        File cacheFile = new File(App.getAppContext().getCacheDir(), "cache");
//        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
//
//        //http
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .readTimeout(30*1000, TimeUnit.MILLISECONDS)
//                .connectTimeout(5*1000, TimeUnit.MILLISECONDS)
////                .addInterceptor(mInterceptor)
//                .addInterceptor(interceptor)
//                .addNetworkInterceptor(new HttpCacheInterceptor())
//                .cache(cache)
//                .build();
//
//        //https
//        OkHttpClient okHttpsClient = new OkHttpClient.Builder()
//                .readTimeout(30*1000, TimeUnit.MILLISECONDS)
//                .connectTimeout(30*1000, TimeUnit.MILLISECONDS)
//                .addInterceptor(mInterceptor)
//                .addInterceptor(interceptor)
//                .sslSocketFactory(createSSLSocketFactory(),new TrustAllManager())
//                .hostnameVerifier((hostname, session) -> true) // 信任所有主机名
//                .cookieJar(new CookieJar() {
//                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
//
//                    @Override
//                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                        cookieStore.put(HttpUrl.parse(url.host()), cookies);
//                    }
//
//                    @Override
//                    public List<Cookie> loadForRequest(HttpUrl url) {
//                        List<Cookie> cookies = cookieStore.get(HttpUrl.parse(url.host()));
//                        return cookies != null ? cookies : new ArrayList<>();
//                    }
//                }).build();
//
//
//        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
//
//        retrofit = new Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl(IS_DEV ? API_DEV_URL : API_PRODUCT_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        movieService = retrofit.create(ApiService.class);
//
//        retrofit2 = new Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl(IS_DEV ? API_DEV_LOGIN_URL : API_PRODUCT_LOGIN_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        movieService2 = retrofit2.create(ApiService.class);
//
//
//        retrofit3 = new Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl(IS_DEV ? API_DEV_ZHES_URL : API_PRODUCT_ZHES_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        movieService3 = retrofit3.create(ApiService.class);
//    }
//
//    //在访问HttpMethods时创建单例
//    private static class SingletonHolder {
//        private static final Api INSTANCE = new Api();
//    }
//
//    //获取单例
//    public static Api getInstance() {
//        return SingletonHolder.INSTANCE;
//    }
//
//    class HttpCacheInterceptor implements Interceptor {
//
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//            if (!NetWorkUtil.isNetConnected(App.getAppContext())) {
//                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
//                Log.d("Okhttp", "no network");
//            }
//
//            Response originalResponse = chain.proceed(request);
//            if (NetWorkUtil.isNetConnected(App.getAppContext())) {
//                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
//                String cacheControl = request.cacheControl().toString();
//                return originalResponse.newBuilder()
//                        .header("Cache-Control", cacheControl)
//                        .removeHeader("Pragma")
//                        .build();
//            } else {
//                return originalResponse.newBuilder()
//                        .header("Cache-Control", "no-cache")
//                        .removeHeader("Pragma")
//                        .build();
//            }
//        }
//    }
//
//    private static SSLSocketFactory createSSLSocketFactory() {
//        SSLSocketFactory sSLSocketFactory = null;
//
//        try {
//            SSLContext sc = SSLContext.getInstance("TLS");
//            sc.init(null, new TrustManager[]{new TrustAllManager()},
//                    new SecureRandom());
//            sSLSocketFactory = sc.getSocketFactory();
//        } catch (Exception e) {
//        }
//
//        return sSLSocketFactory;
//    }
//
//    private static class TrustAllManager implements X509TrustManager {
//
//        @Override
//        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//
//        }
//
//        @Override
//        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//
//        }
//
//        @Override
//        public X509Certificate[] getAcceptedIssuers() {
//            return new X509Certificate[0];
//        }
//    }
}