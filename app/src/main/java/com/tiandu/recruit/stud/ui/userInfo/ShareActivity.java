package com.tiandu.recruit.stud.ui.userInfo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.base.utils.AImageUtil;
import com.tiandu.recruit.stud.base.utils.SpUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;


/**
 * 项目名称：RecruitStud
 * 类描述：
 * 创建人：chendequnn
 * 创建时间：2017/11/21 16:34
 * 修改人：chendequnn
 * 修改时间：2017/11/21 16:34
 * 修改备注：
 */
public class ShareActivity extends BaseActivity {
    @BindView(R.id.im_qrcode)
    ImageView im_qrcode;
    @BindView(R.id.ivShare)
    ImageView ivShare;
    @Override
    protected void initView() {
        cannelDialog();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initPresenter() {
        Bitmap qrBitmap = generateBitmap("http://www.tdhr-rpo.com/MemberReg/MemberReg?ParentID="+ SpUtil.getMemberID(),400, 400);
        im_qrcode.setImageBitmap(qrBitmap);

    }
    @OnClick(R.id.ivShare) void shareClick(){
        showShare();
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }
    private Bitmap generateBitmap(String content, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("人人招会员注册");

        String url = "http://www.tdhr-rpo.com/MemberReg/MemberReg?ParentID="+ SpUtil.getMemberID();
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(url);

        // text是分享文本，所有平台都需要这个字段
//        oks.setText("人人招会员注册");

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_applogo);
        String imageUrl = AImageUtil.saveFile(bitmap, "icon");

        //imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(imageUrl);//确保SDcard下面存在此张图片

        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);

        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        //oks.setComment("我是测试评论文本");

        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));

        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);

        // 启动分享GUI
        oks.show(this);

    }
}