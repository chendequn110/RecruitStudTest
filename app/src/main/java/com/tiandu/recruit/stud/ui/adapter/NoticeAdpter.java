package com.tiandu.recruit.stud.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.data.entity.NoticeInfo;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/10 16:49
 * 修改人：chendequnn
 * 修改时间：2017/11/10 16:49
 * 修改备注：
 */
public class NoticeAdpter extends BaseQuickAdapter<NoticeInfo.AaDataBean,BaseViewHolder> {
    private LayoutInflater mInflater = null;
    private Context context;
    public NoticeAdpter(Context context) {
        super(R.layout.item_notice_appoint_viwe, null);
        this.context = context;
        this.mInflater = LayoutInflater.from(this.context);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NoticeInfo.AaDataBean info) {


        Button btnMeg = baseViewHolder.getView(R.id.btnMeg);

        baseViewHolder.setText(R.id.tvTitle, info.getTitle())
                .setText(R.id.tvCreator, info.getCreator())
                .setText(R.id.tvCreateTime, info.getCreateTime())
                .setText(R.id.tvStatusName, info.getStatusName())
                .addOnClickListener(R.id.btnMeg);

    }

}