package com.mijing.mide.mall.widget.error;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.mijing.mide.mall.R;

/**
 * Created by Administrator on 2017/9/27.
 */

public class LoadingErrorView extends AbsErrorStateView {

    private TextView mHintTextView;

    public LoadingErrorView(Context paramContext) {
        super(paramContext);
    }

    public LoadingErrorView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    @Override
    public void init(Context context) {
        View.inflate(context, R.layout.error_view_loading, this);
        this.mHintTextView = ((TextView)findViewById(R.id.tv_loading));
    }

    @Override
    public void setHint(String hint) {
        mHintTextView.setText(hint);
    }

    @Override
    public void setHintDrawableResource(@DrawableRes int drawableResource) {

    }

    @Override
    public void setOnRetryListener(OnRetryListener listener) {

    }
}
