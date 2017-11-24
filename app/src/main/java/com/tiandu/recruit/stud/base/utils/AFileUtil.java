package com.tiandu.recruit.stud.base.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 文件操作类
 * Created by Nereo on 2015/4/8.
 */
public class AFileUtil {

    public final static String FILE_EXTENSION_SEPARATOR = ".";
    public final static String tag = "FileUtil";

    /**
     * 关闭流
     * @param closeables
     */
    public static void closeIO(Closeable... closeables) {
        if (null == closeables || closeables.length <= 0) {
            return;
        }
        for (Closeable cb : closeables) {
            try {
                if (null == cb) {
                    continue;
                }
                cb.close();
            } catch (IOException e) {
                Log.e(tag, "close IO ERROR...", e);
            }
        }
    }

    public static File createTmpFile(Context context){
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            // 已挂载
            File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
            String fileName = "multi_image_"+timeStamp+"";
            File tmpFile = new File(pic, fileName+".jpg");
            return tmpFile;
        }else{
            File cacheDir = context.getCacheDir();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
            String fileName = "multi_image_"+timeStamp+"";
            File tmpFile = new File(cacheDir, fileName+".jpg");
            return tmpFile;
        }
    }

    public static StringBuilder readFile(String filePath, String charsetName) {
        File file = new File(filePath);
        StringBuilder fileContent = new StringBuilder("");
        if (file == null || !file.isFile()) {
            return null;
        }

        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(
                    file), charsetName);
            reader = new BufferedReader(is);
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (!fileContent.toString().equals("")) {
                    fileContent.append("\r\n");
                }
                fileContent.append(line);
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    public static boolean writeFile(String filePath, String content,
                                    boolean append) {
        if(TextUtils.isEmpty(filePath)){
            Log.e(tag,"filePath==null");
            return false;
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath, append);
            if(fileWriter==null	){
                Log.e(tag, "fileWriter==null");
                return false;
            }
            fileWriter.write(content);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            Log.e(tag, "IOException occurred. ", e);
            return false;

        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    return false;
                }
            }
        }
    }

    public static boolean writeFile(String filePath, InputStream stream) {
        OutputStream o = null;
        try {
            o = new FileOutputStream(filePath);
            byte data[] = new byte[1024];
            int length = -1;
            while ((length = stream.read(data)) != -1) {
                o.write(data, 0, length);
            }
            o.flush();
            return true;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (o != null) {
                try {
                    o.close();
                    stream.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * 删除该目录下的文件
     * @param path
     */
    public static void delFile(String path) {
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * 把图片压缩到200K
     * @param oldpath
     * @param newPath
     * @return
     */
    public static File compressFile(String oldpath, String newPath) {
        Bitmap compressBitmap = BitmapFactory.decodeFile(oldpath);
        Bitmap newBitmap = ratingImage(oldpath, compressBitmap);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        newBitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
        byte[] bytes = os.toByteArray();
        File file = null ;
        try {
            file = AFileUtil.getFileFromBytes(bytes, newPath);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(newBitmap != null ){
                if(!newBitmap.isRecycled()){
                    newBitmap.recycle();
                }
                newBitmap  = null;
            }
            if(compressBitmap != null ){
                if(!compressBitmap.isRecycled()){
                    compressBitmap.recycle();
                }
                compressBitmap  = null;
            }
        }
        return file;
    }

    /**
     * 把字节数组保存为一个文件
     * @param b
     * @param outputFile
     * @return
     */
    public static File getFileFromBytes(byte[] b, String outputFile) {
        File ret = null;
        BufferedOutputStream stream = null;
        try {
            ret = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(ret);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    private static Bitmap ratingImage(String filePath,Bitmap bitmap){
        int degree = readPictureDegree(filePath);
        return rotaingImageView(degree, bitmap);
    }

    /**
     * 读取图片属性：旋转的角度
     * @param path
     * @return
     */
    public static int readPictureDegree(String path) {
        int degree  = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片
     * @param angle
     * @param bitmap
     * @return
     */
    public static Bitmap rotaingImageView(int angle , Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        //System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    public static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
    }


    public static boolean isFileExist(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }

        File file = new File(filePath);
        return (file.exists() && file.isFile());
    }


    public static boolean isFolderExist(String directoryPath) {
        if (TextUtils.isEmpty(directoryPath)) {
            return false;
        }

        File dire = new File(directoryPath);
        return (dire.exists() && dire.isDirectory());
    }

    public static boolean deleteFile(String path) {
        if (TextUtils.isEmpty(path)) {
            return true;
        }

        File file = new File(path);
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        for (File f : file.listFiles()) {
            if (f.isFile()) {
                f.delete();
            } else if (f.isDirectory()) {
                deleteFile(f.getAbsolutePath());
            }
        }
        return file.delete();
    }

    public static long getFileSize(String path) {
        if (TextUtils.isEmpty(path)) {
            return -1;
        }

        File file = new File(path);
        return (file.exists() && file.isFile() ? file.length() : -1);
    }

    /**
     * 文件拷贝
     * @param source 源文件
     * @param dest 目标文件
     * @return
     */
    public static boolean copyFile(String source, String dest) {
        try {
            if (AFileUtil.isFileExist(source) && !TextUtils.isEmpty(dest)) {
                FileInputStream fileIn = new FileInputStream(source);
                FileOutputStream fileOut = new FileOutputStream(dest);
                byte[] buffer = new byte[8192];
                int length = 0;
                while ((length = fileIn.read(buffer)) != -1) {
                    fileOut.write(buffer, 0, length);
                }

                fileIn.close();
                fileOut.close();
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * 拷贝assets文件夹下的文件
     * @param context 上下文
     * @param source assets文件夹下源文件
     * @param dest 目标文件
     */
    public static boolean copyAssetsFile(Context context, String source, String dest) {
        try {
            if (context != null && AFileUtil.isFileExist(source)
                    && !TextUtils.isEmpty(dest)) {
                BufferedInputStream bufferIn = new BufferedInputStream(context
                        .getResources().getAssets().open(source));
                FileOutputStream fileOut = new FileOutputStream(dest);
                byte[] buffer = new byte[8192];
                int length = 0;
                while ((length = bufferIn.read(buffer)) != -1) {
                    fileOut.write(buffer, 0, length);
                }
                bufferIn.close();
                fileOut.close();
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 读取AssetsFile文件
     */
    public static String readAssetsFile(Context context,String source) {
        try {
            if (context != null && !AStringUtil.isEmpty(source)) {
                InputStream bufferIn = context.getAssets().open(source);
                ByteArrayOutputStream bo = new ByteArrayOutputStream();
                int i = bufferIn.read();
                while (i != -1) {
                    bo.write(i);
                    i = bufferIn.read();
                }
                return bo.toString();
            }
        } catch (IOException e) {

        }
        return "";
    }

    public static String getFolderName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
    }

    /**
     * 创建文件
     * @param filePath
     * @return
     */
    public static boolean createFile(String filePath) {
        if (TextUtils.isEmpty(filePath))
            return false;
        File file = new File(filePath);
        if (file.exists())
            return true;
        boolean result = false;
        try {
            if (makeDirs(filePath))
                result = file.createNewFile();
        } catch (IOException ex) {
        }
        return result;
    }

    /**
     * 根据文件路径，创建目录
     * @param filePath
     */
    public static boolean makeDirs(String filePath) {
        String folderName = getFolderName(filePath);
        if (TextUtils.isEmpty(folderName)) {
            return false;
        }
        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) ? true : folder
                .mkdirs();
    }

    /**
     * 根据文件夹路径，创建目录
     * @param fileDir
     * @return
     */
    public static boolean makeFolders(String fileDir) {
        if (TextUtils.isEmpty(fileDir)) {
            return false;
        }

        File folder = new File(fileDir);
        return (folder.exists() && folder.isDirectory()) ? true : folder
                .mkdirs();
    }

    public static String getDateFormatString(Date date) {
        if (date == null)
            date = new Date();
        String formatStr = new String();
        SimpleDateFormat matter = new SimpleDateFormat("yyyyMMddHHmmss");
        formatStr = matter.format(date);
        return formatStr;
    }



//    public static File scal(String path){
//        File outputFile = new File(path);
//        long fileSize = outputFile.length();
//        final long fileMaxSize = 450;
//        if (fileSize >= fileMaxSize) {
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inJustDecodeBounds = true;
//            BitmapFactory.decodeFile(path, options);
//            int height = options.outHeight;
//            int width = options.outWidth;
//
//            //double scale = Math.sqrt((float) fileSize / fileMaxSize);
//            options.outHeight = (int) (height);
//            options.outWidth = (int) (width);
//            options.inSampleSize = (int) (1);
//            options.inJustDecodeBounds = false;
//
//            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
//            outputFile = new File(createImageFile().getPath());
//            FileOutputStream fos = null;
//            try {
//                fos = new FileOutputStream(outputFile);
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 40, fos);
//                fos.close();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            if (!bitmap.isRecycled()) {
//                bitmap.recycle();
//            }else{
//                File tempFile = outputFile;
//                outputFile = new File(createImageFile().getPath());
//                copyFileUsingFileChannels(tempFile, outputFile);
//            }
//
//        }
//        return outputFile;
//
//    }
//
//    public static Uri createImageFile(){
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES);
//        File image = null;
//        try {
//            image = File.createTempFile(
//                    imageFileName,  /* prefix */
//                    ".jpg",         /* suffix */
//                    storageDir      /* directory */
//            );
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        // Save a file: path for use with ACTION_VIEW intents
//        return Uri.fromFile(image);
//    }
//    public static void copyFileUsingFileChannels(File source, File dest){
//        FileChannel inputChannel = null;
//        FileChannel outputChannel = null;
//        try {
//            try {
//                inputChannel = new FileInputStream(source).getChannel();
//                outputChannel = new FileOutputStream(dest).getChannel();
//                outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        } finally {
//            try {
//                inputChannel.close();
//                outputChannel.close();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }
}
