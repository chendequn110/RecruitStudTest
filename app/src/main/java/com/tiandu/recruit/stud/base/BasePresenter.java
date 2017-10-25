package com.tiandu.recruit.stud.base;

import android.content.Context;

/**
 * Created by Jerome on 16/5/24.
 * Email :jeromekai8@gmail.com
 */
public abstract class BasePresenter<E,T> {

    public Context context;
    public E mModel;
    public T mView;
    public RxManage mRxManage = new RxManage();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public abstract void onStart();

    public void onDestroy() {
        mRxManage.clear();
    }


}
