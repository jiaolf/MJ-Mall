package com.mijing.mide.mall.base;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mijing.mide.mall.app.AppManager;
import com.mijing.mide.mall.app.UI;

/**
 * Created by Administrator on 2017/9/21.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UI.enter(this);
        AppManager.getInstance().pushActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UI.enter(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        AppManager.getInstance().popActivity(this);
        super.onDestroy();
    }

    public void onBack(View view) {
        UI.removeWaitBox();
        finish();
    }

    public void setFullscreenLayout() {
        View decor = getWindow().getDecorView();
        int flags = decor.getSystemUiVisibility();
        flags |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        decor.setSystemUiVisibility(flags);
    }

    public void changeStatusBarColor(@ColorRes int colorResId) {
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(colorResId));
        }
    }

    /**
     * 简单的消息提示对话框
     *
     * @param title
     * @param message
     */
    public void showDialogMessage(String title, String message) {
        new AlertDialog.Builder(this).setMessage(message).setTitle(title).setCancelable(true).create().show();
    }

    //-----------------------------------------------------------------------------------
    public void showToast(final String msg) {
        if (msg == null) {
            return;
        }

        if (Looper.myLooper() == getMainLooper()) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        } else {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //-------------------------------- Dialog ------------------------------------------------
    ProgressDialog mProgressDialog;

    public void showProgress(final String paramString) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            return;
        }
        if (Looper.getMainLooper() == Looper.myLooper()) {
            this.mProgressDialog = ProgressDialog.show(this, null, paramString, true);
            return;
        }
        runOnUiThread(new Runnable() {
            public void run() {
                BaseActivity.this.mProgressDialog = ProgressDialog.show(BaseActivity.this, null, paramString, true);
            }
        });
    }

    public void showProgress(final String msg, final boolean cancelable) {
        if (this.mProgressDialog == null) {
            return;
        }
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ProgressDialog.show(this, null, msg, cancelable);
            return;
        }
        runOnUiThread(new Runnable() {
            public void run() {
                ProgressDialog.show(BaseActivity.this, null, msg, cancelable);
            }
        });
    }

    public void stopProgress() {
        if (this.mProgressDialog == null || !this.mProgressDialog.isShowing()) {
            return;
        }
        if (Looper.getMainLooper() == Looper.myLooper()) {
            this.mProgressDialog.dismiss();
            return;
        }
        runOnUiThread(new Runnable() {
            public void run() {
                BaseActivity.this.mProgressDialog.dismiss();
            }
        });
    }

}
