package com.xinnian.http;

import android.util.Log;

public final class Logger {

    private static boolean isDebug = BuildConfig.DEBUG;

    private static final String TAG = "xinnian";

    private Logger() {
    }

    /**
     * 在非debug模式下，显示该信息
     *
     * @param msg 日志信息，如果为null，则不打印日志信息
     */
    public static void i(String msg) {
        if (msg != null && isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void i(String msg, Object... args) {
        if (msg != null && isDebug) {
            String message = createMessage(msg, args);
            Log.i(TAG, message);
        }
    }

    /**
     * 无论是否是debug模式，都打印日志信息
     *
     * @param msg 日志信息，如果为null，则不打印日志信息
     */
    public static void d(String msg) {
        if (msg != null) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String msg, Object... args) {
        if (msg != null) {
            String message = createMessage(msg, args);
            Log.d(TAG, message);
        }
    }

    /**
     * 无论是否是debug模式，都打印错误日志信息
     *
     * @param msg 日志信息，如果为null，则不打印日志信息
     */
    public static void e(String msg) {
        if (msg != null) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String msg, Object... args) {
        if (msg != null) {
            String message = createMessage(msg, args);
            Log.e(TAG, message);
        }
    }

    private static String createMessage(String message, Object... args) {
        return args.length == 0 ? message : String.format(message, args);
    }
}
