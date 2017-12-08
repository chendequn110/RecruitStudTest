package com.tiandu.recruit.stud.ui.fragment;


import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.base.BaseLazyFragment;
import com.tiandu.recruit.stud.base.utils.AImageUtil;
import com.tiandu.recruit.stud.base.utils.Logger;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.AdvertInfo;
import com.tiandu.recruit.stud.view.AppBarStateChangeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.functions.Action1;

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
//    @BindView(R.id.rvHead) RecyclerView rvHead;
    @BindView(R.id.mAppBarLayout) AppBarLayout mAppBarLayout;
    @BindView(R.id.collapsingToolBarLayout) CollapsingToolbarLayout collapsingToolbarLayout;
//    @BindView(R.id.lltollbar) LinearLayout lltollbar;
//    @BindView(R.id.tvNotice) TextView tvNotice;
//    @BindView(R.id.tvNotice2) TextView tvNotice2;
//    @BindView(R.id.headLine) View headLine;
//    @BindView(R.id.LineVertical) View LineVertical;
//    @BindView(R.id.line_school) View line_school;
//    @BindView(R.id.line_coach) View line_coach;
    @BindView(R.id.convenientBanner) ConvenientBanner convenientBanner;

    @BindView(R.id.payFragmentContainer) LinearLayout fragmentContainer;

    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private ArrayList<String> onileImages = new ArrayList<String>();
    private NoticeFragment noticeFragment;

    @Override
    protected void initViewsAndEvents() {
        //ll_btn_screen = (LinearLayout) getActivity().findViewById(R.id.ll_btn_screen);//底部按钮
        initBanner();
        initHeader();

    }

    private void initBanner() {
        Api.getInstance()
                .movieService.getAdv(C.USER_ADVERT, SpUtil.getMemberID(), SpUtil.getToken())
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<AdvertInfo>>() {
                    @Override
                    public void call(List<AdvertInfo> meInfos) {
                        if (null != meInfos) {
                         for (AdvertInfo data :meInfos){
                             Logger.d(data.getAdvUrl());
                             onileImages.add(data.getAdvUrl());
                         }
                            //        for (int position = 1; position < 5; position++)
//            localImages.add(getResId("ic_banner_" + position, R.mipmap.class));
                            convenientBanner.setPages(
                                    new CBViewHolderCreator<LocalImageHolderView>() {
                                        @Override
                                        public LocalImageHolderView createHolder() {
                                            return new LocalImageHolderView();
                                        }
                                    }, onileImages)
                                    //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                                    .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});
                            //设置指示器的方向
                            //               .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);

                        }else {

                        }
                    }
                }, e -> {

                });
    }



    private void initHeader() {
//        line_school.setVisibility(View.VISIBLE);
//        line_coach.setVisibility(View.INVISIBLE);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) { //展开状态
                    Logger.d("展开状态");
//                    LineVertical.setVisibility(View.VISIBLE);
//                    headLine.setBackgroundColor((Color.parseColor("#eaeaea")));//灰色
//                    lltollbar.setBackgroundColor(Color.parseColor("#ffffff"));
//                    tvNotice.setTextColor(Color.parseColor("#000000"));//黑色
//                    tvNotice2.setTextColor(Color.parseColor("#000000"));
//                    line_coach.setBackgroundColor(Color.parseColor("#4CAF50"));
//                    line_school.setBackgroundColor(Color.parseColor("#4CAF50"));
                } else if (state == State.COLLAPSED) { //折叠状态
                    Logger.d("折叠状态");
//                    LineVertical.setVisibility(View.INVISIBLE);
//                    lltollbar.setBackgroundColor(Color.parseColor("#4CAF50"));
//                    headLine.setBackgroundColor((Color.parseColor("#4CAF50")));//绿色
//                    tvNotice.setTextColor(Color.parseColor("#ffffff"));//白色
//                    tvNotice2.setTextColor(Color.parseColor("#ffffff"));
//                    line_coach.setBackgroundColor(Color.parseColor("#ffffff"));
//                    line_school.setBackgroundColor(Color.parseColor("#ffffff"));
                } else {
                    //中间状态
                    Logger.d("中间状态");
//                    LineVertical.setVisibility(View.VISIBLE);
//                    lltollbar.setBackgroundColor(Color.parseColor("#ffffff"));
//                    headLine.setBackgroundColor((Color.parseColor("#eaeaea")));
//                    tvNotice.setTextColor(Color.parseColor("#000000"));//黑色
//                    tvNotice2.setTextColor(Color.parseColor("#000000"));
//                    line_coach.setBackgroundColor(Color.parseColor("#4CAF50"));
//                    line_school.setBackgroundColor(Color.parseColor("#4CAF50"));

                }
            }
        });
    }
//    @OnClick({R.id.tvNotice, R.id.tvNotice2})
//    void onClick(View v) {
//        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//        switch (v.getId()) {
//            case R.id.tvNotice:
//                Logger.d("通知");
//                if (!noticeFragment.isVisible()) {
//                    transaction.show(noticeFragment);
//                    if (noticeFragment.isVisible() && null != noticeFragment) {
//                        transaction.hide(noticeFragment);
//                    }
//                    transaction.commit();
//                }
////                line_coach.setVisibility(View.INVISIBLE);
////                line_school.setVisibility(View.VISIBLE);
//                break;
//            case R.id.tvNotice2:
//                Logger.d("推送");
//                if (!noticeFragment.isVisible()) {
//                    transaction.show(noticeFragment);
//                    if (noticeFragment.isVisible() && null != noticeFragment) {
//                        transaction.hide(noticeFragment);
//                    }
//                    transaction.commit();
//                }
////                line_coach.setVisibility(View.VISIBLE);
////                line_school.setVisibility(View.INVISIBLE);
//                break;
//        }
//    }

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
        initNoticeView();

        //开始自动翻页
        convenientBanner.startTurning(5000);
    }
    private void initNoticeView() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if(null==noticeFragment){
            noticeFragment = new NoticeFragment();
        }
        transaction.add(R.id.payFragmentContainer, noticeFragment);
        transaction.commit();
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



    public class LocalImageHolderView implements Holder<String> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            imageView.setImageResource(R.mipmap.ic_banner_1);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
           AImageUtil.loadImg(imageView, data ,R.mipmap.ic_banner_1);
//            imageView.setImageResource(data);
        }
    }
}