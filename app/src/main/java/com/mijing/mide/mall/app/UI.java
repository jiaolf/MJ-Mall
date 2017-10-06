package com.mijing.mide.mall.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.mijing.mide.mall.base.BaseActivity;
import com.mijing.mide.mall.view.WaitBox;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/21.
 */

public class UI {

    public static void enter(BaseActivity activity) {
        MallApplication.getInstance().enter(activity);
    }

    public static void startActivity(Class<? extends Activity> clazz) {
        Activity activity = MallApplication.getInstance().getCurrentActivity();
        activity.startActivity(new Intent(activity, clazz));
    }

    public static void startActivity(Class<? extends Activity> clazz, Object... keyValueExtra) {
        Activity activity = MallApplication.getInstance().getCurrentActivity();
        Intent intent = new Intent(activity, clazz);

        if (keyValueExtra.length > 0 && keyValueExtra.length % 2 != 0) {
            throw new UnsupportedOperationException("keyValueExtra 必须是偶数");
        }

        for (int i = 0; i < keyValueExtra.length; i++) {
            String key = (String) keyValueExtra[i];
            Object value = keyValueExtra[++i];

            if (value == null) {
                Log.e("jlf", "intent extra data is null, key :" + key);
                continue;
            }

            if (value instanceof Character) {
                intent.putExtra(key, (char) value);
            } else if (value instanceof Byte) {
                intent.putExtra(key, (byte) value);
            } else if (value instanceof Short) {
                intent.putExtra(key, (short) value);
            } else if (value instanceof Integer) {
                intent.putExtra(key, (int) value);
            } else if (value instanceof Boolean) {
                intent.putExtra(key, (boolean) value);
            } else if (value instanceof String) {
                intent.putExtra(key, (String) value);
            } else if (value instanceof Long) {
                intent.putExtra(key, (long) value);
            } else if (value instanceof Double) {
                intent.putExtra(key, (double) value);
            } else if (value instanceof Float) {
                intent.putExtra(key, (float) value);
            } else if (value instanceof Serializable) {
                intent.putExtra(key, (Serializable) value);
            } else if (value instanceof Parcelable) {
                intent.putExtra(key, (Parcelable) value);
            } else
                throw new UnsupportedOperationException("不支持的数据类型调用:" + value + ", 请手动为intent添加extra数据");
        }

        activity.startActivity(intent);
    }

    public static void startActivityForResult(Class<? extends Activity> clazz, int requestCode, Object... keyValueExtra) {
        Activity activity = MallApplication.getInstance().getCurrentActivity();
        Intent intent = new Intent(activity, clazz);

        if (keyValueExtra.length > 0 && keyValueExtra.length % 2 != 0) {
            throw new UnsupportedOperationException("keyValueExtra 必须是偶数");
        }

        for (int i = 0; i < keyValueExtra.length; i++) {
            String key = (String) keyValueExtra[i];
            Object value = keyValueExtra[++i];

            if (value == null) {
                Log.e("jlf", "intent extra data is null, key :" + key);
                continue;
            }

            if (value instanceof Character) {
                intent.putExtra(key, (char) value);
            } else if (value instanceof Byte) {
                intent.putExtra(key, (byte) value);
            } else if (value instanceof Short) {
                intent.putExtra(key, (short) value);
            } else if (value instanceof Integer) {
                intent.putExtra(key, (int) value);
            } else if (value instanceof Boolean) {
                intent.putExtra(key, (boolean) value);
            } else if (value instanceof String) {
                intent.putExtra(key, (String) value);
            } else if (value instanceof Long) {
                intent.putExtra(key, (long) value);
            } else if (value instanceof Double) {
                intent.putExtra(key, (double) value);
            } else if (value instanceof Float) {
                intent.putExtra(key, (float) value);
            } else if (value instanceof Serializable) {
                intent.putExtra(key, (Serializable) value);
            } else if (value instanceof Parcelable) {
                intent.putExtra(key, (Parcelable) value);
            } else
                throw new UnsupportedOperationException("不支持的数据类型调用:" + value + ", 请手动为intent添加extra数据");
        }

        activity.startActivityForResult(intent, requestCode);
    }

    //-------------------隐藏、显示软键盘----------------------
    public static void hideKeyboard() {

        InputMethodManager inputManager = (InputMethodManager) MallApplication.getInstance().getCurrentActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        View view = MallApplication.getInstance().getCurrentActivity().getCurrentFocus();

        if (view != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void showKeyboard(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) editText.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, 0);
    }

    //---------------dp/sp和px之间的转换-----------------------------------
    public static int dip2px(float dpValue) {
        int value = 0;
        try {
            Context context = MallApplication.getInstance().getCurrentActivity();
            value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue,
                    context.getResources().getDisplayMetrics());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static float sp2px(float spValue) {
        Context context = MallApplication.getInstance().getCurrentActivity();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue,
                context.getResources().getDisplayMetrics());
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    // ----------------------------------------------------

    public static void showToast(String msg) {
        final Activity context = MallApplication.getInstance().getCurrentActivity();

        if (Looper.myLooper() == context.getMainLooper()) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        } else {
            final String m = msg;
            context.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(context, m, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    //=================== 半透明的加载框 ======================

    private static WaitBox _waitBox;

    // 半透明的样式显示
    public static void showWaitBox(String message) {
        showWaitBoxWithTransparency(message, true);
    }

    public static void showWaitBox(String message, boolean isTransparency) {
        showWaitBoxWithTransparency(message, isTransparency);
    }

    private static void createWaitBox() {

        WaitBox waitBox = new WaitBox(MallApplication.getInstance().getCurrentActivity());

        ViewGroup rootContainer = (ViewGroup) MallApplication.getInstance().getCurrentActivity()
                .getWindow().getDecorView().findViewById(android.R.id.content);
        rootContainer.addView(waitBox);
        ViewGroup.LayoutParams params = waitBox.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        waitBox.setLayoutParams(params);

        _waitBox = waitBox;
    }

    private static void showWaitBoxWithTransparency(String message, boolean isTransparent) {
        if (_waitBox == null) {
            createWaitBox();
        }

        _waitBox.setMessage(message);
        _waitBox.setIsTransparent(isTransparent);

        _waitBox.startAnimation();
    }

    public static void removeWaitBox() {

        ViewGroup rootContainer = (ViewGroup) MallApplication.getInstance().getCurrentActivity()
                .getWindow().getDecorView().findViewById(android.R.id.content);

        if (rootContainer.getChildAt(rootContainer.getChildCount() - 1) instanceof WaitBox) {
            rootContainer.removeViewAt(rootContainer.getChildCount() - 1);
        }

        disposeWaitBox();
    }

    private static void disposeWaitBox() {
        if (_waitBox != null) {
            _waitBox.stopAnimation();
            _waitBox = null;
        }
    }

    //---------------------------------------------------------------
}
