package com.nj.baijiayun.imageloader.config;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.ViewPropertyTransition;
import com.nj.baijiayun.imageloader.listener.LoadListener;
import com.nj.baijiayun.imageloader.target.BitmapTarget;
import com.nj.baijiayun.imageloader.target.DrawableTarget;
import com.nj.baijiayun.imageloader.target.GifTarget;

import java.io.File;


/**
 * @author chengang
 */

public class SingleConfig {
    private Context context;
    private Fragment fragment;
    private String url;
    /**
     * 缩略图缩放倍数
     */
    private float thumbnail;

    private File file;
    private int resId;

    private boolean isGif;
    private Object target;
    private int oWidth;
    private int oHeight;
    private boolean dontAnimate;
    private int priority;
    private int animationType;
    private int animationId;
    private Animation animation;
    private boolean skipMemoryCache;
    private ViewPropertyTransition.Animator animator;
    private int placeHolderResId;
    private int errorResId;
    private int diskCacheStrategyMode;

    /**
     * 只获取bitmap
     */
    private boolean asBitmap;

    private boolean asGif;

    /**
     * 默认矩形,可选直角矩形,圆形/椭圆
     */
    private int shapeMode;
    /**
     * 圆角矩形时圆角的半径
     */
    private int rectRoundRadius;
    private int scaleMode;
    private boolean openBlur;
    private int blurRadius;

    private LoadListener bitmapListener;

    public SingleConfig(ConfigBuilder builder) {
        this.url = builder.url;
        this.thumbnail = builder.thumbnail;
        this.file = builder.file;
        this.resId = builder.resId;
        this.target = builder.target;
        this.oWidth = builder.oWidth;
        this.oHeight = builder.oHeight;
        this.shapeMode = builder.shapeMode;
        if (shapeMode == ShapeMode.RECT_ROUND) {
            this.rectRoundRadius = builder.rectRoundRadius;
        }
        this.scaleMode = builder.scaleMode;
        this.animationId = builder.animationId;
        this.animationType = builder.animationType;
        this.animator = builder.animator;
        this.animation = builder.animation;
        this.priority = builder.priority;
        this.placeHolderResId = builder.placeHolderResId;
        this.asBitmap = builder.asBitmap;
        this.asGif = builder.asGif;
        this.bitmapListener = builder.loadListener;
        this.isGif = builder.isGif;
        this.errorResId = builder.errorResId;
        this.context = builder.context;
        this.fragment = builder.fragment;
        this.dontAnimate = builder.dontAnimate;
        this.skipMemoryCache = builder.skipMemoryCache;
        this.diskCacheStrategyMode = builder.diskCacheStrategyMode;
        this.blurRadius = builder.blurRadius;
        this.openBlur = builder.openBlur;
    }

    public int getDiskCacheStrategyMode() {
        return diskCacheStrategyMode;
    }

    public boolean isSkipMemoryCache() {
        return skipMemoryCache;
    }

    public boolean isDontAnimate() {
        return dontAnimate;
    }

    public boolean isAsBitmap() {
        return asBitmap;
    }

    public boolean isAsGif() {
        return asGif;
    }

    public boolean isOpenBlur() {
        return openBlur;
    }

    public int getBlurRadius() {
        return blurRadius;
    }

    public Context getContext() {
        if (context == null) {
            context = GlobalConfig.getInstance().getContext();
        }
        return context;
    }

    public Fragment getFragment() {
        return fragment;
    }


    public int getErrorResId() {
        return errorResId;
    }


    public File getFile() {
        return file;
    }


    public int getPlaceHolderResId() {
        return placeHolderResId;
    }

    public int getRectRoundRadius() {
        return rectRoundRadius;
    }

    public int getResId() {
        return resId;
    }

    public int getScaleMode() {
        return scaleMode;
    }

    public int getShapeMode() {
        return shapeMode;
    }

    public Object getTarget() {
        return target;
    }

    public String getUrl() {
        return url;
    }

    public int getoWidth() {
        return oWidth;
    }

    public int getoHeight() {
        return oHeight;
    }

    public int getAnimationType() {
        return animationType;
    }

    public int getAnimationId() {
        return animationId;
    }

    public Animation getAnimation() {
        return animation;
    }

    public ViewPropertyTransition.Animator getAnimator() {
        return animator;
    }

    public int getPriority() {
        return priority;
    }


    public LoadListener getLoadListener() {
        return bitmapListener;
    }

    public float getThumbnail() {
        return thumbnail;
    }

    public void setLoadListener(LoadListener loadListener) {
        this.bitmapListener = loadListener;
    }

    private void show() {
        GlobalConfig.getInstance().getLoader().request(this);
    }

    public boolean isGif() {
        return isGif;
    }


    public static class ConfigBuilder {
        private Context context;
        private Fragment fragment;

        /**
         * 图片源
         * 类型	SCHEME	示例
         * 远程图片	http://, https://	HttpURLConnection 或者参考 使用其他网络加载方案
         * 本地文件	file://	FileInputStream
         * Content provider	content://	ContentResolver
         * asset目录下的资源	asset://	AssetManager
         * res目录下的资源	  load://	Resources.openRawResource
         * Uri中指定图片数据	data:mime/type;base64,	数据类型必须符合 rfc2397规定 (仅支持 UTF-8)
         */
        private String url;
        private float thumbnail;
        private File file;
        private int resId;
        private boolean isGif = false;
        private Object target;
        /**
         * 只获取bitmap
         */
        private boolean asBitmap;
        private boolean asGif;
        private LoadListener loadListener;
        //选择加载分辨率的宽
        private int oWidth;
        //选择加载分辨率的高
        private int oHeight;
        private int placeHolderResId;
        private int errorResId;
        /**
         * 默认矩形,可选直角矩形,圆形/椭圆
         */
        private int shapeMode;
        /**
         * 圆角矩形时圆角的半径
         */
        private int rectRoundRadius;
        private int scaleMode;
        /**
         * 请求优先级
         */
        private int priority;
        /**
         * 动画资源id
         */
        private int animationId;
        /**
         * 动画资源Type
         */
        private int animationType;
        /**
         * 动画资源
         */
        private Animation animation;
        /**
         * 动画资源id
         */
        private ViewPropertyTransition.Animator animator;
        /**
         * 是否开启动画
         */
        private boolean dontAnimate;
        /**
         * 跳过内存缓存
         */
        private boolean skipMemoryCache;
        private int diskCacheStrategyMode;

        private boolean openBlur = false;
        private int blurRadius;


        public ConfigBuilder(Context context) {
            this.context = context;
        }

        public ConfigBuilder(Fragment fragment) {
            this.fragment = fragment;
        }


        public ConfigBuilder openBlur() {
            this.openBlur = true;
            this.blurRadius = 25;
            return this;
        }

        public ConfigBuilder setBlurRadius(int blurRadius) {
            this.blurRadius = blurRadius;
            return this;
        }

        public ConfigBuilder dontAnimate() {
            dontAnimate = true;
            return this;
        }

        public ConfigBuilder setSkipMemoryCache(boolean skipMemoryCache) {
            this.skipMemoryCache = skipMemoryCache;
            return this;
        }

        public ConfigBuilder diskCacheStrategy(int diskCacheStrategyMode) {
            this.diskCacheStrategyMode = diskCacheStrategyMode;
            return this;
        }

        /**
         * 缩略图
         *
         * @param thumbnail t
         * @return ConfigBuilder
         */
        public ConfigBuilder thumbnail(float thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        /**
         * error图
         *
         * @param errorResId e
         * @return ConfigBuilder
         */
        public ConfigBuilder error(int errorResId) {
            this.errorResId = errorResId;
            return this;
        }

        /**
         * 设置网络路径
         *
         * @param url 路径
         * @return ConfigBuilder
         */
        public ConfigBuilder load(String url) {
            this.url = url;
            if (url != null && url.endsWith("gif")) {
                isGif = true;
            }
            return this;
        }


        /**
         * 加载SD卡资源
         *
         * @param file f
         * @return ConfigBuilder
         */
        public ConfigBuilder load(File file) {
            this.file = file;

            return this;
        }

        /**
         * 加载drawable资源
         *
         * @param resId r
         * @return ConfigBuilder
         */
        public ConfigBuilder load(int resId) {
            this.resId = resId;
            return this;
        }


        public void into(ImageView target) {
            this.target = target;
            new SingleConfig(this).show();
        }

        public void into(GifTarget gifTarget) {

            this.target = gifTarget;
            new SingleConfig(this).show();
        }

        public void into(BitmapTarget bitmapTarget) {
            this.target = bitmapTarget;
            Log.e("target--> BitmapTarget", (bitmapTarget instanceof Target) + "");
            new SingleConfig(this).show();
        }

        public void into(DrawableTarget drawableTarget) {
            this.target = drawableTarget;

            new SingleConfig(this).show();
        }


        public ConfigBuilder asBitmap() {
            this.asBitmap = true;
            return this;
        }

        public ConfigBuilder asGif() {
            this.asGif = true;
            return this;
        }

        public ConfigBuilder listener(LoadListener loadListener) {
            this.loadListener = loadListener;
            return this;
        }

        /**
         * 加载图片的分辨率
         *
         * @param oWidth  w
         * @param oHeight h
         * @return ConfigBuilder
         */
        public ConfigBuilder override(int oWidth, int oHeight) {
            this.oWidth = dip2px(oWidth);
            this.oHeight = dip2px(oHeight);
            return this;
        }

        /**
         * 占位图
         *
         * @param placeHolderResId p
         * @return ConfigBuilder
         */
        public ConfigBuilder placeHolder(int placeHolderResId) {
            this.placeHolderResId = placeHolderResId;
            return this;
        }


        /**
         * 圆角
         *
         * @return ConfigBuilder
         */
        public ConfigBuilder asCircle() {
            this.shapeMode = ShapeMode.OVAL;
            return this;
        }

        /**
         * 形状为圆角矩形时的圆角半径
         *
         * @param rectRoundRadiusDp r
         * @return ConfigBuilder
         */
        public ConfigBuilder rectRoundCorner(int rectRoundRadiusDp) {
            this.rectRoundRadius = dip2px(rectRoundRadiusDp);
            this.shapeMode = ShapeMode.RECT_ROUND;
            return this;
        }


        /**
         * 正方形
         *
         * @return ConfigBuilder
         */
        public ConfigBuilder asSquare() {
            this.shapeMode = ShapeMode.SQUARE;
            return this;
        }


        /**
         * 拉伸/裁剪模式
         *
         * @param scaleMode 取值ScaleMode
         * @return ConfigBuilder
         */
        public ConfigBuilder scale(int scaleMode) {
            this.scaleMode = scaleMode;
            return this;
        }


        public ConfigBuilder animate(int animationId) {
            this.animationType = AnimationMode.ANIMATIONID;
            this.animationId = animationId;
            return this;
        }

        public ConfigBuilder animate(ViewPropertyTransition.Animator animator) {
            this.animationType = AnimationMode.ANIMATOR;
            this.animator = animator;
            return this;
        }

        public ConfigBuilder animate(Animation animation) {
            this.animationType = AnimationMode.ANIMATION;
            this.animation = animation;
            return this;
        }

        public ConfigBuilder priority(int priority) {
            this.priority = priority;
            return this;
        }


    }


    public static int dip2px(float dipValue) {
        final float scale = GlobalConfig.getInstance().getContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}