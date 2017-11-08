package com.tiandu.recruit.stud.ui.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.base.BaseAppManager;
import com.tiandu.recruit.stud.base.BaseLazyFragment;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.ui.login.LoginActivity;
import com.tiandu.recruit.stud.ui.modify.ModifyPwdActivity;
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
    @BindView(R.id.tvCoupon) TextView tvCoupon;
    @BindView(R.id.tvWall) TextView tvWall;
    @BindView(R.id.tvComplaint) TextView tvComplaint;
    @BindView(R.id.tvEvaluate) TextView tvEvaluate;
    @BindView(R.id.tvExit) TextView tvExit;


    @Override
    protected void initViewsAndEvents() {

    }

    @OnClick({R.id.ivAvatar,R.id.tvModifyPwd,R.id.tvUserInfo, R.id.tvCoupon, R.id.llWall, R.id.tvCoach,R.id.tvComplaint, R.id.tvEvaluate,R.id.tvAboutUs,R.id.tvExit})
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
//            case R.id.llWall:
//                readyGo(WalletActivity.class);
//                break;
//            case R.id.tvCoach:
//                readyGo(AllCoachActivity.class);
//                break;
//            case R.id.tvEvaluate://评论
//                readyGo(EvaluateListActivity.class);
//                break;
//            case R.id.tvComplaint://投诉
//                readyGo(ComplaintActivity.class);
//                break;
//            case R.id.tvAboutUs:
//                readyGo(AboutUsActivity.class);
//                break;
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

        Bundle bundle = new Bundle();
        bundle.putString(C.LOGIN_ACCOUNT, SpUtil.getAccount());
        bundle.putInt(C.USER_OUT, C.USER_OUT_STATUS);
        readyGo(LoginActivity.class, bundle);

        SpUtil.clearAll();
    }

}
