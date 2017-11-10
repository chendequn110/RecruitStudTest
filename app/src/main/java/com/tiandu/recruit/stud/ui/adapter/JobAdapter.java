package com.tiandu.recruit.stud.ui.adapter;

import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.data.entity.JobInfo;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/10 13:46
 * 修改人：chendequnn
 * 修改时间：2017/11/10 13:46
 * 修改备注：
 */
public class JobAdapter extends BaseQuickAdapter<JobInfo.AaDataBean,BaseViewHolder> {

    public JobAdapter() {
        super(R.layout.item_job_appoint_viwe, null);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, JobInfo.AaDataBean info) {



        Button btnMeg = baseViewHolder.getView(R.id.btnMeg);

        baseViewHolder.setText(R.id.tvTitle, info.getTitle())
                .setText(R.id.tvWorkPlace,info.getWorkPlace())
                .setText(R.id.tvMonthPay,info.getMonthPay())
                .setText(R.id.tvCompanyName,info.getCompanyName())
                .setText(R.id.tvCreateTime,info.getCreateTime())
                .setText(R.id.tvStatusName,info.getStatusName())
                .addOnClickListener(R.id.btnMeg);

    }

}