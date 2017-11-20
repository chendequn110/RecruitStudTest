package com.tiandu.recruit.stud.ui.fragment;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.base.BaseAppManager;
import com.tiandu.recruit.stud.base.BaseLazyFragment;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.MeInfo;
import com.tiandu.recruit.stud.ui.login.LoginActivity;
import com.tiandu.recruit.stud.ui.modify.ModifyPwdActivity;
import com.tiandu.recruit.stud.ui.userInfo.BindInfoActivity;
import com.tiandu.recruit.stud.ui.userInfo.CertificationActivity;
import com.tiandu.recruit.stud.ui.userInfo.ChildMemberInfoActivity;
import com.tiandu.recruit.stud.ui.userInfo.MeActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by Jerome on 2016/10/27.
 * Email :jeromekai8@gmail.com
 */

public class UserFragment extends BaseLazyFragment {

    @BindView(R.id.ivAvatar) ImageView ivAvatar;
    @BindView(R.id.tvName) TextView tvName;
    @BindView(R.id.tvUserInfo) TextView tvUserInfo;
    @BindView(R.id.tvWall) TextView tvWall;
    @BindView(R.id.tvExit) TextView tvExit;
    @BindView(R.id.tvAccountBind) TextView tvAccountBind;
    @BindView(R.id.tvGetUserInfo) TextView tvGetUserInfo;
    @BindView(R.id.tvGetAuthentic) TextView tvGetAuthentic;


    @Override
    protected void initViewsAndEvents() {

    }

    @OnClick({R.id.ivAvatar,R.id.tvModifyPwd,R.id.llUserInfo, R.id.llWall,R.id.tvAboutUs,R.id.tvExit,R.id.tvAccountBind,R.id.llAuthentic,R.id.llChildInfo})
    void OnSelectedClick(View view) {
        switch (view.getId()) {
            case R.id.tvModifyPwd:
                readyGo(ModifyPwdActivity.class);
                break;
            case R.id.llUserInfo:
                readyGo(MeActivity.class);
                break;
            case R.id.ivAvatar:
                readyGo(MeActivity.class);
                break;
            case R.id.tvAccountBind:
                readyGo(BindInfoActivity.class);
                break;
            case R.id.llAuthentic:
                readyGo(CertificationActivity.class);
                break;
            case R.id.llChildInfo:
                readyGo(ChildMemberInfoActivity.class);
                break;
            case R.id.tvExit:
                exitLogin();
                break;
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_userinfo;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    private View view;

    @Override
    protected void onFirstUserVisible() {
        view = getActivity().findViewById(R.id.statusBar);
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        }

        if (isUser()) {
            initUserInfo();
        }
    }

    @Override
    protected void onUserVisible() {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onFirstUserInvisible() {
    }

    @Override
    protected void onUserInvisible() {
    }

    private void initUserInfo() {
        Api.getInstance()
        .movieService.getUserInfo(C.USER_USERINFO_PATH,SpUtil.getMemberID(), SpUtil.getToken())
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<MeInfo>>() {
                    @Override
                    public void call(List<MeInfo> meInfos) {
                        if (null != meInfos) {
                            tvGetAuthentic.setText(meInfos.get(0).getStatusName());
                            tvGetUserInfo.setText(meInfos.get(0).getMemberID());
                        }else {

                        }
                    }
                }, e -> {

                });
    }


//    private void showUserInfo(UserInfo.DataBean dataBean) {
//        if (null != dataBean) {
//            AImageUtil.loadCircleImg2(ivAvatar, dataBean.getIcon());
//            if (null != dataBean.getIcon()) {
//                SpUtil.setIcon(dataBean.getIcon());
//            }
//            if (null != dataBean.getName()) {
//                tvName.setText(dataBean.getName());
//                SpUtil.setName(dataBean.getName());
//            }
//        }
//
//    }

    private void showMessage(String message) {
        showToast(message);
    }

    private void exitLogin() {
        BaseAppManager.getInstance().clearAll();
//        Bundle bundle = new Bundle();
//        bundle.putString(C.LOGIN_ACCOUNT, SpUtil.getAccount());
//        bundle.putInt(C.USER_OUT, C.USER_OUT_STATUS);
        SpUtil.clearAll();
        readyGo(LoginActivity.class);
//        BaseAppManager.getInstance().clearToTop();
    }

}
