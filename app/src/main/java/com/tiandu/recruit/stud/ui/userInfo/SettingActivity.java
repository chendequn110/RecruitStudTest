package com.tiandu.recruit.stud.ui.userInfo;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.base.BaseActivity;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/21 16:02
 * 修改人：chendequnn
 * 修改时间：2017/11/21 16:02
 * 修改备注：
 */
public class SettingActivity extends BaseActivity {
    @Override
    protected void initView() {
        cannelDialog();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }
}