package com.nj.baijiayun.imageloader.loader;

import android.content.Context;

import com.bumptech.glide.MemoryCategory;
import com.nj.baijiayun.imageloader.config.SingleConfig;


public interface ILoader {

    void init(Context context, int cacheSizeInM, MemoryCategory memoryCategory, boolean isInternalCD);

    void request(SingleConfig config);

    void pause();

    void resume();

    void clearDiskCache();

    void clearMemory();

    void trimMemory(int level);


}
