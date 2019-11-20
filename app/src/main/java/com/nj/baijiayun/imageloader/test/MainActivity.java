package com.nj.baijiayun.imageloader.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
//    public static String url = "http://neixun2.admin.zhiyun88.com/uploads/images/20190916/00ec409a2b832ac01c2a3cbe26031241.png";
    public static String url2 = "http://haodiquan.oss-cn-shanghai.aliyuncs.com/1.572577119933118E12.jpg";
    private static String cover="";

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



        System.out.println("Cache0" + this.getExternalCacheDir());
        System.out.println("Cache1" + this.getCacheDir());


//        ImageLoader
//                .with(this)
//                .load(url2)
//                .rectRoundCorner(1)
//                .openBlur()
//                .asSquare()
//                .placeHolder(R.mipmap.common_course_dufault)
//                .listener(new LoadListener() {
//                    @Override
//                    public boolean onSuccess(Object resource) {
//                        Log.e("===", "onSuccess" + resource);
//                        if (resource instanceof BitmapDrawable) {
//                            ImageView viewById = findViewById(R.id.img2);
//                            viewById.setImageDrawable((Drawable) resource);
//                        }
//
//                        return true;
//                    }
//
//                    @Override
//                    public boolean onFail(Exception e) {
//                        Log.e("===", "onFail" + e.getMessage());
//
//                        return false;
//                    }
//
//                    @Override
//                    public void preLoad() {
//                        Log.e("===", "preLoad");
//
//                    }
//                }).into((ImageView) findViewById(R.id.img3));

//        ImageLoader.with(this)
//                .load(url)
//                .rectRoundCorner(1)
//                .openBlur()
//                .asCircle()
//                .placeHolder(R.mipmap.common_course_dufault)
//                .listener(new LoadListener() {
//                    @Override
//                    public boolean onSuccess(Object resource) {
//
//                        Log.e("===>>>", "onSuccess" + resource);
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onFail(Exception e) {
//                        Log.e("===", "onFail" + e.getMessage());
//
//                        return false;
//                    }
//
//                    @Override
//                    public void preLoad() {
//                        Log.e("===", "preLoad" );
//
//                    }
//                }).into(findViewById(R.id.img));


        String a=null;
//        Glide.with(this).setDefaultRequestOptions(new RequestOptions().centerCrop().fallback(R.drawable.public_ic_image_error)).load("").dontAnimate().transform(new CenterCrop(),new RoundedCornersTransformation(20,0, RoundedCornersTransformation.CornerType.TOP)).error().into((ImageView) findViewById(R.id.cover));
//       ImageLoader.with(this).rectRoundCorner(30).setCornerType(RoundedCornersTransformation.CornerType.TOP).load(url).into((ImageView) findViewById(R.id.img));
        Glide.with(this).load(url2.replace("http://", "https://")).disallowHardwareConfig().error(R.drawable.public_ic_image_error).into((ImageView) findViewById(R.id.cover));
//                into(new BitmapTarget() {
//            @Override
//            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                Log.e("TAG", "onResourceReady" + resource);
//
//                ImageView viewById = findViewById(R.id.img);
//                viewById.setImageBitmap(resource);
//            }
//
//            @Override
//            public void onLoadCleared(@Nullable Drawable placeholder) {
//
//            }
//
//            @Override
//            public void onLoadStarted(@Nullable Drawable placeholder) {
//                super.onLoadStarted(placeholder);
//                Log.e("TAG", "onResourceReady start" + placeholder);
//
//
//            }
//
//            @Override
//            public void onLoadFailed(@Nullable Drawable errorDrawable) {
//                super.onLoadFailed(errorDrawable);
//                Log.e("TAG", "onResourceReady fail" + errorDrawable);
//
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void open(View view) {
        Glide.with(this).resumeRequestsRecursive();

//        ImageLoader.getActualLoader().resumeRequests(this);
    }

    public void next(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}
