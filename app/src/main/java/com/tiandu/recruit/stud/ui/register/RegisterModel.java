package com.tiandu.recruit.stud.ui.register;

import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.RegisterInfo;


import rx.Observable;

/**
 * Created by cdq on 2017/10/29.
 */

public class RegisterModel implements RegisterContract.Model {


    @Override
    public Observable<RegisterInfo> doRegister(String mobile, String password) {
        return Api.getInstance().movieService.doRegister(C.USER_REGISTER_PATH,mobile,password)
                .compose(RxSchedulers.io_main()).compose(RxSchedulers.sTransformer());
    }
}
