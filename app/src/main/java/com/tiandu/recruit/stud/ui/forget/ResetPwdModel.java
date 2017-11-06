package com.tiandu.recruit.stud.ui.forget;

import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.RegisterInfo;

import java.util.List;

import rx.Observable;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/6 16:12
 * 修改人：chendequnn
 * 修改时间：2017/11/6 16:12
 * 修改备注：
 */
public class ResetPwdModel implements ResetPwdContract.Model {
    @Override
    public Observable<List<RegisterInfo>> doResetPwd(String mobile, String password) {
        return Api.getInstance().movieService.doRegister(C.USER_RESETPWD_PATH,mobile,password)
                .compose(RxSchedulers.io_main()).compose(RxSchedulers.sTransformer());
    }
}