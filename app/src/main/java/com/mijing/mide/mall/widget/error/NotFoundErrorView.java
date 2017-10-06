package com.mijing.mide.mall.widget.error;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mijing.mide.mall.R;

/**
 * Created by Administrator on 2017/9/27.
 */

public class NotFoundErrorView extends AbsErrorStateView {

    private ImageView mHintImageView;
    private TextView mHintTextView;

    public NotFoundErrorView(Context context) {
        super(context);
    }

    public NotFoundErrorView(Context context, AttributeSet paramAttributeSet) {
        super(context, paramAttributeSet);
    }

    @Override
    public void init(Context context) {
        View.inflate(context, R.layout.error_view_not_found, this);
        this.mHintImageView = ((ImageView) findViewById(R.id.iv_error_hint));
        this.mHintTextView = ((TextView) findViewById(R.id.tv_error_hint));
    }

    @Override
    public void setHint(String hint) {
        mHintTextView.setText(hint);
    }

    @Override
    public void setHintDrawableResource(@DrawableRes int drawableResource) {
        mHintImageView.setImageResource(drawableResource);
    }

    @Override
    public void setOnRetryListener(OnRetryListener listener) {

    }
}
