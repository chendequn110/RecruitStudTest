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
 * 创建时间：2017/11/17 12:56
 * 修改人：chendequnn
 * 修改时间：2017/11/17 12:56
 * 修改备注：
 */
public class BindMailActivity extends BaseActivity {
    @BindView(R.id.btnSubmit)Button btnSubmit;
    @BindView(R.id.etMail)
    ClearEditText etMail;
    @OnClick(R.id.btnSubmit) void submitClick(){
        String mail = etMail.getText().toString();
        if (AStringUtil.isEmpty(mail)) {
            showToast("邮箱不能为空");
            return ;
        }

        showloginDialog("");
        Api.getInstance()
                .movieService.bindMail(C.USER_BINDMAIL, SpUtil.getMemberID(),mail,SpUtil.getToken())
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
        return R.layout.activity_bindmail;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return  false;
    }
    private void showMessage(String message) {
        cannelDialog();
        showToast(message);
    }
}