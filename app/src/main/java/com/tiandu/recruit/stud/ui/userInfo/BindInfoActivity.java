package com.tiandu.recruit.stud.ui.userInfo;

import android.view.View;
import android.widget.LinearLayout;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/17 11:20
 * 修改人：chendequnn
 * 修改时间：2017/11/17 11:20
 * 修改备注：
 */
public class BindInfoActivity extends BaseActivity {
    @BindView(R.id.ltBindPhone)
    LinearLayout ltBindPhone;
    @BindView(R.id.ltBindMail)
    LinearLayout ltBindMail;
    @BindView(R.id.ltbindwx)
    LinearLayout ltbindwx;
    @BindView(R.id.ltbindqq)
    LinearLayout ltbindqq;

    @Override
    protected void initView() {
        cannelDialog();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bindinfo;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }
    @OnClick({R.id.ltBindPhone,R.id.ltBindMail,R.id.ltbindwx,R.id.ltbindqq})
    void OnSelectedClick(View view) {
        switch (view.getId()) {
            case R.id.ltBindPhone:
                readyGo(BindPhoneActivity.class);
                break;
            case R.id.ltBindMail:
                readyGo(BindMailActivity.class);
                break;
            case R.id.ltbindwx:
                readyGo(BindWxActivity.class);
                break;
            case R.id.ltbindqq:
                readyGo(BindQQActivity.class);
                break;
        }
    }
}