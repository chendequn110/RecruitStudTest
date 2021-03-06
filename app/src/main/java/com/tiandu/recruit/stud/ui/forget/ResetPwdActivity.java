package com.tiandu.recruit.stud.ui.forget;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.api.exception.MessageFactory;
import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.base.utils.AStringUtil;
import com.tiandu.recruit.stud.base.utils.CountDownButtonHelper;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.RegisterInfo;
import com.tiandu.recruit.stud.ui.login.LoginActivity;
import com.tiandu.recruit.stud.view.ClearEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import rx.functions.Action1;


/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/6 16:19
 * 修改人：chendequnn
 * 修改时间：2017/11/6 16:19
 * 修改备注：
 */
public class ResetPwdActivity extends BaseActivity <ResetPwdPresenter,ResetPwdModel> implements ResetPwdContract.View{
    @BindView(R.id.btnSubmit)Button btnSubmit;
    @BindView(R.id.btnCode)Button btnCode;
    @BindView(R.id.etPhone)
    ClearEditText etPhone;
    @BindView(R.id.etValideCode)
    EditText etValideCode;
    @BindView(R.id.setPasswd)
    ClearEditText setPasswd;
    @BindView(R.id.okPasswd) ClearEditText okPasswd;
    @BindString(R.string.modify_loading) String loading;
    private EventHandler handler;
    private String phone;



    @OnClick(R.id.btnCode) void getCodeClick(){
        phone = etPhone.getText().toString().trim();
        if(AStringUtil.isPhone(phone)){
            CountDownButtonHelper helper = new CountDownButtonHelper(btnCode,"",60,1);
//            helper.setOnFinishListener(new CountDownButtonHelper.OnFinishListener() {
//                @Override
//                public void finish() {
//                    // Toast.makeText(RegisterActivity.this,"倒计时结束",Toast.LENGTH_SHORT).show();
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            btnCode.setText("再次获取");
//                        }
//                    });
//                }
//            });
            helper.start();
            phone = etPhone.getText().toString().trim();
            //获取验证码
//            SMSSDK.getVerificationCode("86", phone);
            getSMSAuthCode(phone);
        }else{
//            showToast("请输入正确手机号");
            return;
        }

    }
    private void getSMSAuthCode(String phone) {
        showloginDialog("");
        Api.getInstance()
                .movieService.getSMSAuthCode(C.USER_MESSAGECODE,"ResetPwd",phone)
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<RegisterInfo>>() {
                    @Override
                    public void call(List<RegisterInfo> meInfos) {
                        showMessage("获取验证码成功");
                    }
                }, e -> {
                    showMessage(MessageFactory.getMessage(e));
                });
    }
    @OnClick(R.id.btnSubmit) void submitClick(){
        String number = etValideCode.getText().toString();
//        SMSSDK.submitVerificationCode("86",phone,number);
        String uPassword = setPasswd.getText().toString().trim();
        if (AStringUtil.isEmpty(uPassword)) {
            showToast("密码不能为空");
            return ;
        }
        String okPwd = okPasswd.getText().toString().trim();
        if (AStringUtil.isEmpty(okPwd)) {
            showToast("确认密码不能为空");
            return ;
        }
        if(!uPassword.equals(okPwd)){
            showToast("两次输入密码不一致");
            return ;
        }

        showloginDialog(loading);
        presenter.userResetPwd(phone,uPassword,number);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(handler);
    }

    @Override
    protected void initView() {
        cannelDialog();
        handler = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE){
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                            }
                        });

                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast("验证码已发送");
                            }
                        });
                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){



                    }
                }else{
                    ((Throwable)data).printStackTrace();
                    Throwable throwable = (Throwable) data;
                    try {
                        JSONObject obj = new JSONObject(throwable.getMessage());
                        final String des = obj.optString("detail");
                        if (!TextUtils.isEmpty(des)){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showToast("短信验证失败");
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        SMSSDK.registerEventHandler(handler);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_register;
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
    private void showMessage(String message) {
        cannelDialog();
        showToast(message);
    }
}