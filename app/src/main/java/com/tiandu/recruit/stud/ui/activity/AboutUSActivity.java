package com.tiandu.recruit.stud.ui.activity;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.base.BaseActivity;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2018/3/9 17:41
 * 修改人：chendequnn
 * 修改时间：2018/3/9 17:41
 * 修改备注：
 */
public class AboutUSActivity extends BaseActivity {
//    @OnClick({R.id.etSetting_web})
//    void OnAboutUsClick(View view) {
//        Intent intent = new Intent();
//        intent.setAction("android.intent.action.VIEW");
//        Uri content_url = Uri.parse("www.tdhr-group.com");
//        intent.setData(content_url);
//        startActivity(intent);
//    }
//    @OnClick({R.id.etSetting_phone})
//    void OnAboutUsClick2(View view) {
//        Intent intent = new Intent(Intent.ACTION_DIAL);
//        intent.setData(Uri.parse("tel:" + "021-51087995"));
//        startActivity(intent);
//    }
    @Override
    protected void initView() {
        cannelDialog();
    }

    @Override
    protected int getLayoutRes() {
            return R.layout.activity_aboutus;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
            return  false;
    }
}