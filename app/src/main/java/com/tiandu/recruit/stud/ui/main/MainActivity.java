package com.tiandu.recruit.stud.ui.main;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.citywheel.CityPickerView;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.base.BaseAppManager;
import com.tiandu.recruit.stud.base.BaseLazyFragment;
import com.tiandu.recruit.stud.data.entity.VersionInfo;
import com.tiandu.recruit.stud.data.event.AreaEvent;
import com.tiandu.recruit.stud.data.event.CityEvent;
import com.tiandu.recruit.stud.data.event.PhotoEvent;
import com.tiandu.recruit.stud.ui.adapter.FragmentAdapter;
import com.tiandu.recruit.stud.ui.fragment.FeeFragment;
import com.tiandu.recruit.stud.ui.fragment.HomeFragment;
import com.tiandu.recruit.stud.ui.fragment.JobFragment;
import com.tiandu.recruit.stud.ui.fragment.UserFragment;
import com.tiandu.recruit.stud.view.WheelView;
import com.tiandu.recruit.stud.view.XViewPager;
import com.tiandu.recruit.stud.view.tabstrip.HomeNavigateTab;
import com.tiandu.recruit.stud.view.tabstrip.HomeNavigateTabIndicator;
import com.zaaach.citypicker.CityPickerActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;

public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View,
        HomeNavigateTabIndicator.OnTabChangeListener {
    @BindView(R.id.viewPager)
    XViewPager viewPager;
    @BindView(R.id.tabStrip)
    HomeNavigateTabIndicator tabStrip;
    @BindView(R.id.llContainer)
    LinearLayout container;
    @BindView(R.id.ll_btn_screen)
    LinearLayout ll_btn_screen;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvAddress2)
    TextView tvAddress2;
    private int position = 0;
    private FragmentAdapter adapter = null;
    private List<BaseLazyFragment> mFragments = new ArrayList<>();
    private String mTitles[] = {"首页", "招聘", "本人账户", "我的"};
    public static String city="上海";
    private AlertDialog.Builder builder;
    private static final int REQUEST_CODE_PICK_CITY = 0;
    private static  String[] AREA = new String[]{"全部","黄浦区","卢湾区","徐汇区","长宁区","静安区","普陀区","闸北区","虹口区","杨浦区","闵行区","宝山区","嘉定区","浦东新区","金山区","松江区","青浦区","南汇区","奉贤区","崇明县"};;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        cannelDialog();
        setTranslucent();
        setHomeEnable(false);
        initTabHost();

        if (true) {
            position = 0;
        }
        setCurrentViewPage();
        viewPager.setCurrentItem(position, false);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        presenter.setVM(this, mModel);
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @OnClick({R.id.tvAddress})void addressClick() {
        startActivityForResult(new Intent(MainActivity.this, CityPickerActivity.class),
                REQUEST_CODE_PICK_CITY);
//        startGPS();
//        Bundle bundle = new Bundle();
//        bundle.putString(C.ADDRESS_CITY,city);
//        readyGo(CityActivity.class,bundle);

    }
    @OnClick({R.id.tvAddress2})void address2Click() {
        View outerView = LayoutInflater.from(this).inflate(R.layout.item_whellview, null);
        WheelView wv = (WheelView) outerView.findViewById(R.id.main_wv);
        wv.setOffset(2);
        wv.setItems(Arrays.asList(AREA));
        wv.setSeletion(0);
        wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                tvAddress2.setText(item);
                EventBus.getDefault().post(new AreaEvent(item));
            }
        });

        new AlertDialog.Builder(this)
                .setTitle("请选择区域")
                .setView(outerView)
                .setPositiveButton("确定", null)
                .show();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLatePlanEvent(CityEvent event) {
        if (null != event.getCity()) {
            city = event.getCity();
            if (city.equals("北京")){
                tvAddress2.setText("区域");
                AREA =new String[]{"全部","西城区","崇文区","宣武区","朝阳区","丰台区","石景山区","海淀区","门头沟区","房山区","通州区","顺义区","昌平区","大兴区","怀柔区","平谷区","密云县","延庆县"};
            }else if(city.equals("上海")){
                tvAddress2.setText("区域");
                AREA =new String[]{"全部","黄浦区","卢湾区","徐汇区","长宁区","静安区","普陀区","闸北区","虹口区","杨浦区","闵行区","宝山区","嘉定区","浦东新区","金山区","松江区","青浦区","南汇区","奉贤区","崇明县"};
            }else if(city.equals("天津")){
                tvAddress2.setText("区域");
                AREA =new String[]{"全部","和平区","河东区","河西区","南开区","河北区","红桥区","塘沽区","汉沽区","大港区","东丽区","西青区","津南区","北辰区","武清区","宝坻区","宁河县","静海县","蓟县"};
            }else if(city.equals("重庆")){
                tvAddress2.setText("区域");
                AREA =new String[]{"全部","万州区","涪陵区","渝中区","大渡口区","江北区","沙坪坝区","九龙坡区","南岸区","北碚区","万盛区","双桥区","渝北区","巴南区","黔江区","长寿区","江津区","合川区","永川区","南川区","綦江县","潼南县","铜梁县","大足县","荣昌县","璧山县","梁平县","城口县","丰都县","垫江县","武隆县","忠县","开县","云阳县","奉节县","巫山县","巫溪县","石柱土家族自治县","秀山土家族苗族自治县","酉阳土家族苗族自治县","彭水苗族土家族自治县"};
            }else {
                tvAddress2.setText("区域");
                AREA =new String[]{};
            }
        } else {
            city = "上海";
        }
        tvAddress.setText(city);
    }
    @Override
    public void onTabChanged(int index) {
        position = index;
        if (position == 2 ) {
            tvAddress.setVisibility(View.INVISIBLE);
            tvAddress2.setVisibility(View.INVISIBLE);
            mToolbar.setVisibility(View.VISIBLE);
            setToolbarTitle(mTitles[index]);
            viewPager.setCurrentItem(position, false);
        } else if (position == 3) {
            mToolbar.setVisibility(View.GONE);
            setToolbarTitle(mTitles[index]);
            viewPager.setCurrentItem(position, false);
        }else if( position == 1) {
            tvAddress.setVisibility(View.VISIBLE);
            tvAddress2.setVisibility(View.VISIBLE);
            mToolbar.setVisibility(View.VISIBLE);
            setToolbarTitle(mTitles[index]);
            viewPager.setCurrentItem(position, false);
        }
        else {
            mToolbar.setVisibility(View.GONE);
            setToolbarTitle(mTitles[0]);
            viewPager.setCurrentItem(0, false);
        }
    }


    @Override
    public void showVersion(VersionInfo info) {

    }


    @Override
    public void showError(String message) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setTranslucent() {
        Window window = getWindow();
        //设置底部状态栏为系统预留空间
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //设置透明状态栏,这样才能让 ContentView 向上，不为顶部状态栏预留空间
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }
    }

    private void initTabHost() {
        tvRight.setVisibility(View.VISIBLE);
        tabStrip.setNavigateTab(new HomeNavigateTab(viewPager, this));
        viewPager.setAdapter(adapter = new FragmentAdapter(getSupportFragmentManager()));
        tabStrip.setOnTabChangeListener(this);
        mFragments.add(new HomeFragment());
        mFragments.add(new JobFragment());
        mFragments.add(new FeeFragment());
        mFragments.add(new UserFragment());
        viewPager.setEnableScroll(false);
        viewPager.setOffscreenPageLimit(mFragments.size());
        adapter.updateData(mFragments);
    }

    private void setCurrentViewPage() {
        setViewPage(position);
        setToolbarTitle(mTitles[position]);
    }

    public void setViewPage(int index) {
        if ((index < 0) || (tabStrip != null)
                && (tabStrip.getHomeNavigateTab() != null)
                && (index >= tabStrip.getHomeNavigateTab().getTabParams().size())) {
            index = 0;
        }

        tabStrip.setCurrSelectedIndex(index);
    }

    private long firstTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    cannelDialog();
                    long secondTime = System.currentTimeMillis();
                    if (secondTime - firstTime > 2000) {//如果两次按键时间间隔大于2秒，则不退出
                        showToast("再按一次退出程序");
                        firstTime = secondTime;//更新firstTime
                        return true;
                    } else {        //两次按键小于2秒时，退出应用
                        BaseAppManager.getInstance().clearAll();
                        System.exit(0);
                    }
                    break;
            }
            return super.onKeyUp(keyCode, event);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK){
            if (data != null){
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                EventBus.getDefault().post(new CityEvent(city));
//                tvAddress.setText(city);
            }
        }
        if (resultCode == Activity.RESULT_OK &&
                (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE)) {
            String photo = null;
            if (data != null) {
                photo = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS).get(0);
                PhotoEvent photoEvent=new PhotoEvent();
                photoEvent.setPhoto(photo);
                EventBus.getDefault().post(photoEvent);
            }
        }
    }
    private void startGPS(){
        //详细属性设置，如果不需要自定义样式的话，可以直接使用默认的，去掉下面的属性设置，直接build()即可。
        CityConfig cityConfig = new CityConfig.Builder(this)
                .title("选择地区")
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("直辖市")
                .city("上海")
                .district("徐汇区")
                .visibleItemsCount(7)
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
                tvAddress.setText(city.getName()+district.getName());

            }

            @Override
            public void onCancel() {

            }
        });
    }
}
