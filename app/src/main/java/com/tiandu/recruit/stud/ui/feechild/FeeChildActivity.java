package com.tiandu.recruit.stud.ui.feechild;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.api.exception.MessageFactory;
import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.FeeChildInfo;
import com.tiandu.recruit.stud.ui.adapter.FeeChildAdapter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/10 10:43
 * 修改人：chendequnn
 * 修改时间：2017/11/10 10:43
 * 修改备注：
 */
public class FeeChildActivity extends BaseActivity  implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener,BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    private FeeChildAdapter adapter = null;
//    private LinearLayout llMoreFoor;
    private int page=0;
    private int totalpage = 0;

    private String planId;

    @Override
    protected void initView() {
        cannelDialog();
        setupView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_feechild;
    }

    @Override
    protected void initPresenter() {
        Bundle mbundle=getIntent().getExtras();
        planId = mbundle.getString("PlanID");
        getInitnetData();
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }
    private void setupView() {
        swipeRefresh.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter = new FeeChildAdapter());
//        adapter.addFooterView(getFooterView());
        adapter.setOnLoadMoreListener(this, recyclerView);
        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {


            }
        });

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }
        });
    }
    private void getInitnetData() {
        showloginDialog("");
        Api.getInstance()
                .movieService.getFeeChild(C.USER_FEECHILD,SpUtil.getMemberID(),planId, SpUtil.getToken(),page+"")
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<FeeChildInfo>>() {
                    @Override
                    public void call(List<FeeChildInfo> infos) {
                        cannelDialog();
                        if (null != infos) {
                            List<FeeChildInfo.AaDataBean> aaData=infos.get(0).getAaData();
                            totalpage=infos.get(0).getITotalRecords();
                            if (page == 0) {
                                adapter.setNewData(aaData);
                            } else {
                                adapter.addData(aaData);
                            }
                        }else {
                            showToast("数据为空");
                        }
                    }
                }, e -> {
                    showMessage(MessageFactory.getMessage(e));
                });
    }
    private void showMessage(String message) {
        cannelDialog();
        showToast(message);
    }
//    public View getFooterView() {
//        View view = LayoutInflater.from(this).inflate(R.layout.fragment_foot, null);
//        llMoreFoor = ButterKnife.findById(view, R.id.llMoreFoor);
//        llMoreFoor.setOnClickListener(this);
//        return view;
//    }

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
                            getInitnetData();
                            adapter.loadMoreComplete();
                        });
            }
        });
        swipeRefresh.setEnabled(true);
    }
    @Override
    public void onRefresh() {
        Observable.timer(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    getInitnetData();
                    if (swipeRefresh != null && swipeRefresh.isRefreshing()) {
                        swipeRefresh.setRefreshing(false);
                    }
                });
    }
}