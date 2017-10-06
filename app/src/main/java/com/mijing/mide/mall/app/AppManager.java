package com.mijing.mide.mall.app;

import android.app.Activity;

import com.mijing.mide.mall.base.BaseActivity;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 */
public class AppManager {

    private static Stack<BaseActivity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void pushActivity(BaseActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 移除Activity
     */
    public void popActivity(BaseActivity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity = null;
        }
    }

    public void popToActivity(Class<? extends BaseActivity> clazz) {
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.peek();
            if (activity.getClass() == clazz) {
                return;
            }

            activityStack.pop();
            activity.finish();
        }
    }


    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        if (activity != null) {
            activity.finish();
            activity = null;
        }
    }

    public BaseActivity getActivity(Class<? extends BaseActivity> clazz) {
        for (int i = 0; i < activityStack.size(); i++) {
            if (activityStack.get(i).getClass() == clazz) {
                return activityStack.get(i);
            }
        }
        return null;
    }


    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

}