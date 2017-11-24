package com.tiandu.recruit.stud.base;

import android.Manifest;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.base.utils.APPUtils;
import com.tiandu.recruit.stud.base.utils.DialogFactory;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.base.utils.TUtil;
import com.tiandu.recruit.stud.base.utils.helper.MyToast;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.event.LoginEvent;
import com.tiandu.recruit.stud.ui.login.LoginActivity;
import com.tiandu.recruit.stud.view.swipe.SwipeBackLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/10/23 21:23
 * 修改人：chendequnn
 * 修改时间：2017/10/23 21:23
 * 修改备注：
 */
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity {
    protected boolean isNight;
    protected T presenter;
    protected E mModel;

    protected Toolbar mToolbar;
    protected ImageView ivRight,ivLeft;
    protected TextView tvTitle, tvRight;

    private SwipeBackLayout swipeBackLayout;
    private ImageView ivShadow;
    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isNight = SpUtil.isNight();
        setContentView(getLayoutRes());
        showloginDialog("正在加载");
        unbinder = ButterKnife.bind(this);
        setConfigView();
        setConfigToolbar();
        initViewsAndEvents();

    }

    private void setConfigView() {
        if (isApplyStatusBarTranslucency()) {
            setSystemBarTintDrawable();
        }
        BaseAppManager.getInstance().addActivity(this);
    }

    private void setConfigToolbar() {
        ivLeft = ButterKnife.findById(this, R.id.ivLeft);
        tvRight = ButterKnife.findById(this, R.id.tvRight);
        ivRight = ButterKnife.findById(this, R.id.ivRight);
        mToolbar = ButterKnife.findById(this, R.id.toolbar);
        tvTitle = ButterKnife.findById(this, R.id.toolbarTitle);
        if (mToolbar != null) {
            mToolbar.setTitle("");
            setSupportActionBar(mToolbar);
            setHomeEnable(true);
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_arrow_left_small);
            setToolbarTitle(APPUtils.getActivityLabel(this));
            mToolbar.setNavigationOnClickListener((v)-> onBackPressed());
        }
    }

    private void initViewsAndEvents() {
        presenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        this.initView();
        initPresenter();
    }

    protected void setHomeEnable(boolean isEnabled) {
        getSupportActionBar().setHomeButtonEnabled(isEnabled);
        getSupportActionBar().setDisplayHomeAsUpEnabled(isEnabled);
    }

    protected void setToolbarTitle(String title) {
        if (title != null && tvTitle != null) {
            tvTitle.setText(title);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
        unbinder.unbind();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNight != SpUtil.isNight()) {
            reload();
        }

        if (isNeedCheck) {
            checkPermissions(needPermissions);
        }
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (layoutResID == R.layout.activity_main
                || layoutResID == R.layout.activity_splash
                || layoutResID == R.layout.activity_login
                ||  layoutResID == R.layout.activity_network_teach) {
            super.setContentView(layoutResID);
        } else {
            super.setContentView(getContainer());
            View view = LayoutInflater.from(this).inflate(layoutResID, null);
            view.setBackgroundColor(ContextCompat.getColor(this,R.color.windowBackground));
            swipeBackLayout.addView(view);
        }
    }

    private View getContainer() {
        RelativeLayout container = new RelativeLayout(this);
        swipeBackLayout = new SwipeBackLayout(this);
        swipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.LEFT);
        ivShadow = new ImageView(this);
        ivShadow.setBackgroundColor(ContextCompat.getColor(this,R.color.theme_black_7f));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        container.addView(ivShadow, params);
        container.addView(swipeBackLayout);
        swipeBackLayout.setOnSwipeBackListener((fa, fs) -> ivShadow.setAlpha(1 - fs));
        return container;
    }

    /**
     * startActivity
     * @param clazz
     */
    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * startActivity with bundle
     * @param clazz
     * @param bundle
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * startActivity then finish
     * @param clazz
     */
    protected void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    /**
     * startActivity with bundle then finish
     * @param clazz
     * @param bundle
     */
    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    /**
     * startActivityForResult
     * @param clazz
     * @param requestCode
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    protected void showToast(@NonNull String content) {
        //MyToast.makeText(this,content,Toast.LENGTH_SHORT).show();
        MyToast.showToast(content, this);
    }

    /**
     * set status bar Tint
     */
    private void setSystemBarTintDrawable() {
        SystemBarTintManager mTintManager = new SystemBarTintManager(this);
        mTintManager.setStatusBarTintEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        mTintManager.setStatusBarTintResource(R.color.colorPrimary);
        mTintManager.setNavigationBarTintResource(R.color.colorPrimary);
    }

    /**
     * set status bar translucent
     */
    private void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    @Override
    public void finish() {
        super.finish();
        BaseAppManager.getInstance().removeActivity(this);
    }

    private Dialog loginDialog = null;
    public void cannelDialog() {
        if (loginDialog != null && loginDialog.isShowing()) {
            loginDialog.dismiss();
        }
    }

    public void showloginDialog(String context) {
        loginDialog = DialogFactory.getLoadingDialog(this,context,false,null);
        loginDialog.show();
    }


    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_SMS,

    };

    private static final int PERMISSON_REQUESTCODE = 0;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    /**
     * @param
     * @since 2.5.0
     */
    private void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否说有的权限都已经授权
     *
     * @param grantResults
     * @return
     * @since 2.5.0
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                if (Build.VERSION.SDK_INT < 23) {
                    //版本判断
                } else {
                    showMissingPermissionDialog();
                    isNeedCheck = false;
                }
            }
        }
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.notifyTitle);
        builder.setMessage(R.string.notifyMsg);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.cancel2,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BaseAppManager.getInstance().clearAll();
                        finish();
                    }
                });

        builder.setPositiveButton(R.string.setting,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);
        builder.show();
    }

    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onloginoutEvent(LoginEvent event) {
        if (event.getIsOutLogin() == -1) {
            outLogin();
        }
    }
    public void outLogin() {
        showToast("您的账号" + SpUtil.getAccount() + "在其他手机登录");
        BaseAppManager.getInstance().clearAll();

        Bundle bundle = new Bundle();
        bundle.putString(C.LOGIN_ACCOUNT,SpUtil.getAccount());

        if (!isForeground(this, "com.jq.learn.yn.stud.ui.login.LoginActivity")) {
            readyGo(LoginActivity.class,bundle);
        }

        SpUtil.clearAll();
    }
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


    protected abstract void initView();

    protected abstract int getLayoutRes();

    protected abstract void initPresenter();

    protected abstract boolean isApplyStatusBarTranslucency();

}