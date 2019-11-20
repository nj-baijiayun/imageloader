package com.nj.baijiayun.imageloader.test;

import android.app.Application;

import com.nj.baijiayun.imageloader.loader.ImageLoader;

/**
 * @author chengang
 * @date 2019/5/10
 * @email chenganghonor@gmail.com
 * @QQ 1410488687
 * @package_name com.nj.baijiayun.imageloader.test
 * @describe
 */
public class TestApp extends Application {
    public static Application instace;
    @Override
    public void onCreate() {
        super.onCreate();
//        ImageLoader.init(this)
//                .errorResId(R.mipmap.ic_launcher)
//                .placeholderResId(R.mipmap.ic_launcher);
        instace=this;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
       ImageLoader.getLoader().trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImageLoader.getLoader().onLowMemory();
    }
}
