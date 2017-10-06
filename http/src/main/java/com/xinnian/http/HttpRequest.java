package com.xinnian.http;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/26.
 */

public class HttpRequest {

    private static final Gson gson = new Gson();

    private static final Handler handler = new Handler();

    private Builder builder;

    private HttpRequest(Builder builder) {
        this.builder = builder;
    }

    public void get(RestCallback callback) {
        Request.Builder requestBuilder = new Request.Builder()
                .url(builder.url)
                .tag(builder.requestFlag);
        requestBuilder.get();

        request(requestBuilder.build(), callback);
    }

    public void post(RestCallback callback) {
        if (builder.paramsMap == null) {
            throw new IllegalArgumentException("POST请求时，参数paramsMap不能为null");
        }

        Request.Builder requestBuilder = new Request.Builder()
                .url(builder.url)
                .tag(builder.requestFlag);

        StringBuilder sb = new StringBuilder();
        FormBody.Builder formBuilder = new FormBody.Builder();
        Set<Map.Entry<String, Object>> set = builder.paramsMap.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof List) {
                for (int i = 0; i < ((List) value).size(); i++) {
                    formBuilder.add(key, String.valueOf(((List) value).get(i)));
                }
            } else {
                formBuilder.add(key, String.valueOf(value));
            }
            sb.append(key + " : " + value + "\n");
            Logger.d("params:\n" + sb.toString());
        }

        Request req = requestBuilder.post(formBuilder.build()).build();
        request(req, callback);
    }

    private void request(final Request request, final RestCallback callback) {
        callback.onStart();
        HttpProvider.getInstance().enqueue(request, new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailure(e == null ? "network error" : e.getLocalizedMessage());
                        callback.onFinish();
                    }
                });
            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                try {
                    String json = response.body().string();
                    if (response.isSuccessful()) {
                        if (!TextUtils.isEmpty(json)) {
                            try {
                                builder.parseStringToJson(json, callback);
                            } catch (Exception e) {
                                //e.printStackTrace();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        callback.onFailure("数据解析错误");
                                    }
                                });
                            }
                        } else {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Logger.e("空数据, url :" + request.url().toString());
                                    callback.onFailure("返回数据为空");
                                }
                            });
                        }
                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onFailure(response.message() == null ? "网络错误" : response.message());
                            }
                        });
                    }
                } catch (Exception e) {
                    Logger.e(e.getMessage());
                } finally {
                    callback.onFinish(builder.tags);
                }

            }
        });
    }

    public static class Builder<T> {
        String url;
        String requestFlag;
        Class<T> clazz;
        Object[] tags;
        Map<String, Object> paramsMap;
        Context context;

        /**
         * 设置请求地址
         *
         * @param url 请求地址
         * @return builder
         */
        public Builder url(String url) {
            url = url.replaceAll(" ", "%20");
            this.url = url;
            return this;
        }

        /**
         * 设置服务器返回数据对应的实体类
         *
         * @param clazz 字节码
         * @return builder
         */
        public Builder clazz(Class<T> clazz) {
            this.clazz = clazz;
            return this;
        }

        /**
         * post请求参数
         *
         * @param paramsMap 请求参数
         * @return builder
         */
        public Builder params(Map<String, Object> paramsMap) {
            this.paramsMap = paramsMap;
            return this;
        }

        /**
         * 额外的回调参数
         *
         * @param tags tags
         * @return builder
         */
        public Builder tags(Object... tags) {
            this.tags = tags;
            return this;
        }

        /**
         * 标识该请求
         *
         * @param requestFlag 唯一的标识
         * @return builder
         */
        public Builder flag(String requestFlag) {
            this.requestFlag = requestFlag;
            return this;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public HttpRequest build() {
            if (clazz == null) {
                throw new IllegalArgumentException("参数clazz不能为null");
            } else if (url == null) {
                throw new IllegalArgumentException("参数url不能为null");
            }
            return new HttpRequest(this);
        }

        void parseStringToJson(String json, final RestCallback callback) throws Exception {
            final T t = gson.fromJson(json, clazz);

            handler.post(new Runnable() {
                @Override
                public void run() {
                    callback.onSuccess(t);
                }
            });
        }
    }
}
