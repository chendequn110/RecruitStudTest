package com.tiandu.recruit.stud.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.data.entity.ChildMenberInfo;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/10 11:13
 * 修改人：chendequnn
 * 修改时间：2017/11/10 11:13
 * 修改备注：
 */
public class ChildMemberAdapter extends BaseQuickAdapter<ChildMenberInfo.DataBean, BaseViewHolder> {
    private Context context;
    private LayoutInflater mInflater = null;

    public ChildMemberAdapter(Context context) {
        super(R.layout.item_child_member_viwe, null);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ChildMenberInfo.DataBean info) {



        baseViewHolder.setText(R.id.tvMemberID, "会员编号:" + info.getMemberID())
                .setText(R.id.tvMemberName, "会员名称:" + info.getMemberName())
                .setText(R.id.tvRealName, "真实姓名:" + info.getRealName())
                .setText(R.id.tvStatus, "会员状态:" + info.getStatusName());
    }
}