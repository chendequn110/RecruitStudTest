package com.tiandu.recruit.stud.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.api.exception.MessageFactory;
import com.tiandu.recruit.stud.base.BaseLazyFragment;
import com.tiandu.recruit.stud.base.utils.Logger;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.JobInfo;
import com.tiandu.recruit.stud.ui.adapter.JobAdapter;
import com.tiandu.recruit.stud.ui.job.JobDetailActivity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/9 13:03
 * 修改人：chendequnn
 * 修改时间：2017/11/9 13:03
 * 修改备注：
 */
public class JobFragment extends BaseLazyFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
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
    private LinearLayout llMoreFoor;
    private String type="00";



    @Override
    protected void initViewsAndEvents() {
        setupView();
    }

    public View getFooterView() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_foot, null);
        llMoreFoor = ButterKnife.findById(view, R.id.llMoreFoor);
        llMoreFoor.setOnClickListener(this);
        return view;
    }

    private void setupView() {
        line1.setVisibility(View.VISIBLE);
        line2.setVisibility(View.INVISIBLE);
        line3.setVisibility(View.INVISIBLE);
        line4.setVisibility(View.INVISIBLE);
        swipeRefresh.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter = new JobAdapter());
        adapter.addFooterView(getFooterView());

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
    public void onRefresh() {
        Observable.timer(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (swipeRefresh != null && swipeRefresh.isRefreshing()) {
                        swipeRefresh.setRefreshing(false);
                        getOrdList(type);
//                        showProgress();
                    }
                });
    }


    private void getOrdList(String type) {
        Api.getInstance()
                .movieService.getJobInfo(C.USER_JOBINFO,type,SpUtil.getMemberID(),SpUtil.getToken())
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<JobInfo>>() {
                    @Override
                    public void call(List<JobInfo> infos) {
                        cannelMyDialog();
                        if (null != infos) {
                            List<JobInfo.AaDataBean> aaData = infos.get(0).getAaData();
                            adapter.setNewData(aaData);
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

        if (isUser()) {
            swipeRefresh.setRefreshing(true);
            onRefresh();
        }

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
                type="3";
                swipeRefresh.setRefreshing(true);
                onRefresh();
                break;
        }
    }

    @Override
    public void onClick(View view) {

    }
}