package com.tiandu.recruit.stud.ui.register;

import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.RegisterInfo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import rx.Observable;

/**
 * Created by cdq on 2017/10/29.
 */

public class RegisterModel implements RegisterContract.Model {
    @Override
    public Call<ResponseBody>  doRegister(String reg, String userId, String userName, String password, String bindMobile) {
        return Api.getInstance().movieService.doRegister(C.USER_REGISTER_PATH,reg,userId,userName,password,bindMobile);
    }
}
