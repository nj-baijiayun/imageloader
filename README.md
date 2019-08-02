ImageLoader  是在Glide上封装一层,尽可能减少Glide版本改动的变化，代码调用影响

## 快速集成
在工程目标的`build.gradle`文件添加仓库地址
```
allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url = 'http://172.20.2.114:8081/repository/maven-releases/'
        }
    }
}

```
在dependencies下添加依赖
```
dependencies {
   
    implementation 'com.nj.baijiayun:imageloader:1.0.0'
}
```
替换为最新版本为 [版本说明](./changelog.md)



## 初始化

```
//在程序入口初始化
ImageLoader.init(this);

///在程序入口初始化带有默认配置
 ImageLoader.init(this)
                .errorResId(R.mipmap.ic_launcher)
                .placeholderResId(R.mipmap.ic_launcher);


```


如何使用
=============
封装后的基本使用样式：

```
ImageLoader.with(this)
	.load("xxxxx")
	.placeHolder(R.mipmap.ic_launcher)
	.rectRoundCorner(30)
	.openBlur()
	.into(iv_round);
```


## ImageLoader类
ImageLoader方法：
> - ImageLoader.init(Context context) //初始化
> - ImageLoader.getLoader(); //获取当前的loader
> - ImageLoader.with(Context context) //加载图片
> - ImageLoader.getLoader().resumeRequests(Fragment fragment) //恢复请求
> - ImageLoader.getLoader().resumeRequests(Context context) //恢复请求
> - ImageLoader.getLoader().pauseRequests(Fragment fragment) //恢复请求
> - ImageLoader.getLoader().pauseRequests(Context context) //恢复请求
> - ImageLoader.getLoader().onLowMemory() 低内存调用
> - ImageLoader.getLoader().trimMemory(int level) 
> - ImageLoader.getLoader().clearDiskCache()
> - ImageLoader.getLoader().clearMemory() // 清除内存缓存(必须在UI线程中调用)

在application 里面调用 自动管理内存
```
  
  @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        ImageLoader.getLoader().onTrimMemory(level);
       
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImageLoader.getLoader().onLowMemory();
    }

```




##图片的各种设置信息--SingleConfig
我们所设置图片的所有属性都写在这个类里面。下面我们详细的看一下：



> - load(String url) //支持filepath、图片链接、contenProvider、资源id四种
> - thumbnail(float thumbnail)//缩略图
> - rectRoundCorner(int rectRoundRadius) //形状为圆角矩形时的圆角半径
> - asSquare() //形状为正方形
> - asCircle()//加载圆形图片
> - diskCacheStrategy(int diskCacheStrategy) //DiskCacheStrategyMode.NONE :不缓存图片 ／DiskCacheStrategyMode.SOURCE :缓存图片源文件／DiskCacheStrategyMode.RESULT:缓存修改过的图片／DiskCacheStrategyMode.ALL:缓存所有的图片，默认
> - placeHolder(int placeHolderResId) //占位图
> - override(int oWidth, int oHeight) //加载图片时设置分辨率 a
> - scale(int scaleMode)
//ScaleMode.CENTER_CROP
//ScaleMode.FIT_CENTER
//ScaleMode.CENTER_INSIDE
> - animate(int animationId ) 引入动画
 > - animate( Animation animation) 引入动画
 > - animate(ViewPropertyAnimation.Animator animato) 引入动画
> - dontAnimate() 关闭动画 默认开启淡入淡出动画
> - into(View targetView) //展示到imageview
> - openBlur() ／/高斯模糊 默认25
> - setBlurRadius() 设置模糊数值
> - skipMemoryCache() 不进行内存缓存
> - asBitmap() 加载成bitmap 这个需要into(new BitmapTarget())
> - asGif()  加载Gif 这个需要into(new GifTarget())
> - listener(LoadListener loadListener)

```
listener(new LoadListener() {
                    @Override
                    public boolean onSuccess(Object resource) {
                    //成功回调 默认返回false 设置true表示之后Glide不再处理后续步骤， 需要自己把资源设置到目标
                        Log.e("===","onSuccess"+resource);
                        return false;
                    }

                    @Override
                    public boolean onFail(Exception e) {
                        //失败回调
                        return false;
                    }

                    @Override
                    public void preLoad() {
                        //开始加载
                    }
                })
```


备注：设置数值内部已经换算成px



封装参考：https://github.com/libin7278/ImageLoader


