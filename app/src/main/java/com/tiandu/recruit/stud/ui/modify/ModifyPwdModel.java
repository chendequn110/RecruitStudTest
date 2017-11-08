package com.tiandu.recruit.stud.ui.modify;

import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.RegisterInfo;

import java.util.List;

import rx.Observable;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/8 14:19
 * 修改人：chendequnn
 * 修改时间：2017/11/8 14:19
 * 修改备注：
 */
public class ModifyPwdModel implements ModifyPwdContract.Model {

    @Override
    public Observable<List<RegisterInfo>> doModifyPwd(String mobile, String password, String newPassword, String token) {
        return Api.getInstance().movieService.doModifyPwd(C.USER_MODIFYPWD_PATH,mobile,password,newPassword,SpUtil.getToken())
                .compose(RxSchedulers.io_main()).compose(RxSchedulers.sTransformer());
    }
}