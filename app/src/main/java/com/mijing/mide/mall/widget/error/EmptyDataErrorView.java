package com.mijing.mide.mall.widget.error;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.mijing.mide.mall.R;

/**
 * Created by Administrator on 2017/9/27.
 */

public class EmptyDataErrorView extends AbsErrorStateView {
    private TextView mHintTextView;

    public EmptyDataErrorView(Context paramContext) {
        super(paramContext);
    }

    public EmptyDataErrorView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }


    @Override
    public void init(Context context) {
        inflate(context, R.layout.error_view_empty_data, this);
        this.mHintTextView = ((TextView) findViewById(R.id.tv_empty_data));
    }

    @Override
    public void setHint(String hint) {
        this.mHintTextView.setText(hint);
    }

    @Override
    public void setHintDrawableResource(int drawableResource) {
        if (drawableResource != 0) {
            this.mHintTextView.setCompoundDrawablesWithIntrinsicBounds(0, drawableResource, 0, 0);
        }
    }

    @Override
    public void setOnRetryListener(OnRetryListener listener) {

    }
}
