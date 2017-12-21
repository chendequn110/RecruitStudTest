package com.tiandu.recruit.stud.ui.notice;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.base.BaseActivity;

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
public class NoticeDetailActivity extends BaseActivity {
    @BindView(R.id.wv_notice)
    WebView wv_notice;
    private int id;

    @Override
    protected void initView() {
        cannelDialog();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_noticedetail;
    }

    @Override
    protected void initPresenter() {
        Bundle mbundle=getIntent().getExtras();
        id = mbundle.getInt("ID");
        getInitnetData();
    }
    private void getInitnetData() {
        wv_notice.getSettings().setJavaScriptEnabled(true);
        wv_notice.getSettings().setAllowFileAccess(true);
        //加载HTML字符串进行显示
        if(wv_notice!=null){
            wv_notice.loadUrl("http://advertise.shanghaiiot.org/MobileAPI/NoticesView?ID="+id);
            wv_notice.setWebViewClient(new webViewClient ());
        }
//        showloginDialog("");
//        Api.getInstance()
//                .movieService.getNoticeListInfo(C.USER_NOTICELIST,id,SpUtil.getMemberID(), SpUtil.getToken())
//                .compose(RxSchedulers.io_main())
//                .compose(RxSchedulers.sTransformer())
//                .subscribe(new Action1<List<NoticeListInfo>>() {
//                    @Override
//                    public void call(List<NoticeListInfo> Infos) {
//                        cannelDialog();
//                        wv_notice.getSettings().setJavaScriptEnabled(true);
//                        //加载HTML字符串进行显示
//                        if(wv_notice!=null){
//                            wv_notice.loadDataWithBaseURL(null,Infos.get(0).getArticleContent(),"text/html", "utf-8",null);
//                            wv_notice.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
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