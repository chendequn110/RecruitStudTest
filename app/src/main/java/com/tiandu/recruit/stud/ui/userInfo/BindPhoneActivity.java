package com.tiandu.recruit.stud.ui.userInfo;

import android.widget.Button;
import android.widget.EditText;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.api.exception.MessageFactory;
import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.base.utils.AStringUtil;
import com.tiandu.recruit.stud.base.utils.CountDownButtonHelper;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.RegisterInfo;
import com.tiandu.recruit.stud.view.ClearEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/17 12:35
 * 修改人：chendequnn
 * 修改时间：2017/11/17 12:35
 * 修改备注：
 */
public class BindPhoneActivity extends BaseActivity {
    @BindView(R.id.btnSubmit)Button btnSubmit;
    @BindView(R.id.btnCode)Button btnCode;
    @BindView(R.id.etPhone)
    ClearEditText etPhone;
    @BindView(R.id.etValideCode)
    EditText etValideCode;
    private String phone;
    @OnClick(R.id.btnCode) void getCodeClick(){
        phone = etPhone.getText().toString().trim();
        if(AStringUtil.isPhone(phone)){
            CountDownButtonHelper helper = new CountDownButtonHelper(btnCode,"",60,1);
            helper.start();


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
                .movieService.getSMSAuthCode(C.USER_MESSAGECODE,"Reg",phone)
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
        if (AStringUtil.isEmpty(number)) {
            showToast("验证码不能为空");
            return ;
        }
        String phone=etPhone.getText().toString().trim();
        if (AStringUtil.isEmpty(phone)) {
            showToast("手机号不能为空");
            return ;
        }
        showloginDialog("");
        Api.getInstance()
                .movieService.bindPhone(C.USER_BINDPHONE, SpUtil.getMemberID(),phone ,number,SpUtil.getToken())
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<RegisterInfo>>() {
                    @Override
                    public void call(List<RegisterInfo> meInfos) {
                        showMessage("绑定成功");
                    }
                }, e -> {
                    cannelDialog();
                    showMessage(MessageFactory.getMessage(e));
                });

    }
    @Override
    protected void initView() {
        cannelDialog();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bindphone;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }
    private void showMessage(String message) {
        cannelDialog();
        showToast(message);
    }
}