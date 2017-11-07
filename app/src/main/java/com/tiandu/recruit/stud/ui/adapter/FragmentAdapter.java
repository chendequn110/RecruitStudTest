package com.tiandu.recruit.stud.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.tiandu.recruit.stud.base.BaseLazyFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {

    private List<BaseLazyFragment> mListFragments = null;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
        this.mListFragments = new ArrayList<>();
    }

    public void updateData(List<BaseLazyFragment> fragments) {
        mListFragments.clear();
        if (fragments != null && !fragments.isEmpty()) {
            mListFragments.addAll(fragments);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return null != mListFragments ? mListFragments.size() : 0;
    }

    @Override
    public Fragment getItem(int index) {
        if (mListFragments != null && index > -1 && index < mListFragments.size()) {
            return mListFragments.get(index);
        } else {
            return null;
        }
    }
}