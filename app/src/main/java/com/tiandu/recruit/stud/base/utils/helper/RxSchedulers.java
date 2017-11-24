package com.tiandu.recruit.stud.base.utils.helper;

import android.accounts.NetworkErrorException;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.api.exception.ResponseException;
import com.tiandu.recruit.stud.base.App;
import com.tiandu.recruit.stud.base.utils.Logger;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.data.entity.Response;
import com.tiandu.recruit.stud.data.event.LoginEvent;

import org.greenrobot.eventbus.EventBus;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxSchedulers {

    public static <T> Observable.Transformer<Response<T>, T> sTransformer() {
        return tObservable -> tObservable.flatMap(new Func1<Response<T>, Observable<T>>() {
            @Override
            public Observable<T> call(Response<T> tResponse) {
                if (tResponse != null){
                    if(tResponse.getMessage().equals("token验证失败")){
                        tResponse.setMessage("您的账号\t" + SpUtil.getAccount() +"\n在其他手机登录");
                        EventBus.getDefault().post(new LoginEvent(-1));
                    }
                    if (tResponse.isCode()){
                        return respResult(tResponse);
                    }else {
//                        正常情况下的错误信息
                            return Observable.error(new ResponseException(tResponse.getMessage()));
                    }
//                    switch (tResponse.isCode()){
//                        case 0:
//                        case -1:
//                            //请求失败后，仍然返回数据（特殊情况）
//                            if (tResponse.getContent() != null) return Observable.just(tResponse.getContent());
//                        case -2:
//                        case -3:
//                        case -4:
//                        case -5:
//                            //正常情况下的错误信息
//                            return Observable.error(new ResponseException(tResponse.getMessage()));
//                        default:
//                            SpUtil.setPage(tResponse.getPage());
//                            return respResult(tResponse);
//                    }
                } else {
                    return Observable.error(new NetworkErrorException(App.getAppResources().getString(R.string.req_exce_msg)));
                }
            }
        });
    }

    private static <T> Observable<T> respResult(Response<T> tResponse) {
        if (tResponse.getData() != null) {
            return Observable.just(tResponse.getData());
        } else {
            return Observable.just(tResponse.getData());
        }
    }

    public static <T> Observable.Transformer<T, T> io_main() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    /**
     * 处理不规则相应
     * {
     * "flag": 1,
     * "message": "xxxx",
     * "content": ""
     * }
     * @return
     */
    public static Observable.Transformer<Response<String>,String> trans() {
        return strObservable -> strObservable.flatMap(new Func1<Response<String>, Observable<String>>() {

            @Override
            public Observable<String> call(Response<String> tResponse) {
                if (tResponse != null){
                    if(tResponse.isCode()){
                        return Observable.just(tResponse.getData());
                    }else{
                        return Observable.just(tResponse.getMessage());
                    }
//                    switch (tResponse.getFlag()){
//                        case 0:
//                        case -1:
//                        case -2:
//                        case -3:
//                            return Observable.error(new ResponseException(tResponse.getMessage()));
//                        default:
//                            return Observable.just(tResponse.getContent());
//                    }
                } else {
                    return Observable.error(new NetworkErrorException(App.getAppResources().getString(R.string.req_exce_msg)));
                }
            }
        });
    }
}
