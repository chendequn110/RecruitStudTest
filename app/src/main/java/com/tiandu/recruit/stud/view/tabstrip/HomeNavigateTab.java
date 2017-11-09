package com.tiandu.recruit.stud.view.tabstrip;

import android.content.Context;


import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.view.XViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerome on 2016/11/1.
 * Email :jeromekai8@gmail.com
 */

public class HomeNavigateTab {

    private final List<TabParam> pageParams = new ArrayList();

    public HomeNavigateTab(XViewPager viewPager, Context context) {
        pageParams.clear();

        TabParam pageParam1 = new TabParam();
        pageParam1.title = context.getString(R.string.main_home);
        pageParam1.iconResId = R.mipmap.ic_home_page;
        pageParam1.iconSelectedResId = R.mipmap.ic_home_page_normal;
        pageParams.add(pageParam1);

        TabParam pageParam2 = new TabParam();
        pageParam2.title = context.getString(R.string.main_human);
        pageParam2.iconResId = R.mipmap.ic_home_human;
        pageParam2.iconSelectedResId = R.mipmap.ic_home_human_normal;
        pageParams.add(pageParam2);

        TabParam pageParam3 = new TabParam();
        pageParam3.title = context.getString(R.string.main_trends);
        pageParam3.iconResId = R.mipmap.ic_home_trends;
        pageParam3.iconSelectedResId = R.mipmap.ic_home_trends_normal;
        pageParams.add(pageParam3);

        TabParam pageParam4 = new TabParam();
        pageParam4.title = context.getString(R.string.main_me);
        pageParam4.iconResId = R.mipmap.ic_home_me;
        pageParam4.iconSelectedResId = R.mipmap.ic_home_me_normal;
        pageParams.add(pageParam4);

    }

    public List getTabParams() {
        return pageParams;
    }

    class TabParam {
        public String title;
        public int iconResId;
        public int iconSelectedResId;
        public int tabViewResId;
        public int backgroundColor = 0;
    }
}
