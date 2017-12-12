package com.tiandu.recruit.stud.base;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tiandu.recruit.stud.base.utils.DialogFactory;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.base.utils.helper.MyToast;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.event.LoginEvent;
import com.tiandu.recruit.stud.ui.login.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zz on 16/6/12.
 * zz
 */
public abstract class BaseLazyFragment extends Fragment {

    private boolean isPrepared;
    protected Context context;
    private Unbinder unbinder;

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

        initPrepare();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutRes() != 0) {
            return inflater.inflate(getLayoutRes(), container, false);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initViewsAndEvents();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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

    protected abstract void initViewsAndEvents();

    protected abstract int getLayoutRes();

    protected abstract boolean isBindEventBusHere();

    /**
     * 第一次onResume中的调用onUserVisible避免操作与onFirstUserVisible操作重复
     */
    private boolean isFirstResume = true;

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstResume) {
            isFirstResume = false;
            return;
        }

        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }

    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                onUserInvisible();
            }
        }
    }

    public synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
    }

    /**
     * 第一次fragment可见（进行初始化工作）
     */
    protected abstract void onFirstUserVisible();

    /**
     * fragment可见（切换回来或者onResume）
     */
    protected abstract void onUserVisible();

    /**
     * 第一次fragment不可见（不建议在此处理事件）
     */
    protected abstract void onFirstUserInvisible();

    /**
     * fragment不可见（切换掉或者onPause）
     */
    protected abstract void onUserInvisible();

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
        showToast("您的账号" + SpUtil.getAccount() + "登录异常,请重新登录");
        BaseAppManager.getInstance().clearAll();
        Bundle bundle = new Bundle();
        bundle.putString(C.LOGIN_ACCOUNT,SpUtil.getAccount());

        if (!isForeground(context, "com.jq.learn.yn.stud.ui.login.LoginActivity")) {
            readyGo(LoginActivity.class,bundle);
        }

        SpUtil.clearAll();
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

    /**
     * 判断某个界面是否在前台
     *
     * @param context
     * @param className
     *            某个界面名称
     */
    private boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }

        return false;
    }
}
