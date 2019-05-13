package com.nj.baijiayun.imageloader.cache;

import android.content.Context;

import java.io.File;
import java.math.BigDecimal;


public class GlideCatchUtil {
    private static final String DEFAULT_PATH = "glide";
    private String mDiskCachePath = DEFAULT_PATH;
    private static GlideCatchUtil instance;
    private static Context mContext;
    public static GlideCatchUtil getInstance(Context context) {
        mContext=context;
        if (null == instance) {
            instance = new GlideCatchUtil();
        }
        return instance;
    }

    /**
     * 获取Glide磁盘缓存大小
     * @return
     */
    public String getCacheSize() {
        try {
            return getFormatSize(getFolderSize(new File(mContext.getCacheDir() + "/" + mDiskCachePath)));
        } catch (Exception e) {
            e.printStackTrace();
            return "获取失败";
        }
    }

    /**
     * 清除Glide磁盘缓存，自己获取缓存文件夹并删除方法
     * @return
     */
    public boolean cleanCatchDisk() {
        return deleteFolderFile(mContext.getCacheDir() + "/" +mDiskCachePath, true);
    }


    /**
     * 获取指定文件夹内所有文件大小的和
     * @param file
     * @return
     * @throws Exception
     */
    private long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    // 格式化单位
    private static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    /**
     * 按目录删除文件夹文件方法
     * @param filePath
     * @param deleteThisPath
     * @return
     */
    private boolean deleteFolderFile(String filePath, boolean deleteThisPath) {
        try {
            File file = new File(filePath);
            if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (File file1 : files) {
                    deleteFolderFile(file1.getAbsolutePath(), true);
                }
            }
            if (deleteThisPath) {
                if (!file.isDirectory()) {
                    file.delete();
                } else {
                    if (file.listFiles().length == 0) {
                        file.delete();
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setDiskCacheDir(String path) {
        mDiskCachePath = path;
    }
}