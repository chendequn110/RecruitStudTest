package com.tiandu.recruit.stud;



import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.ui.MeActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.re_home)
    RelativeLayout re_home;
    @BindView(R.id.re_home_human)
    RelativeLayout re_home_human;
    @BindView(R.id.re_trends)
    RelativeLayout re_trends;
    @BindView(R.id.re_home_me)
    RelativeLayout re_home_me;

    @Override
    protected void initView() {
        cannelDialog();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }
    @OnClick(R.id.re_home_me) void meClick() {
        readyGo(MeActivity.class);
    }
    @OnClick(R.id.re_home) void humanClick() {
    }
    @OnClick(R.id.re_home_human) void homeClick() {
    }
    @OnClick(R.id.re_trends) void retClick() {

    }
    @Override
    protected void initPresenter() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }
}
