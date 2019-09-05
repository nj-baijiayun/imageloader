package com.nj.baijiayun.imageloader.loader;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.request.transition.ViewAnimationFactory;
import com.nj.baijiayun.imageloader.config.AnimationMode;
import com.nj.baijiayun.imageloader.config.DiskCacheStrategyMode;
import com.nj.baijiayun.imageloader.config.GlobalConfig;
import com.nj.baijiayun.imageloader.config.PriorityMode;
import com.nj.baijiayun.imageloader.config.ScaleMode;
import com.nj.baijiayun.imageloader.config.ShapeMode;
import com.nj.baijiayun.imageloader.config.SingleConfig;
import com.nj.baijiayun.imageloader.transform.BlurBitmapTranformation;
import com.nj.baijiayun.imageloader.transform.CropSquareTransformation;
import com.nj.baijiayun.imageloader.transform.RoundedCornersTransformation;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chengang
 * @date 2017/4/10
 * 参考:
 * https://mrfu.me/2016/02/28/Glide_Sries_Roundup/
 */

public class GlideLoader implements ILoader {

    /**
     * @param context        上下文
     * @param cacheSizeInM   Glide默认磁盘缓存最大容量250MB
     * @param memoryCategory 调整内存缓存的大小 LOW(0.5f) ／ NORMAL(1f) ／ HIGH(1.5f);
     * @param isInternalCD   true 磁盘缓存到应用的内部目录 / false 磁盘缓存到外部存
     */
    @Override
    public void init(Context context, String diskCacheName, int cacheSizeInM, MemoryCategory memoryCategory, boolean isInternalCD) {
        //如果在应用当中想要调整内存缓存的大小，开发者可以通过如下方式：
        Glide.get(context).setMemoryCategory(memoryCategory);
        GlideBuilder builder = new GlideBuilder();
        if (isInternalCD) {
            builder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskCacheName, cacheSizeInM * 1024 * 1024));
        } else {
            builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context, diskCacheName, cacheSizeInM * 1024 * 1024));
        }


    }

    @Override
    public void request(final SingleConfig config) {
        //得到初始的 RequestOptions
        RequestOptions requestOptions = getRequestOptions(config);
        //得到一个正确类型的 RequestBuilder(bitmap or 其他加载)
        RequestBuilder requestBuilder = getRequestBuilder(config);
        //应用RequestOptions
        requestBuilder.apply(requestOptions);
        //设置缩略图
        if (config.getThumbnail() != 0) {
            //设置缩略比例
            requestBuilder.thumbnail(config.getThumbnail());
        }

        //设置图片加载动画
        setAnimator(config, requestBuilder);
        //如果是获取bitmap,则回调
        //如果是加载图片，（无论是否为Gif）
        RequestListener requestListener = null;
        if (config.getLoadListener() != null) {
            requestListener = new RequestListener() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                    if (config.getLoadListener() == null) {
                        return false;

                    }
                    return config.getLoadListener().onFail(e);
                }

                @Override
                public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                    if (config.getLoadListener() == null) {
                        return false;

                    }
                    return config.getLoadListener().onSuccess(resource);
                }
            };

            config.getLoadListener().preLoad();

        }


        if (config.getTarget() instanceof ImageView) {

            requestBuilder.listener(requestListener).into((ImageView) config.getTarget());

        } else if (config.getTarget() instanceof Target) {

            requestBuilder.listener(requestListener).into((Target) config.getTarget());

        }


    }


    private RequestOptions getRequestOptions(SingleConfig config) {
        RequestOptions options = new RequestOptions();
        int placeHolder = config.getPlaceHolderResId();
        if (placeHolder <= 0) {
            placeHolder = GlobalConfig.getInstance().getPlaceholderResId();
        }
        options = options.placeholder(placeHolder);
        int errorResId = config.getErrorResId();
        if (errorResId <= 0) {
            errorResId = GlobalConfig.getInstance().getErrorResId();
        }
        //错误设置 和 内存缓存跳过
        options.error(errorResId).skipMemoryCache(config.isSkipMemoryCache());

        int scaleMode = config.getScaleMode();
        switch (scaleMode) {
            case ScaleMode.CENTER_CROP:
                options.centerCrop();
                break;
            case ScaleMode.FIT_CENTER:
                options.fitCenter();
                break;
            case ScaleMode.CENTER_INSIDE:
                options.centerInside();
                break;
            default:
                options.centerCrop();
                break;
        }

        //设置图片加载的分辨 sp
        if (config.getoWidth() != 0 && config.getoHeight() != 0) {
            options.override(config.getoWidth(), config.getoHeight());
        }

        //设置图片加载优先级
        setPriority(config, options);
        //设置本地缓存
        setDiskCacheStrategy(config, options);
        //设置RequestOptions 关于 多重变换
        setShapeModeAndBlur(config, options);
        return options;
    }

    /**
     * 磁盘缓存配置
     *
     * @param config d
     */
    private void setDiskCacheStrategy(SingleConfig config, RequestOptions options) {
        switch (config.getDiskCacheStrategyMode()) {
            case DiskCacheStrategyMode.ALL:
                options.diskCacheStrategy(DiskCacheStrategy.ALL);
                break;
            case DiskCacheStrategyMode.AUTOMATIC:
                options.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                break;
            case DiskCacheStrategyMode.DATA:
                options.diskCacheStrategy(DiskCacheStrategy.DATA);
                break;
            case DiskCacheStrategyMode.NONE:
                options.diskCacheStrategy(DiskCacheStrategy.NONE);
                break;
            case DiskCacheStrategyMode.RESOURCE:
                options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                break;
            default:
                options.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                break;
        }
    }


    /**
     * 设置加载优先级
     *
     * @param config  c
     * @param options p
     */
    private void setPriority(SingleConfig config, RequestOptions options) {
        switch (config.getPriority()) {
            case PriorityMode.PRIORITY_LOW:
                options.priority(Priority.LOW);
                break;
            case PriorityMode.PRIORITY_NORMAL:
                options.priority(Priority.NORMAL);
                break;
            case PriorityMode.PRIORITY_HIGH:
                options.priority(Priority.HIGH);
                break;
            case PriorityMode.PRIORITY_IMMEDIATE:
                options.priority(Priority.IMMEDIATE);
                break;
            default:
                options.priority(Priority.IMMEDIATE);
                break;
        }
    }

    /**
     * 设置加载进入动画
     *
     * @param config  c
     * @param request r
     */
    private void setAnimator(SingleConfig config, RequestBuilder request) {

        DrawableCrossFadeFactory drawableCrossFadeFactory = new DrawableCrossFadeFactory.Builder(300).setCrossFadeEnabled(true).build();

        if (config.getAnimationType() == AnimationMode.ANIMATIONID) {
            GenericTransitionOptions genericTransitionOptions = GenericTransitionOptions.with(config.getAnimationId());
            request.transition(genericTransitionOptions);
        } else if (config.getAnimationType() == AnimationMode.ANIMATOR) {
            GenericTransitionOptions genericTransitionOptions = GenericTransitionOptions.with(config.getAnimator());
            request.transition(genericTransitionOptions);
        } else if (config.getAnimationType() == AnimationMode.ANIMATION) {
            GenericTransitionOptions genericTransitionOptions = GenericTransitionOptions.with(new ViewAnimationFactory(config.getAnimation()));
            request.transition(genericTransitionOptions);
        } else {//设置默认的交叉淡入动画
            if (!config.isDontAnimate()) {

//                request.transition(DrawableTransitionOptions.withCrossFade());
                request.transition(DrawableTransitionOptions.with(drawableCrossFadeFactory));
            } else {
                request.dontAnimate();
            }
        }

    }


    /**
     * 构建RequestBuilder
     *
     * @param config config
     * @return RequestBuilder
     */
    private RequestBuilder getRequestBuilder(SingleConfig config) {
        RequestManager requestManager = null;
        if (config.getFragment() != null) {
            requestManager = Glide.with(config.getFragment());
        } else {
            requestManager = Glide.with(config.getContext());
        }
        RequestBuilder request;
        if (config.isAsBitmap()) {
            request = requestManager.asBitmap();
        } else if (config.isAsGif() || config.isGif()) {
            request = requestManager.asGif();
        } else {
            request = requestManager.asDrawable();
        }
        if (config.getResId() > 0) {
            return request.load(config.getResId());
        } else if (config.getFile() != null) {
            return request.load(config.getFile());
        } else {
            return request.load(config.getUrl());
        }

    }

    /**
     * 设置图片滤镜和形状
     *
     * @param config  c
     * @param options o
     */
    private void setShapeModeAndBlur(SingleConfig config, RequestOptions options) {

        int count = 0;

        Transformation[] transformation = new Transformation[statisticsCount(config)];

        if (config.isOpenBlur()) {
            transformation[count] = new BlurBitmapTranformation(config.getBlurRadius());
            count++;
        }
        switch (config.getShapeMode()) {
            default:
                break;
            case ShapeMode.RECT:
                break;
            case ShapeMode.RECT_ROUND:
                transformation[count] = new RoundedCornersTransformation
                        (config.getRectRoundRadius(), 0, config.getCornerType());
                break;
            case ShapeMode.OVAL:
                transformation[count] = new CircleCrop();
                Log.e("TAG", "circleCrop");
                break;
            case ShapeMode.SQUARE:
                transformation[count] = new CropSquareTransformation();
                break;
        }
        Log.e("TAG", "circleCrop" + transformation);

        if (transformation != null) {
            options.transform(transformation);
        }
    }

    private int statisticsCount(SingleConfig config) {
        int count = 0;

        if (config.getShapeMode() == ShapeMode.OVAL || config.getShapeMode() == ShapeMode.RECT_ROUND || config.getShapeMode() == ShapeMode.SQUARE) {
            count++;
        }

        if (config.isOpenBlur()) {
            count++;
        }
        return count;
    }


    @Override
    public void resumeRequests(Context context) {
        Glide.with(context).resumeRequestsRecursive();

    }

    @Override
    public void pauseRequests(Context context) {
        Glide.with(context).pauseRequestsRecursive();


    }

    @Override
    public void resumeRequests(Fragment fragment) {
        Glide.with(fragment).resumeRequestsRecursive();

    }

    @Override
    public void pauseRequests(Fragment fragment) {
        Glide.with(fragment).pauseRequestsRecursive();

    }


    @Override
    public void clearDiskCache() {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Glide.get(GlobalConfig.getInstance().getContext()).clearDiskCache();
            }
        });

    }

    final ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());


    @Override
    public void clearMemory() {
        Glide.get(GlobalConfig.getInstance().getContext()).clearMemory();
    }

    @Override
    public void trimMemory(int level) {
        Glide.get(GlobalConfig.getInstance().getContext()).onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        Glide.get(GlobalConfig.getInstance().getContext()).onLowMemory();
    }


}
