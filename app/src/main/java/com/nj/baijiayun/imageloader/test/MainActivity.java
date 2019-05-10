package com.nj.baijiayun.imageloader.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nj.baijiayun.imageloader.listener.LoadListener;
import com.nj.baijiayun.imageloader.loader.ImageLoader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String url = "https://upload.jianshu.io/users/upload_avatars/4951294/34667d56-03fa-4d00-bd68-81433d73f7de.png?imageMogr2/auto-orient/strip|imageView2/1/w/96/h/96";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化一个默认loader
//        ImageLoader.initDefaultLoader().buildConfig();
//
//        Glide.with(this)
//                .load(R.mipmap.ic_launcher)
//                .load(url)
//                .placeholder(R.mipmap.common_course_dufault)
//                .transform(new GlideCircleTransformation())
//
//                .into((ImageView) findViewById(R.id.img2));
//
//
//
        ImageLoader.init(this);
        ImageLoader.with(this)
                .load(url)
                .asCircle()
                .placeHolder(R.mipmap.common_course_dufault)
                .listener(new LoadListener() {
                    @Override
                    public boolean onSuccess(Object resource) {

                        Log.e("===","onSuccess"+resource);
                        return false;
                    }

                    @Override
                    public boolean onFail(Exception e) {
                        Log.e("===","onFail"+e.getMessage());

                        return false;
                    }

                    @Override
                    public void preLoad() {

                    }
                }).into(findViewById(R.id.img));

    }
}
