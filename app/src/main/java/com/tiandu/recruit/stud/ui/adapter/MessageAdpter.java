package com.tiandu.recruit.stud.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.data.entity.MessageInfo;

/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/10 16:49
 * 修改人：chendequnn
 * 修改时间：2017/11/10 16:49
 * 修改备注：
 */
public class MessageAdpter extends BaseQuickAdapter<MessageInfo.AaDataBean,BaseViewHolder> {
    private LayoutInflater mInflater = null;
    private Context context;
    public MessageAdpter(Context context) {
        super(R.layout.item_message_appoint_viwe, null);
        this.context = context;
        this.mInflater = LayoutInflater.from(this.context);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MessageInfo.AaDataBean info) {



        baseViewHolder.setText(R.id.tvTitle, info.getTitle())
                .setText(R.id.tvCreateTime, info.getPublishDate());
    }

}