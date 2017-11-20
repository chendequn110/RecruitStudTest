package com.tiandu.recruit.stud.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.data.entity.FeeChildInfo;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/10 11:13
 * 修改人：chendequnn
 * 修改时间：2017/11/10 11:13
 * 修改备注：
 */
public class FeeChildAdapter extends BaseQuickAdapter<FeeChildInfo.AaDataBean, BaseViewHolder> {

    public FeeChildAdapter() {
        super(R.layout.item_feechild_appoint_viwe, null);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, FeeChildInfo.AaDataBean info) {



        baseViewHolder.setText(R.id.tvMemberID, "会员编号:" + info.getMemberID())
                .setText(R.id.tvMemberName, "昵称:" + info.getMemberName())
                .setText(R.id.tvStartDate, info.getStartDate())
                .setText(R.id.tvRealName, "真实姓名:" + info.getRealName())
                .setText(R.id.tvParentFee, "上交金额:" + info.getParentFee());
//                .addOnClickListener(R.id.btnXiajia);

    }
}