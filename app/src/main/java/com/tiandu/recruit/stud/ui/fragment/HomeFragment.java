package com.tiandu.recruit.stud.ui.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.base.BaseLazyFragment;
import com.tiandu.recruit.stud.base.utils.Logger;
import com.tiandu.recruit.stud.data.entity.PayInfo;
import com.tiandu.recruit.stud.ui.adapter.HomeHeadAdapter;
import com.tiandu.recruit.stud.view.AppBarStateChangeListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tiandu.recruit.stud.base.utils.AImageUtil.getResId;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/7 17:17
 * 修改人：chendequnn
 * 修改时间：2017/11/7 17:17
 * 修改备注：
 */
public class HomeFragment extends BaseLazyFragment {
    @BindView(R.id.rvHead) RecyclerView rvHead;
    @BindView(R.id.mAppBarLayout) AppBarLayout mAppBarLayout;
    @BindView(R.id.collapsingToolBarLayout) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.lltollbar) LinearLayout lltollbar;
    @BindView(R.id.tvSchool) TextView tvSchool;
    @BindView(R.id.tvCoach) TextView tvCoach;
    @BindView(R.id.headLine) View headLine;
    @BindView(R.id.LineVertical) View LineVertical;
    @BindView(R.id.line_school) View line_school;
    @BindView(R.id.line_coach) View line_coach;
    @BindView(R.id.tvAddress) TextView tvAddress;
    @BindView(R.id.convenientBanner) ConvenientBanner convenientBanner;

    @BindView(R.id.payFragmentContainer) LinearLayout fragmentContainer;
//    private SchoolListFragment schoollistFragment;
//    private CoachListFragment coachlistFragment;

    private HomeHeadAdapter adapter = null;
    private List<PayInfo> homeInfos = new ArrayList();
    private String[] titles = {"学车流程", "学车问答",
            //"服务承诺",
            "运管信息"};
    private Integer[] images = {
            R.mipmap.ic_enroll_learning,
            R.mipmap.ic_enroll_answer,
            //        R.mipmap.ic_enroll_promise,
            R.mipmap.ic_enroll_news,
    };
    public static String city;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    //private LinearLayout ll_btn_screen;

    @Override
    protected void initViewsAndEvents() {
        //ll_btn_screen = (LinearLayout) getActivity().findViewById(R.id.ll_btn_screen);//底部按钮
        initBanner();
        initHeader();
    }

    private void initBanner() {
        for (int position = 1; position < 7; position++)
            localImages.add(getResId("ic_banner_" + position, R.mipmap.class));
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});
        //设置指示器的方向
        //               .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
    }



    private void initHeader() {
//        for (int i = 0; i < 3; i++) {
//            PayInfo payInfo = new PayInfo();
//            payInfo.setName(titles[i]);
//            payInfo.setResId(images[i]);
//            enrollInfos.add(payInfo);
//        }

        line_school.setVisibility(View.VISIBLE);
        line_coach.setVisibility(View.INVISIBLE);

        GridLayoutManager gridlayout = new GridLayoutManager(context, 3);
        rvHead.setLayoutManager(gridlayout);
        rvHead.setAdapter(adapter = new HomeHeadAdapter());
        adapter.setNewData(homeInfos);

        rvHead.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                PayInfo item = adapter.getItem(i);
                Bundle bundle = new Bundle();
//                switch (i) {
//                    case 0:
//                        bundle.putString(C.WEB_URL,C.WEB_STUSERVER_URL);
//                        break;
//                    case 1:
//                        bundle.putString(C.WEB_URL,C.WEB_STUQUESTION_URL);
//                        break;
////                    case 2:
////                        bundle.putString(C.WEB_URL,C.WEB_IMESERVER_URL);
////                        break;
//                    case 2:
//                        bundle.putString(C.WEB_URL,C.WEB_SERVER_URL);
//                        break;
//                }
//                bundle.putString(C.WEB_TIELE,item.getName());
//                readyGo(WebViewAcitivity.class,bundle);
            }
        });


        mAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) { //展开状态
                    Logger.d("展开状态");
                    LineVertical.setVisibility(View.VISIBLE);
                    headLine.setBackgroundColor((Color.parseColor("#eaeaea")));//灰色
                    lltollbar.setBackgroundColor(Color.parseColor("#ffffff"));
                    tvSchool.setTextColor(Color.parseColor("#000000"));//黑色
                    tvCoach.setTextColor(Color.parseColor("#000000"));
                    line_coach.setBackgroundColor(Color.parseColor("#4CAF50"));
                    line_school.setBackgroundColor(Color.parseColor("#4CAF50"));
                } else if (state == State.COLLAPSED) { //折叠状态
                    Logger.d("折叠状态");
                    LineVertical.setVisibility(View.INVISIBLE);
                    lltollbar.setBackgroundColor(Color.parseColor("#4CAF50"));
                    headLine.setBackgroundColor((Color.parseColor("#4CAF50")));//绿色
                    tvSchool.setTextColor(Color.parseColor("#ffffff"));//白色
                    tvCoach.setTextColor(Color.parseColor("#ffffff"));
                    line_coach.setBackgroundColor(Color.parseColor("#ffffff"));
                    line_school.setBackgroundColor(Color.parseColor("#ffffff"));
                } else {
                    //中间状态
                    Logger.d("中间状态");
                    LineVertical.setVisibility(View.VISIBLE);
                    lltollbar.setBackgroundColor(Color.parseColor("#ffffff"));
                    headLine.setBackgroundColor((Color.parseColor("#eaeaea")));
                    tvSchool.setTextColor(Color.parseColor("#000000"));//黑色
                    tvCoach.setTextColor(Color.parseColor("#000000"));
                    line_coach.setBackgroundColor(Color.parseColor("#4CAF50"));
                    line_school.setBackgroundColor(Color.parseColor("#4CAF50"));

                }
            }
        });

//        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                int scrollRangle = appBarLayout.getTotalScrollRange();
//                //初始verticalOffset为0，不能参与计算。
//                if (verticalOffset == 0) {
//                    headLine.setAlpha(1.0f);
//                    lltollbar.setAlpha(1.0f);
//                    tvSchool.setAlpha(1.0f);
//                    tvCoach.setAlpha(1.0f);
//                } else {
//                    //保留一位小数
//                    float alpha = Math.abs(Math.round(1.0f * verticalOffset / scrollRangle) * 10) / 10;
//                    headLine.setAlpha(alpha);
//                    lltollbar.setAlpha(alpha);
//                    tvSchool.setAlpha(alpha);
//                    tvCoach.setAlpha(alpha);
//                }
//            }
//        });

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    private View view;
    @Override
    protected void onFirstUserVisible() {
        view = getActivity().findViewById(R.id.statusBar);
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        }

        initSchoolAndCoachView();


        //开始自动翻页
        convenientBanner.startTurning(5000);
    }

    @Override
    protected void onUserVisible() {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        }

        //开始自动翻页
        convenientBanner.startTurning(5000);
    }

    @Override
    protected void onFirstUserInvisible() {
    }

    @Override
    protected void onUserInvisible() {
        //停止翻页
        convenientBanner.stopTurning();
    }


    private void initSchoolAndCoachView() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//        if (null == schoollistFragment) {
//            schoollistFragment = new SchoolListFragment();
//        }
//        if (null == coachlistFragment) {
//            coachlistFragment = new CoachListFragment();
//        }
//        transaction.add(R.id.payFragmentContainer, schoollistFragment);
//        transaction.add(R.id.payFragmentContainer, coachlistFragment);
//        transaction.hide(coachlistFragment);
        transaction.commit();
    }

    @OnClick({R.id.tvSchool, R.id.tvCoach,R.id.tvAddress})
    void onClick(View v) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.tvSchool:
                Logger.d("点击了驾校");
//                if (!schoollistFragment.isVisible()) {
//                    transaction.show(schoollistFragment);
//                    if (coachlistFragment.isVisible() && null != coachlistFragment) {
//                        transaction.hide(coachlistFragment);
//                    }
//                    transaction.commit();
//
//                    EventBus.getDefault().post(new ShowViewEvent(true,2));//教练筛选消失
//                    line_coach.setVisibility(View.INVISIBLE);
//                    line_school.setVisibility(View.VISIBLE);
//
//                }
                break;
            case R.id.tvCoach:
                Logger.d("点击了教练");
//                if (!coachlistFragment.isVisible()) {
//                    transaction.show(coachlistFragment);
//                    if (schoollistFragment.isVisible() && null != schoollistFragment) {
//                        transaction.hide(schoollistFragment);
//                    }
//
//                    transaction.commit();
//
//                    EventBus.getDefault().post(new ShowViewEvent(true,1));//驾校筛选消失
//                    line_coach.setVisibility(View.VISIBLE);
//                    line_school.setVisibility(View.INVISIBLE);
//                }
                break;
            case R.id.tvAddress:
//                Bundle bundle = new Bundle();
//                bundle.putString(C.ADDRESS_CITY,city);
//                readyGo(CityActivity.class,bundle);
                break;
        }
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onLatePlanEvent(CityEvent event) {
//        if (null != event.getCity()) {
//            city = event.getCity();
//        } else {
//            city = "大理";
//        }
//        tvAddress.setText(city);
//    }


    public class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, Integer data) {
            imageView.setImageResource(data);
        }
    }
}