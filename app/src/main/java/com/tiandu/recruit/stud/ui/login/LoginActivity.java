package com.tiandu.recruit.stud.ui.login;


import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.tiandu.recruit.stud.MainActivity;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.base.utils.AStringUtil;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.ui.RegisterActivity;
import com.tiandu.recruit.stud.view.ClearEditText;



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
    @BindView(R.id.btnRegister) Button btnRegister;
    @BindView(R.id.tvForget)
    TextView tvForget;
    @BindView(R.id.cbProtocol)CheckBox cbProtocol;
    //@BindView(R.id.tvFirstLogin) TextView tvFirstLogin;
    @BindView(R.id.etAccount) ClearEditText etAccount;
    @BindView(R.id.etPassword) ClearEditText etPassword;
    @BindString(R.string.login_loading) String loading;
    private String uName = null;
    public static boolean isFirst;
    private String registrationID;
    private AlertDialog.Builder builder;
    @Override
    protected void initView() {
        cannelDialog();
//        EventBus.getDefault().register(this);
        etAccount.setText(SpUtil.getAccount());
        isFirstLogined();
    }
    private void isFirstLogined() {
        cbProtocol.setChecked(true);
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

    @OnClick(R.id.tvForget) void forgetClick() {
        readyGo(RegisterActivity.class);
    }
    @OnClick(R.id.btnRegister) void registerClick() {
        readyGo(RegisterActivity.class);
    }
    @OnCheckedChanged(R.id.cbProtocol) void onChecked(boolean checked) {
        if (checked) {
            btnLogin.setEnabled(true);
        } else {
            btnLogin.setEnabled(false);
            showToast("请先勾选人人招协议");
        }
    }
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
        if (!cbProtocol.isChecked()) {
            showToast("请先人人招协议");
            return ;
        }
        showloginDialog(loading);
        readyGoThenKill(MainActivity.class);
//        presenter.userLogin(uName,uPasswd,registrationID);
    }
    @Override
    protected void initPresenter() {
//        presenter.setVM(this,mModel);
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

}