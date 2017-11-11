package com.tiandu.recruit.stud.ui.forget;

import com.tiandu.recruit.stud.api.exception.MessageFactory;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/6 16:18
 * 修改人：chendequnn
 * 修改时间：2017/11/6 16:18
 * 修改备注：
 */
public class ResetPwdPresenter extends ResetPwdContract.Persenter {
    @Override
    public void userResetPwd(String mobile, String password,String authCode) {
        mRxManage.add(mModel.doResetPwd(mobile, password,authCode)
                .subscribe(registerInfo -> {
                    mView.loginSuccess("密码修改成功");
                }, e -> {
                    mView.showError(MessageFactory.getMessage(e));
                })
        );
    }
}