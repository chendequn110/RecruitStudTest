package com.tiandu.recruit.stud.ui.modify;

import com.tiandu.recruit.stud.api.exception.MessageFactory;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/8 14:30
 * 修改人：chendequnn
 * 修改时间：2017/11/8 14:30
 * 修改备注：
 */
public class ModifyPwdPresenter extends ModifyPwdContract.Persenter {
    @Override
    public void userModifyPwd(String mobile, String password, String newPassword, String token) {
        mRxManage.add(mModel.doModifyPwd(mobile, password,newPassword,token)
                .subscribe(registerInfo -> {
                    mView.loginSuccess("密码修改成功");
                }, e -> {
                    mView.showError(MessageFactory.getMessage(e));
                })
        );
    }
}