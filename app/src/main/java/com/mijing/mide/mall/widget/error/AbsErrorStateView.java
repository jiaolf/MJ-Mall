package com.mijing.mide.mall.widget.error;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2017/9/27.
 */

public abstract class AbsErrorStateView extends RelativeLayout implements IErrorState {
    public AbsErrorStateView(Context context) {
        super(context);
        init(context);
    }

    public AbsErrorStateView(Context context, AttributeSet paramAttributeSet) {
        super(context, paramAttributeSet);
        init(context);
    }
}
