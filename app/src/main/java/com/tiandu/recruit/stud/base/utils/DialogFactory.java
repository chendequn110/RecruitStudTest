package com.tiandu.recruit.stud.base.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.tiandu.recruit.stud.R;

import java.util.List;

/**
 * Created by Jerome on 16/7/4.
 * Email :jeromekai8@gmail.com
 */
public class DialogFactory {

    // two buttons, OK button and Cancel button
    public final static int DIALOG_TYPE_CONFIRM = 0;
    // only one button
    public final static int DIALOG_TYPE_TIP = DIALOG_TYPE_CONFIRM + 1;
    // no button
    public final static int DIALOG_TYPE_LOADING = DIALOG_TYPE_TIP + 1;
    public static final int WARNING = 1;
    public static final int PROMPT = 2;

    public static Dialog getConfirmDialog(Context activity, int titleId,
                                          int msgId, int cancelBtnStringId, int okBtnStringId,
                                          final View.OnClickListener cancelBtnEvent,
                                          final View.OnClickListener okBtnEvent) {

        String title = null;
        try {
            title = activity.getResources().getString(titleId);
        } catch (Resources.NotFoundException e) {

        }
        String msg = null;
        try {
            msg = activity.getResources().getString(msgId);
        } catch (Resources.NotFoundException e) {

        }
        String cancelBtnString = null;
        try {
            cancelBtnString = activity.getResources().getString(
                    cancelBtnStringId);
        } catch (Resources.NotFoundException e) {

        }
        String okBtnString = null;
        try {
            okBtnString = activity.getResources().getString(okBtnStringId);
        } catch (Resources.NotFoundException e) {

        }
        return getConfirmDialog(activity, title, msg, cancelBtnString,
                okBtnString, cancelBtnEvent, okBtnEvent, true);
    }

    public static Dialog getToastDialog(Context activity, int imageResId, String msg) {
        final Dialog dialog = new AnimaAlertDialog(activity, R.style.Theme_CustomDialog);
        final View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_toast, null);
        dialog.setContentView(dialogView);

        TextView tvMsg = (TextView) dialogView.findViewById(R.id.textToast);
        ImageView ivToast = (ImageView) dialogView.findViewById(R.id.imageToast);
        if (msg != null && !TextUtils.isEmpty(msg)) {
            tvMsg.setText(msg);
        }
        if (imageResId != 0) {
            ivToast.setImageResource(imageResId);
        }
        dialog.setCancelable(false);
        return dialog;
    }

    public static Dialog getConfirmDialog(Context activity, String title,
                                          String msg, String cancelBtnString, String okBtnString,
                                          final View.OnClickListener cancelBtnEvent,
                                          final View.OnClickListener okBtnEvent, boolean cancelable) {

        final AnimaAlertDialog dialog = new AnimaAlertDialog(activity, R.style.Theme_CustomDialog);
        final View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_comm, null);
        dialog.setContentView(dialogView);

        TextView titleView = (TextView) dialogView.findViewById(R.id.dgTitle);
        if (title == null) {
            titleView.setVisibility(View.GONE);
        } else {
            titleView.setText(title);
        }

        TextView msgTextView = (TextView) dialogView.findViewById(R.id.dgMsg);
        if (msg != null) {
            msgTextView.setText(msg);
        }

        View viewLogLine = dialogView.findViewById(R.id.viewLogLine);

        LinearLayout llDialogBut = (LinearLayout) viewLogLine.findViewById(R.id.llDialogBut);
        if (cancelBtnString == null && okBtnString == null) {
            viewLogLine.setVisibility(View.GONE);
            llDialogBut.setVisibility(View.GONE);
        }
        Button okButton = (Button) viewLogLine.findViewById(R.id.dgOk);
        if (!TextUtils.isEmpty(okBtnString)) {
            okButton.setText(okBtnString);
        }
        okButton.setOnClickListener(v -> {
            if (okBtnEvent != null) {
                okBtnEvent.onClick(v);
            }
            dialog.dismiss();
        });

        final Button cancelButton = (Button) viewLogLine.findViewById(R.id.dgCannel);
        if (!TextUtils.isEmpty(cancelBtnString)) {
            cancelButton.setText(cancelBtnString);
        }
        cancelButton.setOnClickListener(v -> {
            if (cancelBtnEvent != null) {
                cancelBtnEvent.onClick(v);
            }
            dialog.dismiss();
        });
        dialog.setCancelable(cancelable);

        return dialog;
    }

    public static Dialog getChangerNickNameDialog(Activity activity,
                                                  String title, String hint, String cancelBtnString,
                                                  String okBtnString, final ChangerEditDialogCallback callback) {

        final Dialog dialog = new AnimaAlertDialog(activity, R.style.Theme_CustomDialog);
        final View dialogview = LayoutInflater.from(activity).inflate(R.layout.dialog_name_edit, null);
        dialog.setContentView(dialogview);

        TextView titleView = (TextView) dialogview.findViewById(R.id.dgTitle);
        if (title == null) {
            titleView.setVisibility(View.GONE);
        } else {
            titleView.setText(title);
        }
        final EditText et_msg = (EditText) dialogview.findViewById(R.id.etMessage);
        et_msg.setText(hint);
        et_msg.setSelection(et_msg.getText().toString().length());

        Button okButton = (Button) dialogview.findViewById(R.id.dgOk);
        if (!TextUtils.isEmpty(okBtnString)) {
            okButton.setText(okBtnString);
        }
        okButton.setOnClickListener(v -> {
            if (callback != null) {
                String editContent = et_msg.getText().toString().trim();
                if (callback.okEvent(v, editContent)) {
                    return;
                }
            }
            dialog.dismiss();
        });

        final Button cancelButton = (Button) dialogview.findViewById(R.id.dgCannel);
        if (!TextUtils.isEmpty(cancelBtnString)) {
            cancelButton.setText(cancelBtnString);
        }
        cancelButton.setOnClickListener(v -> {
            if (callback != null) {
                if (callback.cancelEvent(v)) {
                    return;
                }
            }
            dialog.dismiss();
        });
        dialog.setCancelable(true);

        return dialog;
    }

    public static Dialog getListDialog(Activity activity, String title,
                                       List<String> data, AdapterView.OnItemClickListener listener) {
        final Dialog dialog = new AnimaAlertDialog(activity, R.style.Theme_CustomDialog);
        final View dialogview = LayoutInflater.from(activity).inflate(R.layout.dialog_list, null);
        ListView dialogList = (ListView) dialogview.findViewById(R.id.dialog_list);
        ((TextView) dialogview.findViewById(R.id.dialog_list_title)).setText(title);
        dialogList.setAdapter(new MyDialogListAdapter(activity, data));
        dialogList.setOnItemClickListener(listener);
        dialog.setContentView(dialogview);

        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = mDisplayMetrics.widthPixels / 4 * 3;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();
        return dialog;
    }

    public static Dialog getListDialog(Activity activity, String title,
                                       String[] data, AdapterView.OnItemClickListener listener) {
        final Dialog dialog = new AnimaAlertDialog(activity, R.style.Theme_CustomDialog);
        final View dialogview = LayoutInflater.from(activity).inflate(R.layout.dialog_list, null);
        ListView dialogList = (ListView) dialogview.findViewById(R.id.dialog_list);
        ((TextView) dialogview.findViewById(R.id.dialog_list_title)).setText(title);
        dialogList.setAdapter(new MyDialogArraryAdapter(activity, data));
        dialogList.setOnItemClickListener(listener);
        dialog.setContentView(dialogview);

        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = mDisplayMetrics.widthPixels / 4 * 3;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();
        return dialog;
    }

    public static Dialog getLoadingDialog(Activity activity, String msg,
                                          boolean cancelable,
                                          final DialogInterface.OnCancelListener cancelEvent) {
        final Dialog dialog = new Dialog(activity, R.style.Theme_CustomDialog);

        View contentView = LayoutInflater.from(activity).inflate(R.layout.dialog_loading, null);
        ImageView aniImage = (ImageView) contentView.findViewById(R.id.ivLoading);
        TextView msgView = (TextView) contentView.findViewById(R.id.tvMessage);
        Animation ani = AnimationUtils.loadAnimation(activity, R.anim.loading_ani);
        aniImage.startAnimation(ani);
        if (TextUtils.isEmpty(msg)) {
            msgView.setVisibility(View.GONE);
        } else {
            msgView.setVisibility(View.VISIBLE);
            msgView.setText(msg);
        }

        if (cancelable) {
            if (cancelEvent != null) {
                dialog.setOnCancelListener(cancelEvent);
            }
        } else {
            dialog.setCancelable(false);
        }
        dialog.setContentView(contentView);
        return dialog;
    }

    public static void showNewActionConfirmDialog(Activity activity,
                                                  String msg, String cancelBtnString, String okBtnString,
                                                  final View.OnClickListener cancelBtnEvent,
                                                  final View.OnClickListener okBtnEvent, int type) {

        final Dialog dialog = new Dialog(activity, R.style.shareDialogTheme);
        final View dialogview = LayoutInflater.from(activity).inflate(R.layout.dialog_comm_bottom, null);
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        window.setLayout(dm.widthPixels - ADensityUtil.dip2px(activity, 14), ViewGroup.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.y = ADensityUtil.dip2px(activity, 7);
        window.setAttributes(lp);
        dialog.getWindow().setContentView(dialogview);

        TextView msgTextView = (TextView) dialogview.findViewById(R.id.dgMsg);
        if (msg != null) {
            msgTextView.setText(msg);
        }

        TextView okButton = (TextView) dialogview.findViewById(R.id.dgOk);
        if (!TextUtils.isEmpty(okBtnString)) {
            okButton.setText(okBtnString);
            if (type == PROMPT) {
                okButton.setTextColor(ContextCompat.getColor(activity, R.color.black));
            } else if (type == WARNING) {
                okButton.setTextColor(ContextCompat.getColor(activity, R.color.error_color));
            }
        }
        okButton.setOnClickListener(v -> {
            if (okBtnEvent != null) {
                okBtnEvent.onClick(v);
            }
            dialog.dismiss();
        });

        final TextView cancelButton = (TextView) dialogview.findViewById(R.id.dgCannel);
        if (!TextUtils.isEmpty(cancelBtnString)) {
            cancelButton.setText(cancelBtnString);
        }
        cancelButton.setOnClickListener(v -> {
            if (cancelBtnEvent != null) {
                cancelBtnEvent.onClick(v);
            }
            dialog.dismiss();
        });
        dialog.setCancelable(true);
    }

    public interface onClickListener {
        boolean onClick();
    }

    public interface ChangerEditDialogCallback {
        boolean okEvent(View v, String editContent);

        boolean cancelEvent(View v);
    }

    public static class MyDialogListAdapter extends BaseAdapter {
        private List<String> data;
        private LayoutInflater mInflater;

        public MyDialogListAdapter(Context context, List<String> data) {
            this.data = data;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = mInflater.inflate(R.layout.dialog_list_item, null);
            ((TextView) convertView.findViewById(R.id.dialog_item_title))
                    .setText(data.get(position));
            return convertView;
        }

    }

    public static class MyDialogArraryAdapter extends BaseAdapter {
        private String[] data;
        private LayoutInflater mInflater;

        public MyDialogArraryAdapter(Context context, String[] data) {
            this.data = data;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public Object getItem(int position) {
            return data[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = mInflater.inflate(R.layout.dialog_list_item, null);
            ((TextView) convertView.findViewById(R.id.dialog_item_title)).setText(data[position]);
            return convertView;
        }
    }
}
