package com.tiandu.recruit.stud.ui.adapter;

import android.view.View;
import android.widget.TextView;

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




        baseViewHolder.setText(R.id.tvTitle, info.getTitle())
//                .setText(R.id.tvWorkPlace,info.getWorkPlace())
                .setText(R.id.tvMonthPay,"薪酬:"+info.getMonthPay())
                .setText(R.id.tvCompanyName,info.getCompanyName())
                .setText(R.id.tvCreateTime,info.getPublishDate())
                .setText(R.id.tvAwardAmt,"推荐奖金:"+info.getAwardAmt())
            .setText(R.id.tvWorkPlace,info.getCity()+"  "+info.getDistrict());


        TextView tv_isTop = baseViewHolder.getView(R.id.tv_isTop);
        TextView tv_apply = baseViewHolder.getView(R.id.tv_apply);
        if((info.getIsTop()!=null)){
            if(info.getIsTop().equals("是")){
                tv_isTop.setText("置顶");
            }else{
                tv_isTop.setVisibility(View.INVISIBLE);
            }
        }else{
            tv_isTop.setVisibility(View.INVISIBLE);
        }

        if(info.getIsApply()!=null){
            if(info.getIsApply().equals("1")){
                tv_apply.setText("已应聘");
            }else{
                tv_apply.setText("我要应聘");
            }
        }else{
            tv_apply.setText("");
        }


//                .setText(R.id.tv_isTop,if(info.getIsTop().equals("是"))
//                .setText(R.id.tv_apply,info.getAwardAmt());
//                .setText(R.id.tvStatusName,info.getStatusName())
//                .addOnClickListener(R.id.btnMeg);

    }

}