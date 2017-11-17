package com.tiandu.recruit.stud.ui.fragment;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.base.BaseAppManager;
import com.tiandu.recruit.stud.base.BaseLazyFragment;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.ui.login.LoginActivity;
import com.tiandu.recruit.stud.ui.modify.ModifyPwdActivity;
import com.tiandu.recruit.stud.ui.userInfo.BindInfoActivity;
import com.tiandu.recruit.stud.ui.userInfo.CertificationActivity;
import com.tiandu.recruit.stud.ui.userInfo.ChildMemberInfoActivity;
import com.tiandu.recruit.stud.ui.userInfo.MeActivity;

import butterknife.BindView;
import butterknife.OnClick;

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


    @Override
    protected void initViewsAndEvents() {

    }

    @OnClick({R.id.ivAvatar,R.id.tvModifyPwd,R.id.tvUserInfo, R.id.llWall,R.id.tvAboutUs,R.id.tvExit,R.id.tvAccountBind,R.id.tvAuthentic,R.id.llChildInfo})
    void OnSelectedClick(View view) {
        switch (view.getId()) {
            case R.id.tvModifyPwd:
                readyGo(ModifyPwdActivity.class);
                break;
            case R.id.tvUserInfo:
                readyGo(MeActivity.class);
                break;
            case R.id.ivAvatar:
                readyGo(MeActivity.class);
                break;
            case R.id.tvAccountBind:
                readyGo(BindInfoActivity.class);
                break;
            case R.id.tvAuthentic:
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
//        Api.getInstance()
//                .movieService.getUserInfo(SpUtil.getToken())
//                .compose(RxSchedulers.io_main())
//                .compose(RxSchedulers.sTransformer())
//                .subscribe(new Action1<UserInfo.DataBean>() {
//                    @Override
//                    public void call(UserInfo.DataBean dataBean) {
//                        showUserInfo(dataBean);
//                    }
//                }, e -> {
//                    showMessage(MessageFactory.getMessage(e));
//                });
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
