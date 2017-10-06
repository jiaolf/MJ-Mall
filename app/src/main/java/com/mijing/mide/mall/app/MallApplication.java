package com.mijing.mide.mall.app;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;

/**
 * Created by Administrator on 2017/9/21.
 */

public class MallApplication extends MultiDexApplication {

    private static MallApplication application;

    public static MallApplication getInstance(){
        return application;
    }

    private Activity mCurrActivity;

    public Activity getCurrentActivity() {
        return this.mCurrActivity;
    }

    public void enter(Activity activity) {
        this.mCurrActivity = activity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
