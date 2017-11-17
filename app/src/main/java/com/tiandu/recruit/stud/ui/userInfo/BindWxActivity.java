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
 * 创建时间：2017/11/17 15:14
 * 修改人：chendequnn
 * 修改时间：2017/11/17 15:14
 * 修改备注：
 */
public class BindWxActivity extends BaseActivity {
    @BindView(R.id.btnSubmit)Button btnSubmit;
    @BindView(R.id.etWX)
    ClearEditText etWX;
    @OnClick(R.id.btnSubmit) void submitClick(){
        String wx = etWX.getText().toString();
        if (AStringUtil.isEmpty(wx)) {
            showToast("微信号不能为空");
            return ;
        }

        showloginDialog("");
        Api.getInstance()
                .movieService.upDataUserInfo(C.USER_UPDATAUSERINFO_PATH,"Edit",SpUtil.getMemberID(),"","","",wx,"","","","","","","","",SpUtil.getToken())
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<MeInfo>>() {
                    @Override
                    public void call(List<MeInfo> meInfos) {
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
        return R.layout.activity_bindwx;
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