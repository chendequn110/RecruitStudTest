package com.tiandu.recruit.stud.ui.register;



import com.tiandu.recruit.stud.base.BaseModel;
import com.tiandu.recruit.stud.base.BasePresenter;
import com.tiandu.recruit.stud.base.BaseView;
import com.tiandu.recruit.stud.data.entity.RegisterInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by cdq on 2017/10/29.
 */

public interface RegisterContract {
    interface Model extends BaseModel {

        Observable<List<RegisterInfo>> doRegister(String mobile, String password ,String authCode);
    }

    interface View extends BaseView {

        void loginSuccess(String message);

        void showError(String message);

    }

    abstract class Persenter extends BasePresenter<Model, View> {

        @Override
        public void onStart() {

        }

        public abstract void userRegister(String mobile ,String password,String authCode);
    }
}
