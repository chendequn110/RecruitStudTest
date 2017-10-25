package com.tiandu.recruit.stud.base.utils.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.tiandu.recruit.stud.R;

import butterknife.ButterKnife;

/**
 * Created by Jerome on 2016/10/19.
 * Email :jeromekai8@gmail.com
 */

public class MyToast {

   /* public void setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            mToast.setGravity(gravity, xOffset, yOffset);
        }
    }*/

    //单例吐司
    private static final Object SYNC_LOCK = new Object();
    private static Toast mToast;
    /** 上下文 */
    public static Context context;
    private static TextView textView;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        MyToast.context = context;
    }

    /**
     * 获取toast环境，为toast加锁
     *
     * @param
     * @return
     */
    private static void initToastInstance() {
        if (mToast == null) {
            synchronized (SYNC_LOCK) {
                if (mToast == null) {
                    View v = LayoutInflater.from(context).inflate(R.layout.view_toast, null);
                    textView = ButterKnife.findById(v, R.id.tvToast);
                    mToast = new Toast(context);
                    mToast.setView(v);
                }
            }
        }
    }

    /**
     * 展示吐司
     *
     * @param context
     *            环境
     * @param text
     *            内容
     */
    public static void showToast(String text, Context context) {
        setContext(context);
        if (getContext() != null && text != null) {
            initToastInstance();
            mToast.setDuration(Toast.LENGTH_SHORT);
            textView.setText(text);
            mToast.show();
        }
    }

}
