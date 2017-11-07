package com.tiandu.recruit.stud.ui.main;



import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.VersionInfo;

import rx.Observable;

/**
 * Created by zz on 2016/11/2.
 * zz
 */

public class MainModel implements MainContract.Model {

    @Override
    public Observable<VersionInfo> getVersion(int version) {
        return Api.getInstance().movieService.getVersionInfo(C.USER_REGISTER_PATH,version)
                .compose(RxSchedulers.io_main()).compose(RxSchedulers.sTransformer());
    }
}
