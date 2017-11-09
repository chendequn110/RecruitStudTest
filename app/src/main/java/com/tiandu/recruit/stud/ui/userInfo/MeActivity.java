package com.tiandu.recruit.stud.ui.userInfo;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.api.exception.MessageFactory;
import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.base.utils.AStringUtil;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.MeInfo;
import com.tiandu.recruit.stud.ui.register.RegisterActivity;
import com.tiandu.recruit.stud.view.ClearEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/10/23 23:59
 * 修改人：chendequnn
 * 修改时间：2017/10/23 23:59
 * 修改备注：
 */
public class MeActivity extends BaseActivity {
    @BindView(R.id.etMemberID)
    ClearEditText etMemberID;
    @BindView(R.id.etMemberName)
    ClearEditText etMemberName;
    @BindView(R.id.etBindMobile)
    ClearEditText etBindMobile;
    @BindView(R.id.etBindEmail)
    ClearEditText etBindEmail;
    @BindView(R.id.etBindWechat)
    ClearEditText etBindWechat;
    @BindView(R.id.etBindQQ)
    ClearEditText etBindQQ;
    @BindView(R.id.etIDNumber)
    ClearEditText etIDNumber;
    @BindView(R.id.etRealName)
    ClearEditText etRealName;
    @BindView(R.id.etParentID)
    ClearEditText etParentID;
    @BindView(R.id.ltBirthdate)
    LinearLayout ltBirthdate;
    @BindView(R.id.ltCity)
    LinearLayout ltCity;
    @BindView(R.id.ltGender)
    LinearLayout ltGender;
    @BindView(R.id.ltProvince)
    LinearLayout ltProvince;
    @BindView(R.id.btnUpdata)
    Button btnUpdata;
    @Override
    protected void initView() {
        cannelDialog();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_userinfo;
    }

    @Override
    protected void initPresenter() {
        getInitnetData();
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    private void getInitnetData() {
        showloginDialog("");
        Api.getInstance()
                .movieService.getUserInfo(C.USER_USERINFO_PATH,SpUtil.getAccount(), SpUtil.getToken())
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<MeInfo>>() {
                    @Override
                    public void call(List<MeInfo> meInfos) {
                        cannelDialog();
                        if (null != meInfos) {
                            etMemberID.setText( meInfos.get(0).getMemberID());
                            etMemberName.setText(meInfos.get(0).getMemberName());
                            etBindMobile.setText( meInfos.get(0).getBindMobile());
                            etBindEmail.setText(meInfos.get(0).getBindEmail());
                            etBindWechat.setText( meInfos.get(0).getBindWechat());
                            etBindQQ.setText(meInfos.get(0).getBindQQ());
                            etIDNumber.setText( meInfos.get(0).getIDNumber());
                            etRealName.setText(meInfos.get(0).getRealName());
                            etParentID.setText( meInfos.get(0).getParentID());
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
    @OnClick(R.id.btnUpdata) void upDataUserInfo() {
        {
            showloginDialog("更新资料中...");
            Api.getInstance()
                    .movieService.upDataUserInfo(C.USER_UPDATAUSERINFO_PATH,"Edit",SpUtil.getToken())
                    .compose(RxSchedulers.io_main())
                    .compose(RxSchedulers.sTransformer())
                    .subscribe(MeInfo -> {
                        showMessage("会员更新成功");
                    }, e -> {
                        showMessage(MessageFactory.getMessage(e));
                    });
        }
    }
}