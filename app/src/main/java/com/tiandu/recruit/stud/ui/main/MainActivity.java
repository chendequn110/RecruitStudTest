package com.tiandu.recruit.stud.ui.main;




import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.base.BaseLazyFragment;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.VersionInfo;
import com.tiandu.recruit.stud.ui.adapter.FragmentAdapter;
import com.tiandu.recruit.stud.ui.fragment.HomeFragment;
import com.tiandu.recruit.stud.ui.login.LoginActivity;
import com.tiandu.recruit.stud.view.XViewPager;
import com.tiandu.recruit.stud.view.tabstrip.HomeNavigateTab;
import com.tiandu.recruit.stud.view.tabstrip.HomeNavigateTabIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter,MainModel> implements MainContract.View,
        HomeNavigateTabIndicator.OnTabChangeListener {
    @BindView(R.id.viewPager)
    XViewPager viewPager;
    @BindView(R.id.tabStrip) HomeNavigateTabIndicator tabStrip;
    @BindView(R.id.llContainer)
    LinearLayout container;
    @BindView(R.id.ll_btn_screen) LinearLayout ll_btn_screen;
    private int position = 0;
    private FragmentAdapter adapter = null;
    private List<BaseLazyFragment> mFragments = new ArrayList<>();
    private String mTitles[] = {"首页","招聘", "账户", "我的"};
    private AlertDialog.Builder builder;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initView() {
        cannelDialog();
        setTranslucent();
        setHomeEnable(false);
        initTabHost();

//        if (SpUtil.isLogined()) {
//            position = 1;
//        }
        setCurrentViewPage();
        viewPager.setCurrentItem(position, false);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    public void onTabChanged(int index) {
        position = index;
        if (position == 1 | position == 2) {
            if (SpUtil.isLogined()) {
                mToolbar.setVisibility(index == C.HOME_LEARNCAR ? View.VISIBLE : View.GONE);
                setToolbarTitle(mTitles[index]);
                viewPager.setCurrentItem(position, false);
            } else {
                readyGo(LoginActivity.class);
            }
        } else {
            mToolbar.setVisibility(View.GONE);
            setToolbarTitle(mTitles[0]);
            viewPager.setCurrentItem(0, false);
        }
    }

    @Override
    public void showVersion(VersionInfo info) {

    }



    @Override
    public void showError(String message) {

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setTranslucent() {
        Window window = getWindow();
        //设置底部状态栏为系统预留空间
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //设置透明状态栏,这样才能让 ContentView 向上，不为顶部状态栏预留空间
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }
    }
    private void initTabHost() {
        tvRight.setVisibility(View.VISIBLE);
        tabStrip.setNavigateTab(new HomeNavigateTab(viewPager, this));
        viewPager.setAdapter(adapter = new FragmentAdapter(getSupportFragmentManager()));
        tabStrip.setOnTabChangeListener(this);
        mFragments.add(new HomeFragment());
//        mFragments.add(new LearningFragment());
//        mFragments.add(new UserFragment());
//        mFragments.add(new UserFragment());
        viewPager.setEnableScroll(false);
        viewPager.setOffscreenPageLimit(mFragments.size());
        adapter.updateData(mFragments);
    }
    private void setCurrentViewPage() {
        setViewPage(position);
        setToolbarTitle(mTitles[position]);
    }
    public void setViewPage(int index) {
        if ((index < 0) || (tabStrip != null)
                && (tabStrip.getHomeNavigateTab() != null)
                && (index >= tabStrip.getHomeNavigateTab().getTabParams().size())) {
            index = 0;
        }

        tabStrip.setCurrSelectedIndex(index);
    }
}
