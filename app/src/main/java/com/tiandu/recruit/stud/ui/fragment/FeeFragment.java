package com.tiandu.recruit.stud.ui.fragment;

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
import com.tiandu.recruit.stud.base.BaseLazyFragment;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.MemberFeeInfo;
import com.tiandu.recruit.stud.ui.adapter.FeeAdapter;
import com.tiandu.recruit.stud.ui.feechild.FeeChildActivity;

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
 * 创建时间：2017/11/9 12:52
 * 修改人：chendequnn
 * 修改时间：2017/11/9 12:52
 * 修改备注：
 */
public class FeeFragment extends BaseLazyFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener,BaseQuickAdapter.RequestLoadMoreListener{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;


    private FeeAdapter adapter = null;
//    private LinearLayout llMoreFoor;
    private int page=0;
    private int totalpage = 0;



    @Override
    protected void initViewsAndEvents() {
        setupView();
    }

//    public View getFooterView() {
//        View view = LayoutInflater.from(context).inflate(R.layout.fragment_foot, null);
//        llMoreFoor = ButterKnife.findById(view, R.id.llMoreFoor);
//        llMoreFoor.setOnClickListener(this);
//        return view;
//    }

    private void setupView() {
        swipeRefresh.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter = new FeeAdapter());
//        adapter.addFooterView(getFooterView());
        adapter.setOnLoadMoreListener(this, recyclerView);
        recyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MemberFeeInfo.AaDataBean item = adapter.getItem(i);
                        Bundle bundle=new Bundle();
                        bundle.putString("PlanID",item.getPlanID());
                        readyGo(FeeChildActivity.class,bundle);

            }
        });

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MemberFeeInfo.AaDataBean item = adapter.getItem(i);
                Bundle bundle=new Bundle();
                bundle.putString("PlanID",item.getPlanID());
                readyGo(FeeChildActivity.class,bundle);

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
                            getOrdList();
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
                    getOrdList();
                    if (swipeRefresh != null && swipeRefresh.isRefreshing()) {
                        swipeRefresh.setRefreshing(false);
                    }
                });
    }


    private void getOrdList() {
        Api.getInstance()
                .movieService.getMemberFee(C.USER_GETMEMBERFEE,SpUtil.getMemberID(),SpUtil.getToken(),page+"")
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<MemberFeeInfo>>() {
                    @Override
                    public void call(List<MemberFeeInfo> orderInfos) {
                        cannelMyDialog();
                        if (null != orderInfos) {
                            List<MemberFeeInfo.AaDataBean> aaData = orderInfos.get(0).getAaData();
                            totalpage=orderInfos.get(0).getITotalRecords();
                            if (page == 0) {
                                adapter.setNewData(aaData);
                            } else {
                                adapter.addData(aaData);
                                adapter.loadMoreComplete();
                            }
                        }
                    }
                }, e -> {
                    showMessage(MessageFactory.getMessage(e));
                });
    }
//    public void showProgress() {
//        Api.getInstance()
//                .movieService.getMemberFee(C.USER_GETMEMBERFEE,SpUtil.getMemberID(), SpUtil.getToken())
//                .compose(RxSchedulers.io_main())
//                .compose(RxSchedulers.sTransformer())
//                .subscribe(new Action1<List<MemberFeeInfo.AaDataBean>>() {
//                    @Override
//                    public void call(List<MemberFeeInfo.AaDataBean> infos) {
//
//                        if (null != infos) {
//                            double score1 = Double.valueOf(hourInfos.getScore1());
//                            double score1Defualt = Double.valueOf(hourInfos.getScore1Defualt());
//                            if (Double.valueOf(score1Defualt) != 0) {
//                                int v = (int) ((score1 / score1Defualt) * 100);
//                                skSubject1.setProgress(v);
//                            }else if (score1 > score1Defualt) {
//                                skSubject1.setProgress(100);
//                            }
//
//                            //tvSurplus1.setText(AStringUtil.setSurplusHour(score1,score1Defualt));
//                            tvSurplus1.setText(AStringUtil.getHour(score1,score1Defualt));
//
//                            double score2 = Double.valueOf(hourInfos.getScore2());
//                            double score2Defualt = Double.valueOf(hourInfos.getScore2Defualt());
//                            if (Double.valueOf(score2Defualt) != 0) {
//                                int v = (int) ((score2 / score2Defualt) * 100);
//                                skSubject2.setProgress(v);
//                            }else if (score2 > score2Defualt) {
//                                skSubject2.setProgress(100);
//                            }
//                            //tvSurplus2.setText(AStringUtil.setSurplusHour(score2,score2Defualt));
//                            tvSurplus2.setText(AStringUtil.getHour(score2,score2Defualt));
//
//                            double score3 = Double.valueOf(hourInfos.getScore3());
//                            double score3Defualt = Double.valueOf(hourInfos.getScore3Defualt());
//                            if (Double.valueOf(score3Defualt) != 0) {
//                                int v = (int) ((score3 / score3Defualt) * 100);
//                                skSubject3.setProgress(v);
//                            }else if (score3 > score3Defualt) {
//                                skSubject3.setProgress(100);
//                            }
//                            //tvSurplus3.setText(AStringUtil.setSurplusHour(score3,score3Defualt));
//                            tvSurplus3.setText(AStringUtil.getHour(score3,score3Defualt));
//                        }
//                    }
//                }, e -> {
//                    showMessage(MessageFactory.getMessage(e));
//                });
//    }

    private void showMessage(String message) {
        cannelMyDialog();
        showToast(message);
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.llMoreFoor:
//                readyGo(OrdActivity.class);
//                break;
//            case R.id.tvAppoint:
//                readyGo(AppointmentActivity.class);
//                break;
//            case R.id.tvScan:
//                readyGo(ZxingActivity.class);
//                break;
//        }
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
        view = getActivity().findViewById(R.id.statusBar);
        if (view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
        }

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
}