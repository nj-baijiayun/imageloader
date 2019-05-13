package com.nj.baijiayun.imageloader.loader;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.bumptech.glide.MemoryCategory;
import com.nj.baijiayun.imageloader.config.GlobalConfig;
import com.nj.baijiayun.imageloader.config.SingleConfig;


/**
 * @author chengang
 */
public class ImageLoader implements ILoader {
    /**
     * 默认最大缓存
     */

    public static final int DEFAULT_DISK_CACHE_SIZE = 250;
    public static final String DEFAULT_DISK_CACHE_DIR = "image_manager_disk_cache";


    public static GlobalConfig init(final Context context) {
        return init(context, DEFAULT_DISK_CACHE_DIR);
    }

    public static GlobalConfig init(final Context context, String diskCacheName) {
        return init(context, diskCacheName,DEFAULT_DISK_CACHE_SIZE);
    }

    public static GlobalConfig init(final Context context, String diskCacheName, int cacheSizeInM) {
        return init(context, diskCacheName, cacheSizeInM,MemoryCategory.NORMAL);
    }
    /**
     * @param context        上下文
     * @param cacheSizeInM   Glide默认磁盘缓存最大容量250MB
     * @param memoryCategory 调整内存缓存的大小 LOW(0.5f) ／ NORMAL(1f) ／ HIGH(1.5f);
     */
    public static GlobalConfig init(final Context context, String diskCacheName, int cacheSizeInM, MemoryCategory memoryCategory) {
        //true 磁盘缓存到应用的内部目录 / false 磁盘缓存到外部存
        return GlobalConfig.init(context, diskCacheName, cacheSizeInM, memoryCategory, true);
    }




    public static GlobalConfig getConfig() {
        return GlobalConfig.getInstance();
    }


    @Override
    public void init(Context context, String diskCacheName, int cacheSizeInM, MemoryCategory memoryCategory, boolean isInternalCD) {
        getActualLoader().init(context, diskCacheName, cacheSizeInM, memoryCategory, isInternalCD);
    }

    @Override
    public void request(SingleConfig config) {
        getActualLoader().request(config);
    }


    @Override
    public void resumeRequests(Context context) {
        getActualLoader().resumeRequests(context);
    }

    @Override
    public void pauseRequests(Context context) {
        getActualLoader().pauseRequests(context);
    }

    @Override
    public void resumeRequests(Fragment fragment) {
        getActualLoader().resumeRequests(fragment);
    }

    @Override
    public void pauseRequests(Fragment fragment) {
        getActualLoader().pauseRequests(fragment);
    }

    @Override
    public void clearDiskCache() {
        getActualLoader().clearDiskCache();
    }

    @Override
    public void clearMemory() {
        getActualLoader().clearMemory();
    }

    @Override
    public void trimMemory(int level) {
        getActualLoader().trimMemory(level);
    }

    /**
     * 获取当前的Loader
     *
     * @return r
     */
    public static ILoader getActualLoader() {
        return GlobalConfig.getInstance().getLoader();
    }

    /**
     * 加载普通图片
     *
     * @param context c
     * @return r
     */
    public static SingleConfig.ConfigBuilder with(Context context) {
        return new SingleConfig.ConfigBuilder(context);
    }

    /**
     * 加载普通图片
     *
     * @param fragment c
     * @return r
     */
    public static SingleConfig.ConfigBuilder with(Fragment fragment) {
        return new SingleConfig.ConfigBuilder(fragment);
    }


}
