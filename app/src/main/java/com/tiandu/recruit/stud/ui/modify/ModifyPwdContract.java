package com.tiandu.recruit.stud.ui.modify;

import com.tiandu.recruit.stud.base.BaseModel;
import com.tiandu.recruit.stud.base.BasePresenter;
import com.tiandu.recruit.stud.base.BaseView;
import com.tiandu.recruit.stud.data.entity.RegisterInfo;

import java.util.List;

import rx.Observable;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/8 14:14
 * 修改人：chendequnn
 * 修改时间：2017/11/8 14:14
 * 修改备注：
 */
public interface ModifyPwdContract {
    interface Model extends BaseModel {

        Observable<List<RegisterInfo>> doModifyPwd(String mobile, String password,String newPassword,String token);
    }

    interface View extends BaseView {

        void loginSuccess(String message);

        void showError(String message);

    }

    abstract class Persenter extends BasePresenter<Model, View> {

        @Override
        public void onStart() {

        }

        public abstract void userModifyPwd(String mobile, String password,String newPassword,String token);
    }
}