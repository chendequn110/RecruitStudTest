package com.tiandu.recruit.stud.ui.userInfo;

import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.citywheel.CityPickerView;
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
import com.tiandu.recruit.stud.view.WheelView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
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
    private static final String[] PLANETS = new String[]{"男", "女"};
    @BindView(R.id.etMemberID)
    ClearEditText etMemberID;
    @BindView(R.id.etMemberName)
    ClearEditText etMemberName;
    @BindView(R.id.etBindMobile)
    ClearEditText etBindMobile;
    @BindView(R.id.etBindEmail)
    ClearEditText etBindEmail;
    @BindView(R.id.etBindWechat)
    ClearEditText etBindWechat;
    @BindView(R.id.etBindQQ)
    ClearEditText etBindQQ;
    @BindView(R.id.etIDNumber)
    ClearEditText etIDNumber;
    @BindView(R.id.etRealName)
    ClearEditText etRealName;
    @BindView(R.id.etParentID)
    ClearEditText etParentID;
    @BindView(R.id.tvBirthdate)
    TextView tvBirthdate;
    @BindView(R.id.tvGender)
    TextView tvGender;
    @BindView(R.id.tvProvince)
    TextView tvProvince;
    @BindView(R.id.btnUpdata)
    Button btnUpdata;
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
                .movieService.getUserInfo(C.USER_USERINFO_PATH,SpUtil.getMemberID(), SpUtil.getToken())
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<MeInfo>>() {
                    @Override
                    public void call(List<MeInfo> meInfos) {
                        cannelDialog();
                        if (null != meInfos) {
                            etMemberID.setText( meInfos.get(0).getMemberID());
                            etMemberName.setText(meInfos.get(0).getMemberName());
                            etBindMobile.setText( meInfos.get(0).getBindMobile());
                            etBindEmail.setText(meInfos.get(0).getBindEmail());
                            etBindWechat.setText( meInfos.get(0).getBindWechat());
                            etBindQQ.setText(meInfos.get(0).getBindQQ());
                            etIDNumber.setText( meInfos.get(0).getIDNumber());
                            etRealName.setText(meInfos.get(0).getRealName());
                            etParentID.setText( meInfos.get(0).getParentID());
                            tvBirthdate.setText(meInfos.get(0).getBirthDate());
                            tvGender.setText(meInfos.get(0).getGender());
                            tvProvince.setText(meInfos.get(0).getProvince());
                        }else {
                            showToast("数据为空");
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
    @OnClick(R.id.btnUpdata) void upDataUserInfo() {
        String memberName=etMemberName.getText().toString();
        if(AStringUtil.isEmpty(memberName)){
            showToast("用户名不能为空");
            return;
        }
        String BindMobile=etBindMobile.getText().toString();
        if(AStringUtil.isEmpty(BindMobile)){
            showToast("绑定手机不能为空");
            return;
        }
        String BindEmail=etBindEmail.getText().toString();
        if(AStringUtil.isEmpty(BindEmail)){
            showToast("绑定邮箱不能为空");
            return;
        }
        String BindWechat=etBindWechat.getText().toString();
        if(AStringUtil.isEmpty(BindWechat)){
            showToast("绑定微信不能为空");
            return;
        }
        String BindQQ=etBindQQ.getText().toString();
        if(AStringUtil.isEmpty(BindQQ)){
            showToast("绑定QQ不能为空");
            return;
        }
        String IDNumber=etIDNumber.getText().toString();
        if(AStringUtil.isEmpty(IDNumber)){
            showToast("绑定身份证不能为空");
            return;
        }
        String RealName=etRealName.getText().toString();
        if(AStringUtil.isEmpty(RealName)){
            showToast("真实姓名不能为空");
            return;
        }
        String Gender=tvGender.getText().toString();
        if(AStringUtil.isEmpty(Gender)){
            showToast("性别不能为空");
            return;
        }
        String Birthdate=tvBirthdate.getText().toString();
        if(AStringUtil.isEmpty(Birthdate)){
            showToast("生日不能为空");
            return;
        }
        String Province=tvProvince.getText().toString();
        if(AStringUtil.isEmpty(Province)){
            showToast("性别不能为空");
            return;
        }
        String ParentID=etParentID.getText().toString();
        if(AStringUtil.isEmpty(ParentID)){
            showToast("上级代理不能为空");
            return;
        }
            showloginDialog("更新资料中...");
            Api.getInstance()
                    .movieService.upDataUserInfo(C.USER_UPDATAUSERINFO_PATH,"Edit",SpUtil.getMemberID(),memberName,BindMobile,BindEmail,BindWechat,BindQQ,IDNumber,RealName,Gender,Birthdate,Province,"",ParentID,SpUtil.getToken())
                    .compose(RxSchedulers.io_main())
                    .compose(RxSchedulers.sTransformer())
                    .subscribe(MeInfo -> {
                        showMessage("会员更新成功");
                    }, e -> {
                        showMessage(MessageFactory.getMessage(e));
                    });
    }
    @OnClick(R.id.tvBirthdate) void setBrithdata(){
        DatePickDialog dialog = new DatePickDialog(this);
        //设置上下年分限制
        dialog.setYearLimt(60);
        //设置标题
        dialog.setTitle("选择出生日前");
        //设置类型
        dialog.setType(DateType.TYPE_YMD);
        //设置消息体的显示格式，日期格式
        dialog.setMessageFormat("yyyy-MM-dd");
        //设置选择回调
        dialog.setOnChangeLisener(null);
        //设置点击确定按钮回调
        dialog.setOnSureLisener(new OnSureLisener() {
            @Override
            public void onSure(Date date) {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String str=sdf.format(date);
                tvBirthdate.setText(str);
            }
        });
        dialog.show();
    }
    @OnClick(R.id.tvProvince) void setProvince(){
        //详细属性设置，如果不需要自定义样式的话，可以直接使用默认的，去掉下面的属性设置，直接build()即可。
        CityConfig cityConfig = new CityConfig.Builder(this)
                .title("选择地区")
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("直辖市")
                .city("上海")
                .district("徐汇区")
                .visibleItemsCount(5)
                .provinceCyclic(true)
                .cityCyclic(true)
                .districtCyclic(true)
                .itemPadding(5)
                .setCityInfoType(CityConfig.CityInfoType.BASE)
                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)
                .build();

//配置属性
        CityPickerView cityPicker = new CityPickerView(cityConfig);
        cityPicker.show();

//监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPickerView.OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {

                //ProvinceBean 省份信息
                //CityBean     城市信息
                //DistrictBean 区县信息
                tvProvince.setText(province.getName()+city.getName()+district.getName());

            }

            @Override
            public void onCancel() {

            }
        });

    }
    @OnClick(R.id.tvGender) void setGender(){
        View outerView = LayoutInflater.from(this).inflate(R.layout.item_whellview, null);
        WheelView wv = (WheelView) outerView.findViewById(R.id.main_wv);
        wv.setOffset(2);
        wv.setItems(Arrays.asList(PLANETS));
        wv.setSeletion(3);
        wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                tvGender.setText(item);
            }
        });

        new AlertDialog.Builder(this)
                .setTitle("请选择性别")
                .setView(outerView)
                .setPositiveButton("确定", null)
                .show();

    }
}