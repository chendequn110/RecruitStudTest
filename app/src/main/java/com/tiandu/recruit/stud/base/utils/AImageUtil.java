package com.tiandu.recruit.stud.base.utils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tiandu.recruit.stud.R;
import com.tiandu.recruit.stud.view.circle.GlideCircleTransform;
import com.tiandu.recruit.stud.view.circle.GlideRoundTransform;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

public class AImageUtil {

    /**
     * 普通
     */
    public static void loadImg2(ImageView v, String url,int errorImg) {
        Glide.with(v.getContext())
                .load(url)
                .placeholder(R.mipmap.ic_banner_1)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(errorImg)   //R.mipmap.ic_complaint
                .into(v);
    }
    public static void loadImg(ImageView v, String url,int errorImg) {
        Glide.with(v.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(errorImg)   //R.mipmap.ic_complaint
                .into(v);
    }

    /**
     * 圆形
     */
    public static void loadCircleImg(ImageView v,String url){
        Glide.with(v.getContext())
                .load(url)
                .placeholder(R.mipmap.ic_default_circle_icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideCircleTransform(v.getContext()))
                .error(R.mipmap.ic_default_circle_icon)
                .into(v);
    }

    /**
     * 圆角
     */
    public static void loadRoundImg(ImageView v, String url) {
        Glide.with(v.getContext())
                .load(url)
//                .placeholder(R.mipmap.ic_default_corner_icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideRoundTransform(v.getContext(),15))
                .error(R.mipmap.ic_applogo)
                .into(v);
    }

    public static void loadCircleImg2(ImageView v,String url){
        Glide.with(v.getContext())
                .load(url)
                .placeholder(R.mipmap.ic_default_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideCircleTransform(v.getContext()))
                .error(R.mipmap.ic_default_img)
                .into(v);
    }

    public static String getFuckUrl(String url) {
        if (url != null && url.startsWith("http://ear.duomi.com/wp-content/themes/headlines/thumb.php?src=")) {
            url = url.substring(url.indexOf("=") + 1, url.indexOf("jpg") > 0 ? url.indexOf("jpg") + 3 : url.indexOf("png") > 0 ? url.indexOf("png") + 3 : url.length());
            url = url.replace("kxt.fm", "ear.duomi.com");
        }
        return url;
    }

    /**
     * 保存文件
     *
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public static String saveFile(Bitmap bm, String fileName) {
        String s = Environment.getExternalStorageDirectory().toString();
        File dirFile = new File(s + "/DCIM/Camera/");
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(s + "/DCIM/Camera/" + fileName);
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(
                    new FileOutputStream(myCaptureFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
        try {
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myCaptureFile.getAbsolutePath();
    }

    /**
     * 获得指定文件的byte数组
     */
    public static byte[] getBytes(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("e","IOException");
        }
        return buffer;
    }

    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     *
     * @param variableName
     * @param c
     * @return
     */
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
