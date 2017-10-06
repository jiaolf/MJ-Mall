package com.xinnian.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/26.
 */

public class HttpProvider {
    private static HttpProvider instance;
    private OkHttpClient okHttpClient;

    public synchronized static HttpProvider getInstance() {
        if (instance == null) {
            instance = new HttpProvider();
        }
        return instance;
    }

    private HttpProvider() {
        //增加头部信息
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build();
                return chain.proceed(build);
            }
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        okHttpClient = builder.readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(headerInterceptor).build();
    }

    /**
     * 将请求加入请求队列
     *
     * @param req      请求信息
     * @param callback 响应回调
     */
    public void enqueue(Request req, Callback callback) {
        //this.context = context;
        okHttpClient.newCall(req).enqueue(callback);
    }

    /**
     * 取消请求
     *
     * @param requestFlag 请求标识
     */
    public void cancelRequest(Object requestFlag) {

    }
}
