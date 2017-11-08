package com.tiandu.recruit.stud.ui.userInfo;

import android.view.View;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.api.exception.MessageFactory;
import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.base.utils.AStringUtil;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.MeInfo;
import com.tiandu.recruit.stud.data.entity.UserInfo;

import java.util.List;

import rx.functions.Action1;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/10/23 23:59
 * 修改人：chendequnn
 * 修改时间：2017/10/23 23:59
 * 修改备注：
 */
public class MeActivity extends BaseActivity {
    @Override
    protected void initView() {
        cannelDialog();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_userinfo;
    }

    @Override
    protected void initPresenter() {
        getInitnetData();
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    private void getInitnetData() {
        showloginDialog("");
        Api.getInstance()
                .movieService.getUserInfo(C.USER_USERINFO_PATH,SpUtil.getAccount(), SpUtil.getToken())
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<MeInfo>>() {
                    @Override
                    public void call(List<MeInfo> meInfos) {
                        cannelDialog();
                        if (null != meInfos) {
                            meInfos.get(0).getBindEmail();
                        }
                    }
                }, e -> {
                    cannelDialog();
                    showMessage(MessageFactory.getMessage(e));
                });

    }

    private void showMessage(String message) {
        cannelDialog();
        showToast(message);
    }
}