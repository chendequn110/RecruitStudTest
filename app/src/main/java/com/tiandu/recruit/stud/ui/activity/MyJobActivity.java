package com.tiandu.recruit.stud.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.api.exception.MessageFactory;
import com.tiandu.recruit.stud.base.BaseActivity;
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
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2018/3/11 16:37
 * 修改人：chendequnn
 * 修改时间：2018/3/11 16:37
 * 修改备注：
 */
public class MyJobActivity extends BaseActivity  implements SwipeRefreshLayout.OnRefreshListener,View.OnClickListener,BaseQuickAdapter.RequestLoadMoreListener{
    private JobAdapter adapter = null;
    private int page=0;
    private int totalpage = 0;
    private String IsApply="";
    private String IsFavorite="1";
    private String IsBrowse="";
    private List<JobInfo.AaDataBean> aaData;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.mFilterContentView)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.tvMyJob1)
    TextView tvJob1;
    @BindView(R.id.tvMyJob2)
    TextView tvJob2;
    @BindView(R.id.tvMyJob3)
    TextView tvJob3;
    @BindView(R.id.line2)
    View line2;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.line3)
    View line3;
    @OnClick({R.id.tvMyJob1, R.id.tvMyJob2,R.id.tvMyJob3})
    void onSelectClick(View v) {
        switch (v.getId()) {
            case R.id.tvMyJob1:
                Logger.d("点击1");
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.INVISIBLE);
                line3.setVisibility(View.INVISIBLE);
                IsApply="";
                IsFavorite="1";
                IsBrowse="";
                swipeRefresh.setRefreshing(true);
                onRefresh();
                break;
            case R.id.tvMyJob2:
                Logger.d("点击2");
                line1.setVisibility(View.INVISIBLE);
                line2.setVisibility(View.VISIBLE);
                line3.setVisibility(View.INVISIBLE);
                IsApply="1";
                IsFavorite="";
                IsBrowse="";
                swipeRefresh.setRefreshing(true);
                onRefresh();
                break;
            case R.id.tvMyJob3:
                Logger.d("点击3");
                line1.setVisibility(View.INVISIBLE);
                line2.setVisibility(View.INVISIBLE);
                line3.setVisibility(View.VISIBLE);
                swipeRefresh.setRefreshing(true);
                IsBrowse="1";
                IsApply="";
                IsFavorite="";
                onRefresh();
                break;
        }
    }
    @Override
    protected void initView() {
        cannelDialog();
        setupView();
        getOrdList("");
    }
    private void setupView() {
        line1.setVisibility(View.VISIBLE);
        line2.setVisibility(View.INVISIBLE);
        line3.setVisibility(View.INVISIBLE);
        swipeRefresh.setOnRefreshListener(this);
//        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter = new JobAdapter());
//        adapter.addFooterView(getFooterView());
        adapter.setOnLoadMoreListener(MyJobActivity.this, recyclerView);

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
    protected int getLayoutRes() {
        return R.layout.activity_myjob;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
            return  false;
    }

    @Override
    public void onRefresh() {
        page = 0;
        adapter.setEnableLoadMore(false);
        Observable.timer(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    getOrdList("");
                    if (swipeRefresh != null && swipeRefresh.isRefreshing()) {
                        swipeRefresh.setRefreshing(false);
                    }
                });
    }
    private void getOrdList(String type) {
        Api.getInstance()
                .movieService.getJobInfo(C.USER_JOBINFO,"",SpUtil.getMemberID(),SpUtil.getToken(),"","","",IsFavorite,IsApply,IsBrowse,page+"")
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<JobInfo>>() {
                    @Override
                    public void call(List<JobInfo> infos) {
                        cannelDialog();
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
        cannelDialog();
        showToast(message);
    }

    @Override
    public void onClick(View view) {

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
                            getOrdList("");
                        });
            }
        });
        swipeRefresh.setEnabled(true);
    }
}