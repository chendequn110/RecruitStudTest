package com.tiandu.recruit.stud.ui.main;



import com.tiandu.recruit.stud.api.exception.MessageFactory;
import com.tiandu.recruit.stud.data.entity.UserInfo;

import java.util.TreeMap;

/**
 * Created by zz on 2016/11/2.
 * zz
 */

public class MainPresenter extends MainContract.Presenter {

    private UserInfo info = null;
    private TreeMap<String,Object> treeMap = null;

    @Override
    public void onStart() {
        super.onStart();
        treeMap = new TreeMap<>();
    }

    @Override
    public void getVersion(int version) {
        mRxManage.add(mModel.getVersion(version)
                .subscribe(info -> {
                    mView.showVersion(info);
                }, e -> {
                    mView.showError(MessageFactory.getMessage(e));
                })
        );
    }
}
