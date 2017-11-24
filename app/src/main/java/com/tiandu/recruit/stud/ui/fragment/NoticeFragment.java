package com.tiandu.recruit.stud.ui.fragment;

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
import com.tiandu.recruit.stud.base.BaseLazyFragment;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.NoticeInfo;
import com.tiandu.recruit.stud.ui.adapter.NoticeAdpter;
import com.tiandu.recruit.stud.ui.notice.NoticeDetailActivity;

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
 * 创建时间：2017/11/10 16:47
 * 修改人：chendequnn
 * 修改时间：2017/11/10 16:47
 * 修改备注：
 */
public class NoticeFragment extends BaseLazyFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    private NoticeAdpter adapter ;
    private LinearLayout llMoreFoor;



    @Override
    protected void initViewsAndEvents() {
        showMyDialog("");
        setupView();
        getOrdList();
//            swipeRefresh.setRefreshing(true);
//            onRefresh();
    }

    public View getFooterView() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_foot, null);
        llMoreFoor = ButterKnife.findById(view, R.id.llMoreFoor);
        llMoreFoor.setOnClickListener(this);
        return view;
    }

    private void setupView() {
        swipeRefresh.setOnRefreshListener(this);
//        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter = new NoticeAdpter(context));
        adapter.addFooterView(getFooterView());

        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                NoticeInfo.AaDataBean item = adapter.getItem(i);
                        Bundle bundle=new Bundle();
                        bundle.putInt("ID",item.getID());
                        readyGo(NoticeDetailActivity.class,bundle);
            }
        });

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                NoticeInfo.AaDataBean item = adapter.getItem(i);
                Bundle bundle=new Bundle();
                bundle.putInt("ID",item.getID());
                readyGo(NoticeDetailActivity.class,bundle);
            }
        });
    }



    @Override
    public void onRefresh() {
        Observable.timer(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (swipeRefresh != null && swipeRefresh.isRefreshing()) {
                        swipeRefresh.setRefreshing(false);
                        getOrdList();
//                        showProgress();
                    }
                });
    }


    private void getOrdList() {
        Api.getInstance()
                .movieService.getNoticeInfo(C.USER_NOTICE,SpUtil.getMemberID(),SpUtil.getToken())
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<NoticeInfo>>() {
                    @Override
                    public void call(List<NoticeInfo> infos) {
                        cannelMyDialog();
                        if (null != infos) {
                            List<NoticeInfo.AaDataBean> aaData = infos.get(0).getAaData();
                            adapter.setNewData(aaData);
                        }
                    }
                }, e -> {
                    showMessage(MessageFactory.getMessage(e));
                });
    }

    private void showMessage(String message) {
        cannelMyDialog();
        showToast(message);
    }



    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_fee;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    private View view;

    @Override
    protected void onFirstUserVisible() {
        if (isUser()) {
//            showMyDialog("");
            getOrdList();
        }
//        if (isUser()) {
//            swipeRefresh.setRefreshing(true);
//            onRefresh();
//        }
    }

    @Override
    protected void onUserVisible() {
//        if (view.getVisibility() == View.GONE) {
//            view.setVisibility(View.VISIBLE);
//        }
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
    @Override
    public void onClick(View v) {

    }

}