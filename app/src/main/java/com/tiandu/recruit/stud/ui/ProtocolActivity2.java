package com.tiandu.recruit.stud.ui;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Jerome on 16/9/17.
 * Email :jeromekai8@gmail.com
 */
public class ProtocolActivity2 extends BaseActivity {

    @BindView(R.id.webView) WebView webView;

    @Override
    protected void initView() {
        cannelDialog();
        WebSettings wSet = webView.getSettings();
        wSet.setJavaScriptEnabled(true);
        webView.loadUrl("http://www.tdhr-rpo.com/"+"MemberReg/LegalAgreement");
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_protocol;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }
}
