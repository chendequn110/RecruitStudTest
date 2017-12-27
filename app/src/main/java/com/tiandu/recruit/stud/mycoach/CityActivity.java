package com.tiandu.recruit.stud.mycoach;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.location.LocationManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.base.utils.LocationUtils;
import com.tiandu.recruit.stud.base.utils.Logger;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.event.CityEvent;
import com.tiandu.recruit.stud.data.event.LocationEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/13.
 */

public class CityActivity extends BaseActivity {

    @BindView(R.id.tvCityName) TextView tvCityName;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private CityAdapter adapter = null;
    private String cityName;


    public static final String[] allCityName = {"北京市","天津市","上海市","重庆市","河北省","山西省","辽宁省","吉林省","黑龙江省","江苏省","浙江省","安徽省","福建省","江西省","山东省","河南省","湖北省","湖南省","广东省","海南省","四川省","贵州省","云南省","陕西省","甘肃省","青海省","台湾省","内蒙古自治区","广西壮族自治区","西藏自治区","宁夏回族自治区","新疆维吾尔自治区","香港特别行政区","澳门特别行政区"};

    private List<String> cityInfos = new ArrayList<>();

    @Override
    protected void initView() {
        cannelDialog();

        cityName = getIntent().getStringExtra(C.ADDRESS_CITY);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter = new CityAdapter());
        //recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));   //部分手机空指针
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                String item = adapter.getItem(i);
                EventBus.getDefault().post(new CityEvent(item));
                finish();
            }
        });

        for (String city : allCityName) {
            cityInfos.add(city);
        }
        adapter.setNewData(cityInfos);

        if (null != cityName && !cityName.isEmpty()) {
        } else {
            tvCityName.setText("定位失败，点击刷新");
        }

        initLocation();
        startLocation();

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_city;
    }

    @Override
    protected void initPresenter() {

    }

    @OnClick(R.id.tvCityName) void onMyClick() {
        String s = tvCityName.getText().toString();
        if (s.equals("定位失败，点击刷新")) {
            startLocation();
        } else {
            EventBus.getDefault().post(new LocationEvent(longitude,latitude));
            EventBus.getDefault().post(new CityEvent(locatioCity));
            finish();
        }
    }



    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }



    /**
     * 高德
     */

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();
    public static double longitude;
    public static double latitude;

    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(this);
        //设置定位参数
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(5000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(1000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(false);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(true);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(false); //可选，设置是否使用缓存定位，默认为true
        return mOption;

    }

    public void startLocation() {
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 定位监听
     */

    private String locatioCity;
    private DecimalFormat df = new DecimalFormat("0.000000");

    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (null != loc) {
                latitude = loc.getLatitude();//纬
                longitude = loc.getLongitude();//经

                //转百度
                double[] doubles = LocationUtils.gaoDeToBaidu(longitude, latitude);
                longitude = Double.valueOf(df.format(doubles[0]));  //经度
                latitude = Double.valueOf(df.format(doubles[1]));   //纬度


                locatioCity = loc.getCity();

                tvCityName.setText(locatioCity);
                //解析定位结果
                Logger.d("result>>>>" +  LocationUtils.getLocationStr(loc));
                Logger.d("mylatitude>>>>" + latitude);
                Logger.d("mylongitude>>>>" + longitude);

                if (loc.getErrorCode() != 0) { //失败
                    tvCityName.setText("定位失败，点击刷新");
                }

            } else {
                tvCityName.setText("定位失败，点击刷新");
            }

            stopLocation();
        }
    };

    private LocationManager lm;
    private void isOpenGPSServise() {
        //得到系统的位置服务，判断GPS是否激活
        lm=(LocationManager)getSystemService(LOCATION_SERVICE);
        boolean ok=lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!ok) {
            showToast("请开启位置服务,允许访问我的位置信息");
            Intent intent=new Intent();
            intent.setAction(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    public void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            AssetManager e = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(e.open(fileName)));

            String line;
            while((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
