package com.tiandu.recruit.stud.base.utils;


import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * public method <li>getRootDir(Context) 获取可用存储空间的根目录</li> <li>
 * getFileType(String) 获取文件类型</li> <li>getFileDir(Context, int)
 * 根据文件类型获取文件目录名</li> <li>getFileDir(Context, String) 根据文件名获取文件目录名</li>
 * <li>createFileDir(Context, int) 根据文件名创建文件目录</li> <li>
 * createFileDir(Context, String) 根据文件名创建文件目录</li> <li>
 * getFilePath(Context, String) 获取文件的绝对路径</li> <li>createFilePath(Context,
 * String) 创建文件</li> <li>getLeftSpace(String) 获取指定目录剩余存储空间</li> <li>
 * getTotalSpace(String) 获取指定目录所有存储空间</li> private method
 *
 * 文件目录管理类
 * @author：Jerome
 * @create：2015-3-19下午7:34:56
 * @email : kai.zhang15@pactera.com
 */
public class AStorageUtil {

    private static final String tag = AStorageUtil.class.getSimpleName();

    /**
     * 获取可用存储空间的根目录
     * @param context
     * @return
     */
    public static String getRootDir(Context context) {
        // 先找外置存储路径
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
//            return context.getExternalFilesDir("").getAbsolutePath();
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }

        // 再找内置SDCard
        for (VoldFstab vold : mVolds) {
            File mount = new File(vold.mMountPoint);
            if (mount.exists() && mount.canRead() && mount.canWrite()) {
                // && mount.canExecute()
                return mount.getAbsolutePath();
            }
        }

        // 再找本地应用内存路径
        if (context != null) {
            return context.getFilesDir().getAbsolutePath();
        }
        return null;
    }

    /**
     * 获取文件类型，文件类型请查看FileCst定义
     * @param fileName 文件名
     * @return
     */
    public static int getFileType(String fileName) {
        if (TextUtils.isEmpty(fileName))
            return FileCst.TYPE_ERROR;
        fileName = fileName.toLowerCase();

        if (fileName.endsWith(FileCst.SUFFIX_PNG)
                || fileName.endsWith(FileCst.SUFFIX_JPG)
                || fileName.endsWith(FileCst.SUFFIX_JPE)
                || fileName.endsWith(FileCst.SUFFIX_JPEG)
                || fileName.endsWith(FileCst.SUFFIX_BMP)
                || fileName.endsWith(FileCst.SUFFIX_GIF)) { // 图片类型
            return FileCst.TYPE_IMAGE;
        } else if (fileName.endsWith(FileCst.SUFFIX_MP4)
                || fileName.endsWith(FileCst.SUFFIX_3GPP)
                || fileName.endsWith(FileCst.SUFFIX_M4A)
                || fileName.endsWith(FileCst.SUFFIX_AMR)) { // 音频类型
            return FileCst.TYPE_AUDIO;
        } else if (fileName.endsWith(FileCst.SUFFIX_VID)) { // 视频类型
            return FileCst.TYPE_VIDEO;
        } else if (fileName.endsWith(FileCst.SUFFIX_APK)
                || fileName.endsWith(FileCst.SUFFIX_DEX)) { // 安装包类型
            return FileCst.TYPE_APK;
        } else if (fileName.endsWith(FileCst.SUFFIX_TXT)) { // 文本类型
            return FileCst.TYPE_TXT;
        } else if (fileName.endsWith(FileCst.SUFFIX_LOG)) { // 日志类型
            return FileCst.TYPE_LOG;
        } else if (fileName.endsWith(FileCst.SUFFIX_RAR)
                || fileName.endsWith(FileCst.SUFFIX_ZIP)) { // 压缩包类型
            return FileCst.TYPE_ZIP;
        } else if (fileName.endsWith(FileCst.SUFFIX_DB)) { // 数据存储类型
            return FileCst.TYPE_DATA;
        } else { // 未知
            return FileCst.TYPE_UNKNOWN;
        }
    }

    /**
     * 创建目录
     */
    public static File createDir(String path, String fileName) {

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        File dir = new File(path, fileName);

        return dir;
    }

    /**
     * 获取文件目录名, 相对目录请查看FileCst定义
     */
    public static String getFileDir(Context context, int fileType) {
        String rootDir = getRootDir(context);

        if (TextUtils.isEmpty(rootDir))
            return null;

        String directory = FileCst.DIR_UNKNOWN;
        switch (fileType) {
            case FileCst.TYPE_IMAGE:
                directory = FileCst.DIR_IMAGE;
                break;
            case FileCst.TYPE_AUDIO:
                directory = FileCst.DIR_AUDIO;
                break;
            case FileCst.TYPE_VIDEO:
                directory = FileCst.DIR_VIDEO;
                break;
            case FileCst.TYPE_APK:
                directory = FileCst.DIR_APK;
                break;
            case FileCst.TYPE_TXT:
                directory = FileCst.DIR_TXT;
                break;
            case FileCst.TYPE_LOG:
                directory = FileCst.DIR_LOG;
                break;
            case FileCst.TYPE_ZIP:
                directory = FileCst.DIR_ZIP;
                break;
            case FileCst.TYPE_DATA:
                directory = FileCst.DIR_DATA;
                break;
            case FileCst.TYPE_UNKNOWN:
                directory = FileCst.DIR_UNKNOWN;
                break;
            case FileCst.TYPE_ERROR:
            default:
                directory = null;
                break;
        }

        return directory == null ? null : rootDir + File.separator + directory;
    }

    /**
     * 根据文件名获取文件目录名, 相对目录请查看FileCst定义
     * @param context
     * @param fileName
     * @return
     * @see #getFileDir(Context, int)
     */
    public static String getFileDir(Context context, String fileName) {
        int fileType = getFileType(fileName);
        return getFileDir(context, fileType);
    }

    /**
     * 根据文件类型创建文件的目录
     *
     * @param context
     * @param fileType
     * @return
     */
    public static String createFileDir(Context context, int fileType) {
        String fileDir = getFileDir(context, fileType);
        return AFileUtil.makeFolders(fileDir) ? fileDir : null;
    }

    /**
     * 根据文件名创建文件的目录
     *
     * @param context
     * @param fileName
     * @return
     * @see #createFileDir(Context, int)
     */
    public static String createFileDir(Context context, String fileName) {
        int fileType = getFileType(fileName);
        return createFileDir(context, fileType);
    }

    /**
     * 获取文件的绝对路径
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getFilePath(Context context, String fileName) {
        String fileDir = getFileDir(context, fileName);
        // 创建文件目录
        File f = new File(fileDir);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
        }
        return TextUtils.isEmpty(fileDir) ? null : fileDir + File.separator
                + fileName;
    }

    /**
     * 创建文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String createFilePath(Context context, String fileName) {
        String fileDir = createFileDir(context, fileName);
        if (TextUtils.isEmpty(fileDir)) {
            return null;
        } else {
            String filePath = fileDir + File.separator + fileName;
            return AFileUtil.createFile(filePath) ? filePath : null;
        }

    }

    /**
     * 获取指定目录剩余存储空间，返回单位为字节
     *
     * @param directory
     * @return
     */
    public static long getLeftSpace(String directory) {
        if (TextUtils.isEmpty(directory))
            return 0;
        long space = 0;
        try {
            StatFs sf = new StatFs(directory);
            space = (long) sf.getBlockSize() * sf.getAvailableBlocks();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return space;
    }

    /**
     * 获取指定目录所有存储空间, 返回单位为字节
     *
     * @param directory
     * @return
     */
    public static long getTotalSpace(String directory) {
        if (TextUtils.isEmpty(directory))
            return 0;
        long space = 0;
        try {
            StatFs sf = new StatFs(directory);
            space = (long) sf.getBlockSize() * sf.getBlockCount();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return space;
    }

    private static final String DEV_MOUNT = "dev_mount";
    private static ArrayList<VoldFstab> mVolds;

    static {
        mVolds = new ArrayList<VoldFstab>();
        BufferedReader reader = null;

        try {
            // vold.fstab文件
            File file = new File(Environment.getRootDirectory()
                    .getAbsoluteFile()
                    + File.separator
                    + "etc"
                    + File.separator + "vold.fstab");
            reader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith(DEV_MOUNT)) {
                    String[] infos = line.split(" ");
                    VoldFstab vold = new VoldFstab();
                    vold.mLabel = infos[1]; // 设置标签
                    vold.mMountPoint = infos[2].split(":")[0];// 设置挂载点
                    vold.mPart = infos[3];// 设置子分区个数
                    vold.mSysfs = infos[4].split(":");// 设置设备在sysfs文件系统下的路径
                    mVolds.add(vold);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * vold配置文件， 文件位置/etc/vold.fstab example: # external sd card
     * dev_mount sdcard-ext /mnt/sdcard-ext:none:lun1 auto
     * /devices/platform/goldfish_mmc.0
     * /devices/platform/mmci-omap-hs.0/mmc_host/mmc0 # internal eMMC
     * dev_mount sdcard /mnt/sdcard 25
     * /devices/platform/mmci-omap-hs.1/mmc_host/mmc1 ## Example of a dual
     * card setup # dev_mount left_sdcard /sdcard1 auto
     * /devices/platform/goldfish_mmc.0
     * /devices/platform/msm_sdcc.2/mmc_host/mmc1 # dev_mount right_sdcard
     * /sdcard2 auto /devices/platform/goldfish_mmc.1
     * /devices/platform/msm_sdcc.3/mmc_host/mmc1 ## Example of specifying
     * a specific partition for mounts # dev_mount sdcard /sdcard 2
     * /devices/platform/goldfish_mmc.0
     * /devices/platform/msm_sdcc.2/mmc_host/mmc1 # flash drive connection
     * through hub connected to USB3 dev_mount usbdisk_1.1
     * /mnt/usbdisk_1.1 auto /devices/platform/musb_hdrc/usb3/3-1/3-1.1
     */
    private static class VoldFstab {
        // 标签
        public String mLabel;
        // 挂载点
        public String mMountPoint;
        // 子分区个数
        public String mPart;
        // 设备在sysfs文件系统下的路径
        public String[] mSysfs;
    }
}
