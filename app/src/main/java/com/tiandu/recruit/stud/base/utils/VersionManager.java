package com.tiandu.recruit.stud.base.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Jerome on 16/5/23.
 * Email :jeromekai8@gmail.com
 */
public class VersionManager {

    private static final int DOWN_ERROR = 1;

    // 测试用
    private String apkurl = "http://i1.sinaimg.cn/edu/sinaopen/SinaOpencourse_V2.02.apk";

    private Context context;
    private String apkName = "EduClient" + FileCst.SUFFIX_APK;

    private static VersionManager manager;
    private ProgressDialog dialog;

    private boolean isDownloading = false;

    public VersionManager(Context context){
        this.context = context;
    }

    public synchronized void downLoadApk(String apkUrl) {
        if (!isDownloading) {
            apkurl = apkUrl;
            dialog = new ProgressDialog(context);
            dialog.setMessage("正在下载更新");
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setCancelable(false);
            downloadApkTask();
        }
    }

    public boolean isDownLoading() {
        return isDownloading;
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_ERROR:
                    String mssage = (String) msg.obj;
                    Toast.makeText(context, mssage, Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    /**
     * 下载apk
     */
    private void downloadApkTask() {
        new DownloadTask().execute(apkurl);
    }

    private class DownloadTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            isDownloading = true;
            // 显示对话框
            if (dialog != null && !dialog.isShowing())
                dialog.show();
        }

        @Override
        protected String doInBackground(String... urls) {
            FileOutputStream fos = null;
            InputStream is = null;
            try {
                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    URL url = new URL(urls[0]);
                    HttpURLConnection conn = (HttpURLConnection) url
                            .openConnection();
                    conn.setConnectTimeout(5000);
                    // 获取到文件的大小
                    dialog.setMax(conn.getContentLength());
                    is = conn.getInputStream();

                    String filePath = AStorageUtil.createFilePath(context, apkName);
                    File file = new File(filePath);

                    fos = new FileOutputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    byte[] buffer = new byte[1024];
                    int len;
                    int progress = 0;
                    while ((len = bis.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                        progress += len;
                        // 获取当前下载量
                        publishProgress(progress);
                    }
                    fos.close();
                    bis.close();
                    is.close();
                    return file.getAbsolutePath();
                }
            } catch (MalformedURLException e) {
                Message msg = handler.obtainMessage(DOWN_ERROR, "无效的URL");
                handler.sendMessage(msg);
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                Message msg = handler.obtainMessage(DOWN_ERROR, "储存错误");
                handler.sendMessage(msg);
                e.printStackTrace();
            } catch (IOException e) {
                Message msg = handler.obtainMessage(DOWN_ERROR, "数据读取失败");
                handler.sendMessage(msg);
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            return "";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // 更新下载进度
            dialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String uri) {
            super.onPostExecute(uri);
            isDownloading = false;
            if (dialog != null && dialog.isShowing())
                dialog.dismiss();
            installApk(uri);
        }

    }

    private void installApk(String uri) {
        // 替换安装apk
        if (!TextUtils.isEmpty(uri)) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setDataAndType(Uri.parse("file://" + uri),
                    "application/vnd.android.package-archive");
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "下载失败，网络连接错误", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 获取当前应用版本号
     */
    public String getVersion() {
        // PackageManager :包管理器
        PackageManager pm = context.getPackageManager();
        try {
            // 功能清单文件
            PackageInfo packInfo = pm.getPackageInfo(context.getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
