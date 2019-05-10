package com.nj.baijiayun.imageloader.loader;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.bumptech.glide.MemoryCategory;
import com.nj.baijiayun.imageloader.config.GlobalConfig;
import com.nj.baijiayun.imageloader.config.SingleConfig;


/**
 * @author chengang
 */
public class ImageLoader {
    /**
     * 默认最大缓存
     */
    private static final int CACHE_IMAGE_SIZE = 250;

    public static GlobalConfig init(final Context context) {
        return init(context, CACHE_IMAGE_SIZE);
    }

    public static GlobalConfig init(final Context context, int cacheSizeInM) {
        return init(context, cacheSizeInM, MemoryCategory.NORMAL);
    }

    public static GlobalConfig init(final Context context, int cacheSizeInM, MemoryCategory memoryCategory) {
        return init(context, cacheSizeInM, memoryCategory, true);
    }

    public static GlobalConfig getConfig() {
        return GlobalConfig.getInstance();
    }

    /**
     * @param context        上下文
     * @param cacheSizeInM   Glide默认磁盘缓存最大容量250MB
     * @param memoryCategory 调整内存缓存的大小 LOW(0.5f) ／ NORMAL(1f) ／ HIGH(1.5f);
     * @param isInternalCD   true 磁盘缓存到应用的内部目录 / false 磁盘缓存到外部存
     */
    public static GlobalConfig init(final Context context, int cacheSizeInM, MemoryCategory memoryCategory, boolean isInternalCD) {
        return GlobalConfig.init(context, cacheSizeInM, memoryCategory, isInternalCD);
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


    public static void pauseRequests() {
        getActualLoader().pause();

    }

    public static void resumeRequests() {
        getActualLoader().resume();
    }


    public static void trimMemory(int level){
        getActualLoader().trimMemory(level);
    }
    /**
     * Clears disk cache.
     *
     * <p>
     * This method should always be called on a background thread, since it is a blocking call.
     * </p>
     */
    public static void clearDiskCache() {
        getActualLoader().clearDiskCache();
    }

    /**
     * Clears as much memory as possible.
     */
    public static void clearMemory() {
        getActualLoader().clearMemory();
    }

}
