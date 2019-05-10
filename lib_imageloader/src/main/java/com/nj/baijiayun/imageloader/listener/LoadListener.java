package com.nj.baijiayun.imageloader.listener;

/**
 * @author chengang
 * @date 2019/5/9
 * @email chenganghonor@gmail.com
 * @QQ 1410488687
 * @package_name imageloader.libin.com.images.listener
 * @describe
 */

public interface LoadListener<T> {
    /**
     * 成功
     *
     * @param resource 返回的资源
     * @return true l拦截 是否拦截之后的步骤
     */
    boolean onSuccess(T resource);

    /**
     * 加载失败
     *
     * @param e e
     * @return return true l拦截 是否拦截之后的步骤
     */
    boolean onFail(Exception e);

    /**
     * 开始加载
     */
    void preLoad();
}