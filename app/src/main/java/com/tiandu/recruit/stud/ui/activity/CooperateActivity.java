package com.tiandu.recruit.stud.ui.activity;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.base.utils.Logger;
import com.tiandu.recruit.stud.base.utils.SpUtil;

import butterknife.BindView;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/10 14:24
 * 修改人：chendequnn
 * 修改时间：2017/11/10 14:24
 * 修改备注：
 */
public class CooperateActivity extends BaseActivity {
    @BindView(R.id.wv_job)
    WebView wv_job;

    @Override
    protected void initView() {
        cannelDialog();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_jobdetail;
    }

    @Override
    protected void initPresenter() {
        getInitnetData();
    }
    private void getInitnetData() {
        wv_job.getSettings().setJavaScriptEnabled(true);
        wv_job.getSettings().setAllowFileAccess(true);
        //加载HTML字符串进行显示
                        if(wv_job!=null){
                            Logger.d(Api.API_DEV_URL+"FeedbackDetail?MemberID="+ SpUtil.getMemberID()+"&Token="+SpUtil.getToken());
                            wv_job.setWebViewClient(new webViewClient ());
                        }
        wv_job.loadUrl(Api.API_DEV_URL+"FeedbackDetail?MemberID="+ SpUtil.getMemberID()+"&Token="+SpUtil.getToken());
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return  false;
    }
    private void showMessage(String message) {
        cannelDialog();
        showToast(message);
    }
    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}