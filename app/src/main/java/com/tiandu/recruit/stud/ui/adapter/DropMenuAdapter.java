package com.tiandu.recruit.stud.ui.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.baiiu.filter.adapter.MenuAdapter;
import com.baiiu.filter.adapter.SimpleTextAdapter;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.baiiu.filter.interfaces.OnFilterItemClickListener;
import com.baiiu.filter.typeview.DoubleListView;
import com.baiiu.filter.typeview.SingleGridView;
import com.baiiu.filter.typeview.SingleListView;
import com.baiiu.filter.util.CommonUtil;
import com.baiiu.filter.util.UIUtil;
import com.baiiu.filter.view.FilterCheckedTextView;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.data.entity.FilterType;
import com.tiandu.recruit.stud.data.entity.FilterUrl;
import com.tiandu.recruit.stud.view.betterDoubleGrid.BetterDoubleGridView;
import com.tiandu.recruit.stud.view.doubleGrid.DoubleGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * author: baiiu
 * date: on 16/1/17 21:14
 * description:
 */
public class DropMenuAdapter implements MenuAdapter {
    private final Context mContext;
    private OnFilterDoneListener onFilterDoneListener;
    private String[] titles;
    private String[] area=new String[]{};
    private SingleListView<String> singleListView;

    public DropMenuAdapter(Context context, String[] titles, OnFilterDoneListener onFilterDoneListener) {
        this.mContext = context;
        this.titles = titles;
        this.onFilterDoneListener = onFilterDoneListener;
    }
    public DropMenuAdapter(Context context, String[] titles, OnFilterDoneListener onFilterDoneListener, String[] area) {
        this.mContext = context;
        this.titles = titles;
        this.onFilterDoneListener = onFilterDoneListener;
        this.area = area;
    }

    @Override
    public int getMenuCount() {
        return titles.length;
    }

    @Override
    public String getMenuTitle(int position) {
        return titles[position];
    }

    @Override
    public int getBottomMargin(int position) {
        if (position == 3) {
            return 0;
        }

        return UIUtil.dp(mContext, 140);
    }

    @Override
    public View getView(int position, FrameLayout parentContainer) {
        View view = parentContainer.getChildAt(position);

        switch (position) {
            case 0:
                view = createSingleListView(area);
                break;
            case 1:
//                view = createDoubleListView();
                view = createSingleListView2();
                break;
            case 2:
                view = createSingleGridView();
                break;
            case 3:
                // view = createDoubleGrid();
                view = createBetterDoubleGrid();
                break;
        }

        return view;
    }
    public void setNewData(String[] area2) {
        ArrayList<String> objects = new ArrayList<>();
        objects.clear();
        for (int i = 0; i < area2.length; i++) {
            objects.add(area2[i]);
        }

        singleListView.setNewList(objects);
        return;
    }
    private View createSingleListView(String[] area) {
        singleListView = new SingleListView<String>(mContext)
                .adapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String string) {
                        return string;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item) {
                        FilterUrl.instance().singleListPosition = item;

                        FilterUrl.instance().position = 0;
                        FilterUrl.instance().positionTitle = item;

                        onFilterDone();
                    }
                });

        List<String> list = new ArrayList<>();
//        String[] sh=new String[]{"黄浦区","卢湾区","徐汇区","长宁区","静安区","普陀区","闸北区","虹口区","杨浦区","闵行区","宝山区","嘉定区","浦东新区","金山区","松江区","青浦区","南汇区","奉贤区","崇明县"};
        for (int i = 0; i < area.length; ++i) {
            list.add(area[i]);
        }
        singleListView.setList(list, -1);
        return singleListView;
    }
    private View createSingleListView2() {
        SingleListView<String> singleListView = new SingleListView<String>(mContext)
                .adapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String string) {
                        return string;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item) {
                        FilterUrl.instance().singleListPosition = item;

                        FilterUrl.instance().position = 1;
                        FilterUrl.instance().positionTitle = item;

                        onFilterDone();
                    }
                });

        List<String> list = new ArrayList<>();
        list.add("实习");
        list.add("普工");
        list.add("技工");
        list.add("猎聘");
        singleListView.setList(list, -1);

        return singleListView;
    }


    private View createDoubleListView() {
        DoubleListView<FilterType, String> comTypeDoubleListView = new DoubleListView<FilterType, String>(mContext)
                .leftAdapter(new SimpleTextAdapter<FilterType>(null, mContext) {
                    @Override
                    public String provideText(FilterType filterType) {
                        return filterType.desc;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setPadding(UIUtil.dp(mContext, 44), UIUtil.dp(mContext, 15), 0, UIUtil.dp(mContext, 15));
                    }
                })
                .rightAdapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String s) {
                        return s;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setPadding(UIUtil.dp(mContext, 30), UIUtil.dp(mContext, 15), 0, UIUtil.dp(mContext, 15));
                        checkedTextView.setBackgroundResource(android.R.color.white);
                    }
                })
                .onLeftItemClickListener(new DoubleListView.OnLeftItemClickListener<FilterType, String>() {
                    @Override
                    public List<String> provideRightList(FilterType item, int position) {
                        List<String> child = item.child;
                        if (CommonUtil.isEmpty(child)) {
                            FilterUrl.instance().doubleListLeft = item.desc;
                            FilterUrl.instance().doubleListRight = "";

                            FilterUrl.instance().position = 1;
                            FilterUrl.instance().positionTitle = item.desc;

                            onFilterDone();
                        }

                        return child;
                    }
                })
                .onRightItemClickListener(new DoubleListView.OnRightItemClickListener<FilterType, String>() {
                    @Override
                    public void onRightItemClick(FilterType item, String string) {
                        FilterUrl.instance().doubleListLeft = item.desc;
                        FilterUrl.instance().doubleListRight = string;

                        FilterUrl.instance().position = 1;
                        FilterUrl.instance().positionTitle = string;

                        onFilterDone();
                    }
                });


        List<FilterType> list = new ArrayList<>();

        //第一项
        FilterType filterType = new FilterType();
        filterType.desc = "10";
        list.add(filterType);

        //第二项
        filterType = new FilterType();
        filterType.desc = "11";
        List<String> childList = new ArrayList<>();
        for (int i = 0; i < 13; ++i) {
            childList.add("11" + i);
        }
        filterType.child = childList;
        list.add(filterType);

        //第三项
        filterType = new FilterType();
        filterType.desc = "12";
        childList = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            childList.add("12" + i);
        }
        filterType.child = childList;
        list.add(filterType);

        //初始化选中.
        comTypeDoubleListView.setLeftList(list, 1);
        comTypeDoubleListView.setRightList(list.get(1).child, -1);
        comTypeDoubleListView.getLeftListView().setBackgroundColor(mContext.getResources().getColor(R.color.b_c_fafafa));

        return comTypeDoubleListView;
    }


    private View createSingleGridView() {
        SingleGridView<String> singleGridView = new SingleGridView<String>(mContext)
                .adapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String s) {
                        return s;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        checkedTextView.setPadding(0, UIUtil.dp(context, 3), 0, UIUtil.dp(context, 3));
                        checkedTextView.setGravity(Gravity.CENTER);
                        checkedTextView.setBackgroundResource(R.drawable.selector_filter_grid);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item) {
                        FilterUrl.instance().singleGridPosition = item;

                        FilterUrl.instance().position = 2;
                        FilterUrl.instance().positionTitle = item;

                        onFilterDone();

                    }
                });

        List<String> list = new ArrayList<>();
        for (int i = 20; i < 39; ++i) {
            list.add(String.valueOf(i));
        }
        singleGridView.setList(list, -1);


        return singleGridView;
    }


    private View createBetterDoubleGrid() {

        List<String> phases = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            phases.add("3top" + i);
        }
        List<String> areas = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            areas.add("3bottom" + i);
        }


        return new BetterDoubleGridView(mContext)
                .setmTopGridData(phases)
                .setmBottomGridList(areas)
                .setOnFilterDoneListener(onFilterDoneListener)
                .build();
    }


    private View createDoubleGrid() {
        DoubleGridView doubleGridView = new DoubleGridView(mContext);
        doubleGridView.setOnFilterDoneListener(onFilterDoneListener);


        List<String> phases = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            phases.add("3top" + i);
        }
        doubleGridView.setTopGridData(phases);

        List<String> areas = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            areas.add("3bottom" + i);
        }
        doubleGridView.setBottomGridList(areas);

        return doubleGridView;
    }


    private void onFilterDone() {
        if (onFilterDoneListener != null) {
            onFilterDoneListener.onFilterDone( FilterUrl.instance().position, "", "");
        }
    }

}
