package com.tiandu.recruit.stud.ui.userInfo;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.api.exception.MessageFactory;
import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.MeInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

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
//    @BindView(R.id.ltbindwx)
//    LinearLayout ltbindwx;
//    @BindView(R.id.ltbindqq)
//    LinearLayout ltbindqq;
    @BindView(R.id.tvBindPhone)
    TextView tvBindPhone;
    @BindView(R.id.tvBindMail)
    TextView tvBindMail;
//    @BindView(R.id.tvBindwx)
//    TextView tvBindwx;
//    @BindView(R.id.tvBindqq)
//    TextView tvBindqq;

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
        getInitnetData();
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }
    @OnClick({R.id.ltBindPhone,R.id.ltBindMail})//,R.id.ltbindwx,R.id.ltbindqq
    void OnSelectedClick(View view) {
        switch (view.getId()) {
            case R.id.ltBindPhone:
                readyGo(BindPhoneActivity.class);
                break;
            case R.id.ltBindMail:
                readyGo(BindMailActivity.class);
                break;
//            case R.id.ltbindwx:
//                readyGo(BindWxActivity.class);
//                break;
//            case R.id.ltbindqq:
//                readyGo(BindQQActivity.class);
//                break;
        }
    }
    private void getInitnetData() {
        showloginDialog("");
        Api.getInstance()
                .movieService.getUserInfo(C.USER_USERINFO_PATH, SpUtil.getMemberID(), SpUtil.getToken())
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<MeInfo>>() {
                    @Override
                    public void call(List<MeInfo> meInfos) {
                        cannelDialog();
                        if (null != meInfos) {
                            tvBindPhone.setText( meInfos.get(0).getBindMobile());
                            tvBindMail.setText( meInfos.get(0).getBindEmail());
//                            tvBindwx.setText( meInfos.get(0).getBindWechat());
//                            tvBindqq.setText( meInfos.get(0).getBindQQ());
                        }else {
                            showToast("数据为空");
                        }
                    }
                }, e -> {
                    cannelDialog();
                    showMessage(MessageFactory.getMessage(e));
                });
    }
    private void showMessage(String message) {
        cannelDialog();
        showToast(message);
    }
}