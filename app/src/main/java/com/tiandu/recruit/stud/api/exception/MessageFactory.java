package com.tiandu.recruit.stud.api.exception;



import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.base.App;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by Jerome on 2016/10/14.
 * Email :jeromekai8@gmail.com
 */

public class MessageFactory {

    private MessageFactory() {

    }

    public static String getMessage(Throwable e){
        String message = null;
        if (e instanceof UnknownHostException) {
            message = App.getAppContext().getString(R.string.network_conection_exce);
        } else if (e instanceof ConnectException) {
            message = "网络连接失败，请检查网络";
        } else if (e instanceof SocketTimeoutException) {
            message = App.getAppContext().getString(R.string.network_timeout_exce);
        } else if (e instanceof SocketException) {
            message = App.getAppResources().getString(R.string.req_exce_msg);
        } else if (e instanceof ResponseException) {
            message = e.getMessage();
        } else if (e instanceof HttpException) {
            message = "请求失败1";
        } else {
            message = "连接失败2";
        }
        return message;
    }
}
