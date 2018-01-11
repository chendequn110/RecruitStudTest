package com.tiandu.recruit.stud.ui.activity;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.base.BaseActivity;
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
    private int id;

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
                            wv_job.loadUrl(Api.API_DEV_URL+"/FeedbackDetail?MemberID="+ SpUtil.getMemberID()+"&Token="+SpUtil.getToken());
                            wv_job.setWebViewClient(new webViewClient ());
                        }
//        showloginDialog("");
//        Api.getInstance()
//                .movieService.getJobListInfo(C.USER_JOBLISTINFO,id,SpUtil.getMemberID(), SpUtil.getToken())
//                .compose(RxSchedulers.io_main())
//                .compose(RxSchedulers.sTransformer())
//                .subscribe(new Action1<List<JobListInfo>>() {
//                    @Override
//                    public void call(List<JobListInfo> Infos) {
//                        cannelDialog();
//                        wv_job.getSettings().setJavaScriptEnabled(true);
//                        //加载HTML字符串进行显示
//                        if(wv_job!=null){
//                            wv_job.loadDataWithBaseURL(null,Infos.get(0).getArticleContent(),"text/html", "utf-8",null);
//                            wv_job.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//                        }
//
//                    }
//                }, e -> {
//                    cannelDialog();
//                    showMessage(MessageFactory.getMessage(e));
//                });
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