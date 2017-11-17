package com.tiandu.recruit.stud.ui.userInfo;

import android.widget.Button;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.api.exception.MessageFactory;
import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.base.utils.AStringUtil;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.MeInfo;
import com.tiandu.recruit.stud.view.ClearEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/17 13:24
 * 修改人：chendequnn
 * 修改时间：2017/11/17 13:24
 * 修改备注：
 */
public class CertificationActivity extends BaseActivity {
    @BindView(R.id.etRealName)
    ClearEditText etRealName;
    @BindView(R.id.etIDNumber)
    ClearEditText etIDNumber;
    @BindView(R.id.etBank)
    ClearEditText etBank;
    @BindView(R.id.etBankNum)
    ClearEditText etBankNum;
    @BindView(R.id.btnSubmit)Button btnSubmit;
    @OnClick(R.id.btnSubmit) void submitClick(){
        String realName = etRealName.getText().toString();
        if (AStringUtil.isEmpty(realName)) {
            showToast("真实姓名不能为空");
            return ;
        }
        String idNumber = etIDNumber.getText().toString();
        if (AStringUtil.isEmpty(idNumber)) {
            showToast("身份证不能为空");
            return ;
        }
        String bank = etBank.getText().toString();
        if (AStringUtil.isEmpty(bank)) {
            showToast("开户银行不能为空");
            return ;
        }
        String bankNum = etBankNum.getText().toString();
        if (AStringUtil.isEmpty(bankNum)) {
            showToast("银行卡号不能为空");
            return ;
        }

        showloginDialog("");
        Api.getInstance()
                .movieService.upDataUserRealInfo(C.USER_UPDATAUSERREALINFO, SpUtil.getMemberID(),realName,idNumber,bank,bankNum,SpUtil.getToken())
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<MeInfo>>() {
                    @Override
                    public void call(List<MeInfo> meInfos) {
                        showMessage("认证成功");
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
        return R.layout.activity_certificationinfo;
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