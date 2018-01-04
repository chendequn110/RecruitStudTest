package com.tiandu.recruit.stud.ui.fragment;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.api.exception.MessageFactory;
import com.tiandu.recruit.stud.base.BaseLazyFragment;
import com.tiandu.recruit.stud.base.utils.LocationUtils;
import com.tiandu.recruit.stud.base.utils.Logger;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.JobInfo;
import com.tiandu.recruit.stud.data.event.CityEvent;
import com.tiandu.recruit.stud.ui.adapter.JobAdapter;
import com.tiandu.recruit.stud.ui.job.JobDetailActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static android.content.Context.LOCATION_SERVICE;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/9 13:03
 * 修改人：chendequnn
 * 修改时间：2017/11/9 13:03
 * 修改备注：
 */
public class JobFragment extends BaseLazyFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener,BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.tvJob1)
    TextView tvJob1;
    @BindView(R.id.tvJob2)
    TextView tvJob2;
    @BindView(R.id.tvJob3)
    TextView tvJob3;
    @BindView(R.id.tvJob4)
    TextView tvJob4;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.line4)
    View line4;

    private JobAdapter adapter = null;
//    private LinearLayout llMoreFoor;
    private String type="00";
    private int page=0;
    private int totalpage = 0;
    private List<JobInfo.AaDataBean> aaData;


    @Override
    protected void initViewsAndEvents() {
        // 初始化高德
//        initLocation();
//        startLocation();
        setupView();
    }

//    public View getFooterView() {
//        View view = LayoutInflater.from(context).inflate(R.layout.fragment_foot, null);
//        llMoreFoor = ButterKnife.findById(view, R.id.llMoreFoor);
//        llMoreFoor.setOnClickListener(this);
//        return view;
//    }

    private void setupView() {
        line1.setVisibility(View.VISIBLE);
        line2.setVisibility(View.INVISIBLE);
        line3.setVisibility(View.INVISIBLE);
        line4.setVisibility(View.INVISIBLE);
        swipeRefresh.setOnRefreshListener(this);
//        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter = new JobAdapter());
//        adapter.addFooterView(getFooterView());
        adapter.setOnLoadMoreListener(this, recyclerView);

        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                JobInfo.AaDataBean item = adapter.getItem(i);
                        Bundle bundle=new Bundle();
                        bundle.putInt("ID",item.getID());
                        readyGo(JobDetailActivity.class,bundle);
            }
        });

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                JobInfo.AaDataBean item = adapter.getItem(i);
                        Bundle bundle=new Bundle();
                        bundle.putInt("ID",item.getID());
                        readyGo(JobDetailActivity.class,bundle);

            }
        });
    }



    @Override
    public void onLoadMoreRequested() {
        page++;
        adapter.setEnableLoadMore(true);
        swipeRefresh.setEnabled(false);

        recyclerView.post(() -> {
            if (page*10> totalpage) {
                adapter.loadMoreEnd(false);
            } else {
                adapter.setEnableLoadMore(true);

                Observable.timer(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                        .subscribe(aLong -> {
                            getOrdList(type);
                        });
            }
        });
        swipeRefresh.setEnabled(true);
    }
    @Override
    public void onRefresh() {
        page = 0;
        adapter.setEnableLoadMore(false);
        Observable.timer(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    getOrdList(type);
                    if (swipeRefresh != null && swipeRefresh.isRefreshing()) {
                        swipeRefresh.setRefreshing(false);
                    }
                });
    }


    private void getOrdList(String type) {
        Api.getInstance()
                .movieService.getJobInfo(C.USER_JOBINFO,type,SpUtil.getMemberID(),SpUtil.getToken(),"上海市","","",page+"")
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<JobInfo>>() {
                    @Override
                    public void call(List<JobInfo> infos) {
                        cannelMyDialog();
                        if (null != infos) {
                            aaData =  infos.get(0).getAaData();
                            totalpage=infos.get(0).getITotalRecords();
                            if (page == 0) {
                                adapter.setNewData(aaData);
                            } else {
                                adapter.addData(aaData);
                                adapter.loadMoreComplete();
                            }
                        }
                    }
                }, e -> {
                    adapter.setNewData(null);
                    showMessage(MessageFactory.getMessage(e));
                });
    }

    private void showMessage(String message) {
        cannelMyDialog();
        showToast(message);
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_job;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    private View view;

    @Override
    protected void onFirstUserVisible() {
        view = getActivity().findViewById(R.id.statusBar);
        if (view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
        }

//        if (isUser()) {
////            showMyDialog("");
//            getOrdList(type);
//        }
        if (isUser()) {
            swipeRefresh.setRefreshing(true);
            onRefresh();
        }
    }

    @Override
    protected void onUserVisible() {
        if (view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
        }

//        if (isUser()) {
//            swipeRefresh.setRefreshing(true);
//            onRefresh();
//        }

    }

    @Override
    protected void onFirstUserInvisible() {

    }

    @Override
    protected void onUserInvisible() {

    }
    @OnClick({R.id.tvJob1, R.id.tvJob2,R.id.tvJob3,R.id.tvJob4})
    void onSelectClick(View v) {
        switch (v.getId()) {
            case R.id.tvJob1:
                Logger.d("点击1");
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.INVISIBLE);
                line3.setVisibility(View.INVISIBLE);
                line4.setVisibility(View.INVISIBLE);
                type="00";
                swipeRefresh.setRefreshing(true);
                onRefresh();
                break;
            case R.id.tvJob2:
                Logger.d("点击2");
                line1.setVisibility(View.INVISIBLE);
                line2.setVisibility(View.VISIBLE);
                line3.setVisibility(View.INVISIBLE);
                line4.setVisibility(View.INVISIBLE);
                type="01";
                swipeRefresh.setRefreshing(true);
                onRefresh();
                break;
            case R.id.tvJob3:
                Logger.d("点击3");
                line1.setVisibility(View.INVISIBLE);
                line2.setVisibility(View.INVISIBLE);
                line3.setVisibility(View.VISIBLE);
                line4.setVisibility(View.INVISIBLE);
                type="02";
                swipeRefresh.setRefreshing(true);
                onRefresh();
                break;
            case R.id.tvJob4:
                Logger.d("点击4");
                line1.setVisibility(View.INVISIBLE);
                line2.setVisibility(View.INVISIBLE);
                line3.setVisibility(View.INVISIBLE);
                line4.setVisibility(View.VISIBLE);
                type="03";
                swipeRefresh.setRefreshing(true);
                onRefresh();
                break;
        }
    }

    @Override
    public void onClick(View view) {

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
        locationClient = new AMapLocationClient(context);
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
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
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
    public static String locatioCity;
    public static String locatioCity2;
    private DecimalFormat df = new DecimalFormat("0.000000");
    private int errorCount;

    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (null != loc) {
                latitude = loc.getLatitude();//纬
                longitude = loc.getLongitude();//经
                locatioCity = loc.getProvince();
                locatioCity2 = loc.getCity();

                //转百度
                double[] doubles = LocationUtils.gaoDeToBaidu(longitude, latitude);
                longitude = Double.valueOf(df.format(doubles[0]));  //经度
                latitude = Double.valueOf(df.format(doubles[1]));   //纬度

                //解析定位结果
                Logger.d("result>>>>" +  LocationUtils.getLocationStr(loc));
                Logger.d("mylatitude>>>>" + latitude);
                Logger.d("mylongitude>>>>" + longitude);

                if (loc.getErrorCode() != 0) { //失败
                    Logger.d("loc.getErrorCode() != 0");
                    errorCount++;
                    if (errorCount == 6) {  //30秒后没有授权
                        locatioCity = "上海";
                        longitude = 121.39474;
                        latitude = 31.164691;
                    }
                }
            } else {
                //showToast("定位失败");
                locatioCity = "上海";
                longitude = 121.39474;
                latitude = 31.164691;

            }

            if (null != locatioCity && !locatioCity.isEmpty()) {
                EventBus.getDefault().post(new CityEvent(locatioCity));
                stopLocation();
            }

//            showSchoolList();
//            stopLocation();
        }
    };


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLatePlanEvent(CityEvent event) {
        if (null != event.getCity()) {
            locatioCity = event.getCity();
            Logger.d("locatioCity>>>" + locatioCity);
            //TODO 修改城市重新从第一页获取
            page = 0;
            showMyDialog("");
            aaData.clear();
            getOrdList(type);
        }
    }

    private LocationManager lm;
    private void isOpenGPSServise() {
        //得到系统的位置服务，判断GPS是否激活
        lm=(LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
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
    public void onDestroy() {
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
}