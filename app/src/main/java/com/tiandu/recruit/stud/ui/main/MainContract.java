package com.tiandu.recruit.stud.ui.main;




import com.tiandu.recruit.stud.base.BaseModel;
import com.tiandu.recruit.stud.base.BasePresenter;
import com.tiandu.recruit.stud.base.BaseView;
import com.tiandu.recruit.stud.data.entity.VersionInfo;

import rx.Observable;

/**
 * Created by zz on 2016/11/2.
 * zz
 */

public interface MainContract {

    interface Model extends BaseModel {
        Observable<VersionInfo> getVersion(int version);
    }

    interface View extends BaseView {

        void showVersion(VersionInfo info);

        void showError(String message);
    }

    abstract class Presenter extends BasePresenter<Model,View> {

        @Override
        public void onStart() {

        }

        public abstract void getVersion(int i);
    }
}
