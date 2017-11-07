package com.tiandu.recruit.stud.ui.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.data.entity.PayInfo;

/**
 * Created by Administrator on 2017/7/7.
 */

public class HomeHeadAdapter extends BaseQuickAdapter<PayInfo,BaseViewHolder> {

    public HomeHeadAdapter() {
        super(R.layout.item_home_head_viwe, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, PayInfo item) {
        helper.setText(R.id.tvTitle, item.getName())
                .setBackgroundRes(R.id.ivIcon, item.getResId());
    }
}
