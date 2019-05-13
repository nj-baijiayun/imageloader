package com.nj.baijiayun.imageloader.loader;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.bumptech.glide.MemoryCategory;
import com.nj.baijiayun.imageloader.config.SingleConfig;


public interface ILoader {

    void init(Context context,String disdiskCacheName, int cacheSizeInM, MemoryCategory memoryCategory, boolean isInternalCD);

    void request(SingleConfig config);


    void resumeRequests(Context context);

    void pauseRequests(Context context);

    void resumeRequests(Fragment fragment);

    void pauseRequests(Fragment fragment);

    void clearDiskCache();

    void clearMemory();

    void trimMemory(int level);

    void onLowMemory();


}
