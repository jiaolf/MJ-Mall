package com.mijing.mide.mall.widget.error;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.ViewStub;

/**
 * Created by Administrator on 2017/9/27.
 */

public class EmptyStateViewStub implements IErrorState {

    private Context mContext;
    private int mDrawableResId;
    private String mHint;
    private OnRetryListener mOnRetryListener;
    private ViewStub mViewStub;

    public EmptyStateViewStub(Context context, ViewStub viewStub) {
        this.mContext = context;
        this.mViewStub = viewStub;
    }

    public AbsErrorStateView inflate() {
        AbsErrorStateView errorStateView = (AbsErrorStateView) this.mViewStub.inflate();
        if (!TextUtils.isEmpty(mHint)) {
            errorStateView.setHint(mHint);
        }

        if (mOnRetryListener != null) {
            errorStateView.setOnRetryListener(mOnRetryListener);
        }

        if (mDrawableResId > 0) {
            errorStateView.setHintDrawableResource(mDrawableResId);
        }

        return errorStateView;
    }

    @Override
    public void init(Context context) {

    }

    @Override
    public void setHint(String hint) {
        this.mHint = hint;
    }

    @Override
    public void setHintDrawableResource(@DrawableRes int drawableResource) {
        this.mDrawableResId = drawableResource;
    }

    @Override
    public void setOnRetryListener(OnRetryListener listener) {
        this.mOnRetryListener = listener;
    }
}
