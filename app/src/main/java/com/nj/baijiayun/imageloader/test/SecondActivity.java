package com.nj.baijiayun.imageloader.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.nj.baijiayun.imageloader.loader.ImageLoader;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ImageLoader.with(this)
                .load("https://baijiayun-wangxiao.oss-cn-beijing.aliyuncs.com/uploads/image/20190vZvchLdoF1572403177.gif")

                .placeHolder(R.mipmap.common_course_dufault)
                .into((ImageView) findViewById(R.id.img));

    }

    @Override
    protected void onResume() {
        super.onResume();
//        Glide.with(this).pauseRequests();
//        ImageLoader.getActualLoader().pauseRequests(this);
    }
    public void open(View view)
    {
//     Glide.with().onStart();
//        ImageLoader.getActualLoader().resumeRequests(this);
    }
}
