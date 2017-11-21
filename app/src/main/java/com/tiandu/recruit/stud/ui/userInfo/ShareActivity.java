package com.tiandu.recruit.stud.ui.userInfo;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.base.BaseActivity;
import com.tiandu.recruit.stud.base.utils.SpUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

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
        Bitmap qrBitmap = generateBitmap("http://advertise.shanghaiiot.org/MemberReg/MemberReg?ParentID="+ SpUtil.getMemberID(),400, 400);
        im_qrcode.setImageBitmap(qrBitmap);

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
}