package com.tiandu.recruit.stud.ui.adapter;

import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.data.entity.MemberFeeInfo;
import com.tiandu.recruit.stud.data.entity.MemberFeeInfo2;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/9 15:21
 * 修改人：chendequnn
 * 修改时间：2017/11/9 15:21
 * 修改备注：
 */
public class FeeAdapter  extends BaseQuickAdapter<MemberFeeInfo2.DataBean.AaDataBean,BaseViewHolder> {

    public FeeAdapter() {
        super(R.layout.item_fee_appoint_viwe, null);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MemberFeeInfo2.DataBean.AaDataBean info) {



        Button btnXiajia = baseViewHolder.getView(R.id.btnXiajia);

        baseViewHolder.setText(R.id.tvTotalFee,"总金额:" +info.getTotalFee())
                .setText(R.id.tvParentFee,"上交金额:"+info.getParentFee())
                .setText(R.id.tvArrangeTime,info.getStartDate())
                .setText(R.id.tvTakeFee,"劳务费:"+info.getTakeFee())
                .setText(R.id.tvChildFee,"分支金额:"+info.getChildFee())
                .addOnClickListener(R.id.btnXiajia);

    }
}