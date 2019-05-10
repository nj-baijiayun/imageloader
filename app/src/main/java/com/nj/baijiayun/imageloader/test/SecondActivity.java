package com.nj.baijiayun.imageloader.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.nj.baijiayun.imageloader.listener.LoadListener;
import com.nj.baijiayun.imageloader.loader.ImageLoader;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ImageLoader.with(this)
                .load(MainActivity.url)
                .openBlur()
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

    @Override
    protected void onResume() {
        super.onResume();
        Glide.with(this).pauseRequests();
//        ImageLoader.getActualLoader().pauseRequests(this);
    }
    public void open(View view)
    {
//     Glide.with().onStart();
//        ImageLoader.getActualLoader().resumeRequests(this);
    }
}
