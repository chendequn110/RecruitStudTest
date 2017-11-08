package com.tiandu.recruit.stud.ui.modify;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.base.utils.AStringUtil;
import com.tiandu.recruit.stud.base.utils.CountDownButtonHelper;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.ui.login.LoginActivity;
import com.tiandu.recruit.stud.view.ClearEditText;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/8 14:09
 * 修改人：chendequnn
 * 修改时间：2017/11/8 14:09
 * 修改备注：
 */
public class ModifyPwdActivity extends BaseActivity<ModifyPwdPresenter,ModifyPwdModel> implements ModifyPwdContract.View{
    @BindView(R.id.btnSubmit)Button btnSubmit;
    @BindView(R.id.etPhone)
    ClearEditText etPhone;
    @BindView(R.id.oldPasswd)
    ClearEditText oldPasswd;
    @BindView(R.id.newPasswd)
    ClearEditText newPasswd;
    @BindView(R.id.okPasswd) ClearEditText okPasswd;
    @BindString(R.string.modify_loading) String loading;



    @OnClick(R.id.btnSubmit) void submitClick(){
        String phone=etPhone.getText().toString().trim();
        if (AStringUtil.isEmpty(phone)) {
            showToast("手机号不能为空");
            return ;
        }
        String oldPassword=oldPasswd.getText().toString().trim();
        if (AStringUtil.isEmpty(phone)) {
            showToast("旧密码不能为空");
            return ;
        }
        String newPassword = newPasswd.getText().toString().trim();
        if (AStringUtil.isEmpty(newPassword)) {
            showToast("新密码不能为空");
            return ;
        }
        String okPwd = okPasswd.getText().toString().trim();
        if (AStringUtil.isEmpty(okPwd)) {
            showToast("确认密码不能为空");
            return ;
        }
        if(!newPassword.equals(okPwd)){
            showToast("两次输入密码不一致");
            return ;
        }
        showloginDialog(loading);
        presenter.userModifyPwd(phone,oldPassword,newPassword, SpUtil.getToken());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView() {
        cannelDialog();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_modify;
    }

    @Override
    protected void initPresenter() {
        presenter.setVM(this,mModel);
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return  false;
    }

    @Override
    public void loginSuccess(String message) {
        cannelDialog();
        showToast(message);
        readyGoThenKill(LoginActivity.class);
    }

    @Override
    public void showError(String message) {
        showToast(message);
        cannelDialog();
    }
}