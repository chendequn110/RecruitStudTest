package com.tiandu.recruit.stud;



import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.ui.MeActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Override
    protected void initView() {
        cannelDialog();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }
}
