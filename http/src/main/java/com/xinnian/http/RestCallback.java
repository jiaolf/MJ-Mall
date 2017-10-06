package com.xinnian.http;

/**
 * http请求回调
 *
 * @param <T> 服务器返回的响应数据
 */
public interface RestCallback<T> {
    /**
     * 开始请求
     * 显示进度条等，在UI线程执行
     */
    void onStart();

    /**
     * 请求成功，包含业务错误，比如：success:false, msg:'参数有误'
     * 成功后的处理，在UI线程执行
     * @param t 服务器返回的响应数据
     */
    void onSuccess(T t);

    /**
     * 请求失败,只判断网络、连接等失败情况
     * 失败后的处理，在UI线程执行
     * UI线程执行
     */
    //void onFailure(Exception e);
    void onFailure(String errMsg);

    /**
     * 完成请求。在success或failure之后执行，可以执行关闭进度条等操作，必须会执行到
     * UI线程执行
     * @param tags 额外的回调参数
     */
    void onFinish(Object... tags);
}