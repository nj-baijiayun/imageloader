package com.nj.baijiayun.imageloader.config;

import android.content.Context;

import com.bumptech.glide.MemoryCategory;
import com.nj.baijiayun.imageloader.loader.GlideLoader;
import com.nj.baijiayun.imageloader.loader.ILoader;


/**
 * @author chengang
 */
public class GlobalConfig {

    private Context context;
    private ILoader loader;
    private int cacheMaxSize;
    private int placeholderResId;
    private int errorResId;

    private static class SingletonHolder {
        private static GlobalConfig instance = new GlobalConfig();
    }

    public static GlobalConfig getInstance() {
        return SingletonHolder.instance;
    }


    public static GlobalConfig init(Context context, int cacheSizeInM, MemoryCategory memoryCategory, boolean isInternalCD) {
        GlobalConfig instance = getInstance();
        instance.context = context;
        instance.cacheMaxSize = cacheSizeInM;
        instance.getLoader().init(context, cacheSizeInM, memoryCategory, isInternalCD);
        return instance;
    }

    public int getPlaceholderResId() {
        return placeholderResId;
    }

    public Context getContext() {
        return context;
    }

    public int getCacheMaxSize() {
        return cacheMaxSize;
    }

    public int getErrorResId() {
        return errorResId;
    }


    public GlobalConfig placeholderResId(int placeholderResId) {
        this.placeholderResId = placeholderResId;
        return this;
    }

    public GlobalConfig errorResId(int errorResId) {
        this.errorResId = errorResId;
        return this;
    }


    public ILoader getLoader() {
        if (loader == null) {
            loader = new GlideLoader();
        }
        return loader;
    }


}
