package com.tiandu.recruit.stud.mycoach;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tiandu.recruit.stud.R;


/**
 * Created by Administrator on 2017/7/13.
 */

public class CityAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public CityAdapter() {
        super(R.layout.item_city_view, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView tvCity = helper.getView(R.id.tvCity);
        if (null != item) {
            tvCity.setVisibility(View.VISIBLE);
            tvCity.setText(item);
        } else {
            tvCity.setVisibility(View.GONE);
        }
    }
}
