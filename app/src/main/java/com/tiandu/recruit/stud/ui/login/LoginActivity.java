package com.tiandu.recruit.stud.ui.login;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tiandu.recruit.stud.MainActivity;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.base.utils.AStringUtil;
import com.tiandu.recruit.stud.base.utils.DialogFactory;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.ui.RegisterActivity;
import com.tiandu.recruit.stud.view.ClearEditText;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;


import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/10/23 18:21
 * 修改人：chendequnn
 * 修改时间：2017/10/23 18:21
 * 修改备注：
 */
public class LoginActivity extends BaseActivity{
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tvRegister) TextView tvRegister;
    @BindView(R.id.tvForget)
    TextView tvForget;
    /*@BindView(R.id.cbProtocol)CheckBox cbProtocol;*/
    //@BindView(R.id.tvFirstLogin) TextView tvFirstLogin;
    @BindView(R.id.login_qq_fast_login)
    ImageView iv_qq_fast_login;
    @BindView(R.id.login_we_chat_fast_login)
    ImageView iv_wechat_fast_login;
    @BindView(R.id.etAccount) ClearEditText etAccount;
    @BindView(R.id.etPassword) ClearEditText etPassword;
    @BindString(R.string.login_loading) String loading;
    private String uName = null;
    public static boolean isFirst;
    private String registrationID;
    private AlertDialog.Builder builder;
    private Dialog fastDialog = null;
    @Override
    protected void initView() {
        fastDialog = DialogFactory.getLoadingDialog(this,"跳转中",false,null);
        cannelDialog();
//        EventBus.getDefault().register(this);
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(this).setShareConfig(config);
        etAccount.setText(SpUtil.getAccount());
        isFirstLogined();
    }
    private void isFirstLogined() {
      /*  cbProtocol.setChecked(true);*/
        if (isFirst) {
            finish();
        }
        if (SpUtil.isLogined()) {
            readyGoThenKill(MainActivity.class);
            return;
        }
    }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onReceiverEvent(ReceiverEvent event) {
//        if (event != null) {
//            registrationID = event.getRegistrationId();
//        }
//    }
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }
//    @OnClick(R.id.tvProtocol) void onClick() {
//        readyGo(ProtocolActivity.class);
//    }

//    @OnClick(R.id.tvForget) void forgetClick() {
//        readyGo(RegisterActivity.class);
//    }
    @OnClick(R.id.tvRegister) void registerClick() {
        readyGo(RegisterActivity.class);
    }
//    @OnCheckedChanged(R.id.cbProtocol) void onChecked(boolean checked) {
//        if (checked) {
//            btnLogin.setEnabled(true);
//        } else {
//            btnLogin.setEnabled(false);
//            showToast("请先勾选人人招协议");
//        }
//    }
    @OnClick(R.id.btnLogin) void onSubmitClick() {
        uName = etAccount.getText().toString().trim();
        if (AStringUtil.isEmpty(uName)) {
            showToast("用户名不能为空");
            return ;
        }
        String uPasswd = etPassword.getText().toString().trim();
        if (AStringUtil.isEmpty(uPasswd)) {
            showToast("密码不能为空");
            return ;
        }
//        if (!cbProtocol.isChecked()) {
//            showToast("请先人人招协议");
//            return ;
//        }
        showloginDialog(loading);
        readyGoThenKill(MainActivity.class);
//        presenter.userLogin(uName,uPasswd,registrationID);
    }
    @OnClick(R.id.login_qq_fast_login) void qqFastLoginClick(){
        UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.QQ, authListener);
    }
    @OnClick(R.id.login_we_chat_fast_login) void wechatFastClick(){
        UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.WEIXIN, authListener);
    }
    @Override
    protected void initPresenter() {
//        presenter.setVM(this,mModel);
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(fastDialog);
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            SocializeUtils.safeCloseDialog(fastDialog);
            showToast("成功");
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            SocializeUtils.safeCloseDialog(fastDialog);
            showToast("失败：" + t.getMessage());
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            SocializeUtils.safeCloseDialog(fastDialog);
            showToast("取消");
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}