package com.mijing.mide.mall.utils;

import android.net.Uri;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jiaolongfei on 2016/6/24.
 *
 * 拼接BaseUrl和请求参数
 */
public class RequestUtil {

    public static String buildUrl(String url, Object... keyValue) {
        if (keyValue.length > 0 && keyValue.length % 2 != 0) {
            throw new UnsupportedOperationException("keyValue必须是偶数");
        }

        Uri.Builder builder = Uri.parse(url).buildUpon();

        for (int i = 0; i < keyValue.length; i++) {
            String key = String.valueOf(keyValue[i]);
            Object _value = keyValue[++i];

            if (_value == null) {
                continue;
            }

            String value = String.valueOf(_value);

            builder.appendQueryParameter(key, value);
        }

        return builder.toString();
    }

    public static String buildUrl(String url, Map<String, Object> params) {
        Uri.Builder builder = Uri.parse(url).buildUpon();

        Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            if (entry.getValue() == null) continue;

            builder.appendQueryParameter(entry.getKey(), String.valueOf(entry.getValue()));
        }

        return builder.toString();
    }

    public static String buildUrl(String url, Object params) {

        Field[] fields = params.getClass().getDeclaredFields();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);

            try {
                String key = fields[i].getName();
                String value = String.valueOf(fields[i].get(params));
                map.put(key, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Uri.Builder builder = Uri.parse(url).buildUpon();

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            if (entry.getValue() == null) continue;

            builder.appendQueryParameter(entry.getKey(), String.valueOf(entry.getValue()));
        }

        return builder.toString();
    }

}
