package com.mijing.mide.mall.widget.error;

import android.content.Context;
import android.support.annotation.DrawableRes;

/**
 * Created by Administrator on 2017/9/27.
 */

public interface IErrorState {
    void init(Context context);

    void setHint(String hint);

    void setHintDrawableResource(@DrawableRes int drawableResource);

    void setOnRetryListener(OnRetryListener listener);
}
