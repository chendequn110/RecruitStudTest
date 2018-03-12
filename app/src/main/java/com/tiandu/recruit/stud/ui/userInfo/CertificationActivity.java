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
import com.tiandu.recruit.stud.data.event.ErrorEvent;
import com.tiandu.recruit.stud.view.ClearEditText;

import org.greenrobot.eventbus.EventBus;

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
    @BindView(R.id.etParent)
    ClearEditText etParent;
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
                    getInitnetData();
                });

    }
    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        cannelDialog();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_certificationinfo;
    }

    @Override
    protected void initPresenter() {
        getInitnetData();
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }
    private void showMessage(String message) {
        cannelDialog();
        showToast(message);
    }
    private void getInitnetData() {
        showloginDialog("");
        Api.getInstance()
                .movieService.getUserInfo(C.USER_USERINFO_PATH,SpUtil.getMemberID(), SpUtil.getToken())
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<MeInfo>>() {
                    @Override
                    public void call(List<MeInfo> meInfos) {
                        cannelDialog();
                        if (null != meInfos) {
                            etParent.setText( meInfos.get(0).getParentID());
                            etRealName.setText( meInfos.get(0).getRealName());
                            etIDNumber.setText( AStringUtil.makeId(meInfos.get(0).getIDNumber()));
                            etBank.setText( meInfos.get(0).getBankName());
                            etBankNum.setText(AStringUtil.makeCardId(meInfos.get(0).getBankAccount()));
                            if(meInfos.get(0).getBankName().equals("")||meInfos.get(0).getBankAccount().equals("")||
                                    meInfos.get(0).getBankName()==null||meInfos.get(0).getBankAccount()==null){
                                EventBus.getDefault().post(new ErrorEvent("请尽快完善您的银行卡信息，否则我们将无法正常发放您的奖励金额！"));
                            }else{
                                EventBus.getDefault().post(new ErrorEvent(""));
                            }
                        }else {
                            showToast("数据为空");
                        }
                    }
                }, e -> {
                    cannelDialog();
                    showMessage(MessageFactory.getMessage(e));
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}