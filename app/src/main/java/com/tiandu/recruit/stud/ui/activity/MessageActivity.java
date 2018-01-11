package com.tiandu.recruit.stud.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

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
import com.tiandu.recruit.stud.data.entity.MessageInfo;
import com.tiandu.recruit.stud.ui.adapter.MessageAdpter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/17 16:07
 * 修改人：chendequnn
 * 修改时间：2017/11/17 16:07
 * 修改备注：
 */
public class MessageActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener,BaseQuickAdapter.RequestLoadMoreListener{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    private MessageAdpter adapter;
    private LinearLayout llMoreFoor;
    private int page=0;
    private int totalpage = 0;


    public View getFooterView() {
        View view = LayoutInflater.from(this).inflate(R.layout.fragment_foot, null);
        llMoreFoor = ButterKnife.findById(view, R.id.llMoreFoor);
        llMoreFoor.setOnClickListener(this);
        return view;
    }
    @Override
    protected void initView() {
//        showloginDialog("");
        setupView();
        getOrdList();

    }
    private void setupView() {
        swipeRefresh.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter = new MessageAdpter(this));
        adapter.addFooterView(getFooterView());

        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MessageInfo.AaDataBean item = adapter.getItem(i);
                Bundle bundle=new Bundle();
                bundle.putInt("ID",item.getID());
                readyGo(MessageDetailActivity.class,bundle);
            }
        });

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MessageInfo.AaDataBean item = adapter.getItem(i);
                Bundle bundle=new Bundle();
                bundle.putInt("ID",item.getID());
                readyGo(MessageDetailActivity.class,bundle);
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_childmenber;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    public void onClick(View view) {
        getOrdList();
    }

    @Override
    public void onRefresh() {
        page = 0;
        adapter.setEnableLoadMore(false);
        Observable.timer(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    getOrdList();
                    if (null != swipeRefresh) {
                        swipeRefresh.setRefreshing(false);
                    }
                });
    }
    private void getOrdList() {
        Api.getInstance()
                .movieService.getMessageInfo(C.USER_GETMESSAGE, SpUtil.getMemberID(),SpUtil.getToken(),page+"")
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<MessageInfo>>() {
                    @Override
                    public void call(List<MessageInfo> infos) {
                        cannelDialog();
                        if (null != infos) {
                            List<MessageInfo.AaDataBean> aaData = infos.get(0).getAaData();
                            totalpage =infos.get(0).getITotalRecords();
                            if (page == 0) {
                                adapter.setNewData(aaData);
                            } else {
                                adapter.addData(aaData);
                                adapter.loadMoreComplete();
                            }
//                            adapter.setNewData(aaData);
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
                            getOrdList();
                        });
            }
        });
        swipeRefresh.setEnabled(true);
    }
}