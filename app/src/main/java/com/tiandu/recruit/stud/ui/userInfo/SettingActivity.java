package com.tiandu.recruit.stud.ui.userInfo;

import android.app.DownloadManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.base.utils.Logger;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.base.utils.VersionManager;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.VersionInfo;
import com.tiandu.recruit.stud.ui.activity.AboutUSActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/21 16:02
 * 修改人：chendequnn
 * 修改时间：2017/11/21 16:02
 * 修改备注：
 */
public class SettingActivity extends BaseActivity {
    @BindView(R.id.tvGetSettingVersion)
    TextView tvGetSettingVersion;
    private String version;
    private AlertDialog.Builder builder;
    private DownloadManager downManager;
    @Override
    protected void initView() {
        cannelDialog();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initPresenter() {
        getVersion();
    }

    private void getVersion() {
        Api.getInstance()
                .movieService.getVersionInfo(C.USER_GETAPPVERSION,SpUtil.getMemberID(),SpUtil.getToken())
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<VersionInfo.DataBean>>() {
                    @Override
                    public void call(List<VersionInfo.DataBean> infos) {
                        version=infos.get(0).getVersionNo();
                        tvGetSettingVersion.setText("新版本:"+infos.get(0).getVersionNo());
                    }
                }, e -> {
                });
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @OnClick({R.id.llAboutUs})
    void OnAboutUsClick(View view) {
        readyGo(AboutUSActivity.class);
    }
    @OnClick({R.id.llSettingVersion})
    void OnSelectedClick(View view) {
        switch (view.getId()) {
            case R.id.llSettingVersion:
                VersionManager info = new VersionManager(this);
                int nowVersion = Integer.valueOf(info.getVersion().replace(".",""));
                int newVersion = Integer.valueOf(version.replace(".",""));
                Logger.d("now--"+nowVersion+"--new--"+newVersion);
                if (nowVersion < newVersion) {
                    if (null == builder) {
                        builder = new AlertDialog.Builder(this);
                        builder.setMessage("发现新版本v" + version + "，您需要更新");
                        builder.setPositiveButton("立即更新", (dialog13, which) -> {
                            dialog13.dismiss();
                            VersionManager versionManager = new VersionManager(this);

                            versionManager.downLoadApk(Api.API_DEV_URL+"/AppDownload?MemberID=" + SpUtil.getMemberID() + "&VersionNo=" + version + "&Token=" + SpUtil.getToken() + "");
//                            versionManager.downLoadApk("http://openbox.mobilem.360.cn/index/d/sid/3585242");
                        });
                        builder.setNegativeButton("放弃", (dialog12, which) -> {
                            dialog12.dismiss();
                            finish();
                        });
                        builder.setCancelable(false);
                        builder.setOnCancelListener(dialog1 -> {
                            dialog1.dismiss();
                            finish();
                        });
                        builder.show();
//                    }
                    }

//                showloginDialog("下载中...");
//                Api.getInstance().movieService.appDownload(C.USER_APPDOWNLOAD,SpUtil.getMemberID(),version,SpUtil.getToken()).enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        if (response!=null) {
//
//                            response.body();
//
//                            showMessage("下载完成");
//                        } else {
//                            showMessage("server contact failed");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                       showMessage(t.getMessage());
//                    }
//                });
                }else{
                    showToast("已经为最新版本,无需更新");
                }
                break;
        }
    }
    private void showMessage(String message) {
        cannelDialog();
        showToast(message);
    }
}