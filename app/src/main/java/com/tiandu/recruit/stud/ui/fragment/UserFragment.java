package com.tiandu.recruit.stud.ui.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.api.Api;
import com.tiandu.recruit.stud.base.BaseAppManager;
import com.tiandu.recruit.stud.base.BaseLazyFragment;
import com.tiandu.recruit.stud.base.utils.AImageUtil;
import com.tiandu.recruit.stud.base.utils.SpUtil;
import com.tiandu.recruit.stud.base.utils.helper.RxSchedulers;
import com.tiandu.recruit.stud.data.C;
import com.tiandu.recruit.stud.data.entity.MeInfo;
import com.tiandu.recruit.stud.data.entity.RegisterInfo;
import com.tiandu.recruit.stud.data.event.PhotoEvent;
import com.tiandu.recruit.stud.ui.login.LoginActivity;
import com.tiandu.recruit.stud.ui.modify.ModifyPwdActivity;
import com.tiandu.recruit.stud.ui.userInfo.BindInfoActivity;
import com.tiandu.recruit.stud.ui.userInfo.CertificationActivity;
import com.tiandu.recruit.stud.ui.userInfo.ChildMemberInfoActivity;
import com.tiandu.recruit.stud.ui.userInfo.MeActivity;
import com.tiandu.recruit.stud.ui.userInfo.SettingActivity;
import com.tiandu.recruit.stud.ui.userInfo.ShareActivity;
import com.tiandu.recruit.stud.view.circle.GlideCircleTransform;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.utils.AndroidLifecycleUtils;
import rx.functions.Action1;

/**
 * Created by Jerome on 2016/10/27.
 * Email :jeromekai8@gmail.com
 */

public class UserFragment extends BaseLazyFragment {

    @BindView(R.id.ivAvatar) ImageView ivAvatar;
    @BindView(R.id.tvName) TextView tvName;
    @BindView(R.id.tvUserInfo) TextView tvUserInfo;
    @BindView(R.id.tvWall) TextView tvWall;
    @BindView(R.id.tvExit) TextView tvExit;
    @BindView(R.id.tvAccountBind) TextView tvAccountBind;
    @BindView(R.id.tvGetUserInfo) TextView tvGetUserInfo;
    @BindView(R.id.tvGetAuthentic) TextView tvGetAuthentic;
    @BindView(R.id.tvMore) TextView tvMore;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private String photo="";
    private Uri uri;



    @Override
    protected void initViewsAndEvents() {

    }

    @OnClick({R.id.ivAvatar,R.id.tvModifyPwd,R.id.llUserInfo, R.id.llWall,R.id.tvAboutUs,R.id.tvExit,R.id.tvAccountBind,R.id.llAuthentic,R.id.llChildInfo,R.id.tvMore})
    void OnSelectedClick(View view) {
        switch (view.getId()) {
            case R.id.tvModifyPwd:
                readyGo(ModifyPwdActivity.class);
                break;
            case R.id.llUserInfo:
                readyGo(MeActivity.class);
                break;
            case R.id.ivAvatar:
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(true)
                        .setPreviewEnabled(false)
                        .setSelected(selectedPhotos)
                        .start(this.getActivity());
                break;
            case R.id.tvAccountBind:
                readyGo(BindInfoActivity.class);
                break;
            case R.id.llAuthentic:
                readyGo(CertificationActivity.class);
                break;
            case R.id.llChildInfo:
                readyGo(ChildMemberInfoActivity.class);
                break;
            case R.id.tvExit:
                exitLogin();
                break;
            case R.id.tvMore:
                readyGo(SettingActivity.class);
                break;
            case R.id.llWall:
                readyGo(ShareActivity.class);
                break;
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_userinfo;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    private View view;

    @Override
    protected void onFirstUserVisible() {
        view = getActivity().findViewById(R.id.statusBar);
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        }

        if (isUser()) {
            initUserInfo();
            showHeadImage(SpUtil.getHeadImage());
        }
    }

    @Override
    protected void onUserVisible() {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        }
        showHeadImage(SpUtil.getHeadImage());
    }

    @Override
    protected void onFirstUserInvisible() {
    }

    @Override
    protected void onUserInvisible() {
    }

    private void initUserInfo() {
        Api.getInstance()
        .movieService.getUserInfo(C.USER_USERINFO_PATH,SpUtil.getMemberID(), SpUtil.getToken())
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<MeInfo>>() {
                    @Override
                    public void call(List<MeInfo> meInfos) {
                        if (null != meInfos) {
                            tvGetAuthentic.setText(meInfos.get(0).getStatusName());
                            tvGetUserInfo.setText(meInfos.get(0).getMemberID());
                        }else {

                        }
                    }
                }, e -> {

                });
    }


//    private void showUserInfo(UserInfo.DataBean dataBean) {
//        if (null != dataBean) {
//            AImageUtil.loadCircleImg2(ivAvatar, dataBean.getIcon());
//            if (null != dataBean.getIcon()) {
//                SpUtil.setIcon(dataBean.getIcon());
//            }
//            if (null != dataBean.getName()) {
//                tvName.setText(dataBean.getName());
//                SpUtil.setName(dataBean.getName());
//            }
//        }
//
//    }

    private void showMessage(String message) {
        showToast(message);
    }

    private void exitLogin() {
        BaseAppManager.getInstance().clearAll();
//        Bundle bundle = new Bundle();
//        bundle.putString(C.LOGIN_ACCOUNT, SpUtil.getAccount());
//        bundle.putInt(C.USER_OUT, C.USER_OUT_STATUS);
        SpUtil.clearAll();
        readyGo(LoginActivity.class);
//        BaseAppManager.getInstance().clearToTop();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPhotoEvent(PhotoEvent event) {
        if (event != null) {
             photo = event.getPhoto();
             SpUtil.setHeadImage(photo);

            boolean canLoadImage = AndroidLifecycleUtils.canLoadImage(ivAvatar.getContext());

            if (canLoadImage) {
                showHeadImage(photo);
            }
        }
        File file = scal(photo);
        updataImage(file);
    }
    private void showHeadImage(String photo) {
        Uri uri = Uri.fromFile(new File(photo));
        Glide.with(this)
                .load(uri)
                .placeholder(R.mipmap.ic_default_circle_icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideCircleTransform(ivAvatar.getContext()))
                .error(R.mipmap.ic_default_img)
                .into(ivAvatar);
    }
    private void updataImage(File file) {
        Api.getInstance()
                .movieService.updateHeadImage(C.USER_HEADIMAGE,SpUtil.getMemberID(), SpUtil.getToken(), AImageUtil.getBytes(file))
                .compose(RxSchedulers.io_main())
                .compose(RxSchedulers.sTransformer())
                .subscribe(new Action1<List<RegisterInfo>>() {
                    @Override
                    public void call(List<RegisterInfo> meInfos) {

                    }
                }, e -> {

                });
    }
    //TODO 压缩图片
    public static File scal(String path){
        File outputFile = new File(path);
        long fileSize = outputFile.length();
        final long fileMaxSize = 100 * 1024;

        if (fileSize >= fileMaxSize) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            int height = options.outHeight;
            int width = options.outWidth;
            double scale = Math.sqrt((float) fileSize / fileMaxSize);

            options.outHeight = (int) (height);
            options.outWidth = (int) (width);

            if ((int) Math.round(scale) <= 1) {
                options.inSampleSize = 2;
            } else {
                options.inSampleSize = (int) Math.round(scale);
            }
            //像素压缩比例，压缩
            options.inJustDecodeBounds = false;

            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            outputFile = new File(createImageFile().getPath());
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(outputFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG,65, fos);
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }else{
                File tempFile = outputFile;
                outputFile = new File(createImageFile().getPath());
                copyFileUsingFileChannels(tempFile, outputFile);
            }

        }
        return outputFile;
    }
    public static void copyFileUsingFileChannels(File source, File dest){
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            try {
                inputChannel = new FileInputStream(source).getChannel();
                outputChannel = new FileOutputStream(dest).getChannel();
                outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } finally {
            try {
                inputChannel.close();
                outputChannel.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public static Uri createImageFile(){
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Save a file: path for use with ACTION_VIEW intents
        return Uri.fromFile(image);
    }
}
