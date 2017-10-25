package com.tiandu.recruit.stud.ui;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.view.ClearEditText;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/10/23 23:26
 * 修改人：chendequnn
 * 修改时间：2017/10/23 23:26
 * 修改备注：
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.btnSubmit)Button btnSubmit;
    @BindView(R.id.btnCode)Button btnCode;
    @BindView(R.id.etPhone)
    ClearEditText etPhone;
    @BindView(R.id.etValideCode)
    EditText etValideCode;
    @BindView(R.id.setPasswd)
    ClearEditText setPasswd;
    @BindView(R.id.okPasswd) ClearEditText okPasswd;
    private EventHandler handler;
    private String phone;



    @OnClick(R.id.btnCode) void getCodeClick(){
        phone = etPhone.getText().toString().trim();
        //获取验证码
        SMSSDK.getVerificationCode("86", phone);
    }
    @OnClick(R.id.btnSubmit) void submitClick(){
        phone = etPhone.getText().toString().trim();
        String number = etValideCode.getText().toString();
        SMSSDK.submitVerificationCode("86",phone,number);
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
                                showToast("验证成功");
//                                Toast.makeText(RegisterActivity.this,"验证成功",Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intentntenttent(MainActivity.this,Main2Activity.class);
//                                startActivity(intent);
                            }
                        });

                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast("验证码已发送");
//                                Toast.makeText(RegisterActivity.this,"验证码已发送",Toast.LENGTH_SHORT).show();
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
                                    showToast("提交错误信息");
//                                    Toast.makeText(RegisterActivity.this,"提交错误信息", Toast.LENGTH_SHORT).show();
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

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return  false;
    }
}