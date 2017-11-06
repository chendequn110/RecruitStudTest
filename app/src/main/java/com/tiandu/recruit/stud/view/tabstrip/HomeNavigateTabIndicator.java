package com.tiandu.recruit.stud.view.tabstrip;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiandu.recruit.stud.R;


/**
 * Created by Jerome on 2016/11/1.
 * Email :jeromekai8@gmail.com
 */

public class HomeNavigateTabIndicator extends LinearLayout implements View.OnClickListener {

    private HomeNavigateTab mHomeNavigateTab;
    private OnTabChangeListener mOnTabChangeListener;
    private HomeNavigateTabIndicator.ViewHolder currSelectedViewHolder;

    public HomeNavigateTabIndicator(Context context) {
        this(context, null);
    }

    public HomeNavigateTabIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeNavigateTab getHomeNavigateTab() {
        return mHomeNavigateTab;
    }

    public void setOnTabChangeListener(OnTabChangeListener l) {
        mOnTabChangeListener = l;
    }

    @Override
    public void onClick(View v) {
        Object o = v.getTag();
        if ((o != null) && (o instanceof HomeNavigateTabIndicator.ViewHolder)) {
            ViewHolder viewHolder = (HomeNavigateTabIndicator.ViewHolder) o;
            if (viewHolder.pageParam != null) {
                setCurrSelectedIndex(viewHolder.index);
            }
        }
    }

    public void setCurrSelectedIndex(int index) {
        if ((currSelectedViewHolder != null) && (currSelectedViewHolder.index == index)) {
            return;
        }
        setCurrentItem(index);
    }

    public void setNavigateTab(HomeNavigateTab navigateTab) {
        if ((navigateTab != null) && (navigateTab.getTabParams() != null) && (navigateTab.getTabParams().size() > 0)) {
            mHomeNavigateTab = navigateTab;
            removeAllViews();
            for (int index = 0; index < mHomeNavigateTab.getTabParams().size(); index++) {
                addTab(index, (HomeNavigateTab.TabParam) mHomeNavigateTab.getTabParams().get(index));
            }
        }
    }

    /**
     * 未读消息
     */
    public void setUnCount(int count) {
        View view = getChildAt(0);
        TextView textView = (TextView) view.findViewById(R.id.tvUnreadMsg);
        if (textView == null)
            return;
        if (count > 0) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(count < 100 ? String.valueOf(count) : "99+");
        } else {
            textView.setVisibility(View.GONE);
        }
    }

    /**
     * 有消息
     */
    public void setHasNew(int visible) {
        View view = getChildAt(3);
        View newView = (View) view.findViewById(R.id.newMsg);
        if (newView == null)
            return;
        newView.setVisibility(visible);
    }

    private void addTab(int index, HomeNavigateTab.TabParam pageParam) {
        int id = R.layout.item_navigate_view;
        if (pageParam.tabViewResId > 0) {
            id = pageParam.tabViewResId;
        }
        View view = LayoutInflater.from(getContext()).inflate(id, null);
        view.setFocusable(true);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.index = index;
        viewHolder.pageParam = pageParam;
        viewHolder.tabIcon = (ImageView) view.findViewById(R.id.ivAvatar);
        viewHolder.tabTitle = (TextView) view.findViewById(R.id.tvTabTitle);
        viewHolder.tabTitle.setTextColor(0xff999999);
        if (pageParam.iconResId > 0) {
            viewHolder.tabIcon.setImageResource(pageParam.iconResId);
        }
        if (TextUtils.isEmpty(pageParam.title)) {
            viewHolder.tabTitle.setVisibility(View.GONE);
        } else {
            viewHolder.tabTitle.setVisibility(View.VISIBLE);
            viewHolder.tabTitle.setText(pageParam.title);
        }
        if (pageParam.backgroundColor > 0) {
            view.setBackgroundResource(pageParam.backgroundColor);
        }
        view.setTag(viewHolder);
        view.setOnClickListener(this);
        addView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1.0f));
    }

    private void setCurrentItem(int index) {
        int tabCount = getChildCount();
        ViewHolder viewHolder = null;
        for (int i = 0; i < tabCount; i++) {
            View child = getChildAt(i);
            Object o = child.getTag();

            if ((o != null) && (o instanceof ViewHolder)) {
                viewHolder = (ViewHolder) o;
                boolean isSelected = viewHolder.index == index;

                if ((viewHolder.pageParam != null) && (viewHolder.tabIcon != null)) {
                    if (isSelected) {
                        if (viewHolder.pageParam.iconSelectedResId > 0) {
                            currSelectedViewHolder = viewHolder;
                            viewHolder.tabIcon.setImageResource(viewHolder.pageParam.iconSelectedResId);
                        }
                        if (mOnTabChangeListener != null)
                            mOnTabChangeListener.onTabChanged(index);
                    } else if (viewHolder.pageParam.iconResId > 0) {
                        viewHolder.tabIcon.setImageResource(viewHolder.pageParam.iconResId);
                    }

                    if (viewHolder.tabTitle != null)
                        viewHolder.tabTitle.setTextColor(isSelected ? getResources().getColor(R.color.green) : getResources().getColor(R.color.darker_grey));
                }
            }
        }
    }

    public interface OnTabChangeListener {
        void onTabChanged(int index);
    }

    class ViewHolder {
        public int index;
        public HomeNavigateTab.TabParam pageParam;
        public ImageView tabIcon;
        public TextView tabTitle;
    }
}
