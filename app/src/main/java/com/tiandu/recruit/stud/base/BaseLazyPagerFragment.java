package com.tiandu.recruit.stud.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.tiandu.recruit.stud.base.utils.DialogFactory;
import com.tiandu.recruit.stud.base.utils.Logger;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.base.utils.helper.MyToast;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.event.LoginEvent;
import com.tiandu.recruit.stud.ui.login.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zz on 16/6/12.
 * zz
 */
public abstract class BaseLazyPagerFragment extends Fragment {

    private boolean isPrepared;
    protected Context context;
    private Unbinder unbinder;


    /**
     * 视图是否已经初初始化
     */
    protected boolean isInit = false;
    protected boolean isLoad = false;
    private View view;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isInit = true;
        if (getLayoutRes() != 0) {
            view = inflater.inflate(getLayoutRes(), container, false);
            unbinder = ButterKnife.bind(this, view);
            initViewsAndEvents();
            /**初始化的时候去加载数据**/
            isCanLoadData();
            return view;
        } else {
            view = super.onCreateView(inflater, container, savedInstanceState);
            unbinder = ButterKnife.bind(this, view);
            initViewsAndEvents();
            isCanLoadData();
            return view;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        isInit = false;
        isLoad = false;

        if (unbinder != null) {
            unbinder.unbind();
        }
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // for bug ---> java.lang.IllegalStateException: Activity has been destroyed
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    protected abstract void initViewsAndEvents();

    protected abstract int getLayoutRes();

    protected abstract boolean isBindEventBusHere();

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (!isInit) {
            return;
        }

        if (getUserVisibleHint()) {
            lazyLoad();
            isLoad = true;
        } else {
            if (isLoad) {
                stopLoad();
            }
        }
    }


    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    protected abstract void lazyLoad();

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以调用此方法
     */
    protected void stopLoad() {
    }








    protected void showToast(String msg) {
        MyToast.showToast(msg, context);
    }

    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        startActivity(intent);
    }

    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onloginoutEvent(LoginEvent event) {
        if (event.getIsOutLogin() == -1) {
            outLogin();
        }
    }

    public void outLogin() {
//        showToast("您的账号" + SpUtil.getAccount() + "在其他手机登录");
//        BaseAppManager.getInstance().clearAll();
//        Bundle bundle = new Bundle();
//        bundle.putString(C.LOGIN_ACCOUNT,SpUtil.getAccount());
//        readyGo(LoginActivity.class,bundle);
//
//        Logger.d("出来咯fragment");
//
//        SpUtil.clearAll();
    }

    public boolean isUser() {
//        if (SpUtil.getIsVisiter() == 1) {
//            //游客
//            return false;
//        }
        return true;
    }

    private Dialog loginDialog = null;
    public void cannelMyDialog() {
        if (loginDialog != null&& loginDialog.isShowing()) {
            loginDialog.dismiss();
        }
    }

    public void showMyDialog(String context) {
        loginDialog = DialogFactory.getLoadingDialog(getActivity(),context,false,null);
        loginDialog.show();
    }

}
