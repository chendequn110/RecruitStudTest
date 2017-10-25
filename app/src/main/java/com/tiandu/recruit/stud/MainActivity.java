package com.tiandu.recruit.stud;



import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.ui.MeActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rb_home)
    RadioButton rb_home;
    @BindView(R.id.rb_human)
    RadioButton rb_human;
    @BindView(R.id.rb_trends)
    RadioButton rb_trends;
    @BindView(R.id.rb_me)
    RadioButton rb_me;

    @Override
    protected void initView() {
        cannelDialog();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }
    @OnClick(R.id.rb_me) void meClick() {
        readyGo(MeActivity.class);
    }
    @OnClick(R.id.rb_home) void humanClick() {
    }
    @OnClick(R.id.rb_trends) void homeClick() {
    }
    @OnClick(R.id.rb_human) void retClick() {

    }
    @Override
    protected void initPresenter() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }
}
