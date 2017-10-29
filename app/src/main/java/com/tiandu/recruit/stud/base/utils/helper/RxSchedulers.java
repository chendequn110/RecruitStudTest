package com.tiandu.recruit.stud.base.utils.helper;

import android.accounts.NetworkErrorException;


import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.api.exception.ResponseException;
import com.tiandu.recruit.stud.base.App;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.data.entity.Response;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxSchedulers {

    public static <T> Observable.Transformer<Response<T>, T> sTransformer() {
        return tObservable -> tObservable.flatMap(new Func1<Response<T>, Observable<T>>() {
            @Override
            public Observable<T> call(Response<T> tResponse) {
                if (tResponse.getData()!= null){
                    return respResult(tResponse);
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
            return null;
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
//    public static Observable.Transformer<Response<String>,String> trans() {
//        return strObservable -> strObservable.flatMap(new Func1<Response<String>, Observable<String>>() {
//
//            @Override
//            public Observable<String> call(Response<String> tResponse) {
//                if (tResponse != null){
//                    switch (tResponse.getFlag()){
//                        case 0:
//                        case -1:
//                        case -2:
//                        case -3:
//                            return Observable.error(new ResponseException(tResponse.getMessage()));
//                        default:
//                            return Observable.just(tResponse.getContent());
//                    }
//                } else {
//                    return Observable.error(new NetworkErrorException(App.getAppResources().getString(R.string.req_exce_msg)));
//                }
//            }
//        });
//    }
}
