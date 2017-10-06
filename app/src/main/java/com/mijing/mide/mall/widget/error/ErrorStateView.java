package com.mijing.mide.mall.widget.error;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.RelativeLayout;

import com.mijing.mide.mall.R;

/**
 * Created by Administrator on 2017/9/27.
 */

public class ErrorStateView extends RelativeLayout {
    private AbsErrorStateView mEmptyDataHintView;
    private AbsErrorStateView mLoadingHintView;
    private AbsErrorStateView mNetworkOffHintView;
    private AbsErrorStateView mNotFoundHintView;
    private AbsErrorStateView mRequestFailHintView;

    private OnRetryListener mOnRetryListener;
    private boolean mShowLoadingView = false;
    private ErrorState mState;

    private EmptyStateViewStub mVStubLoading;
    private EmptyStateViewStub mVStubNetworkOff;
    private EmptyStateViewStub mVStubNoData;
    private EmptyStateViewStub mVStubRequestFail;
    private EmptyStateViewStub mVStubUnLogin;

    public ErrorStateView(Context context) {
        super(context);
        init(context);
    }

    public ErrorStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ErrorStateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context paramContext) {
        View.inflate(paramContext, R.layout.error_state_view, this);
        this.mVStubNoData = new EmptyStateViewStub(paramContext, (ViewStub) findViewById(R.id.viewstub_empty_data));
        this.mVStubNetworkOff = new EmptyStateViewStub(paramContext, (ViewStub) findViewById(R.id.viewstub_network_off));
        this.mVStubRequestFail = new EmptyStateViewStub(paramContext, (ViewStub) findViewById(R.id.viewstub_request_fail));
        this.mVStubUnLogin = new EmptyStateViewStub(paramContext, (ViewStub) findViewById(R.id.viewstub_not_found));
        this.mVStubLoading = new EmptyStateViewStub(paramContext, (ViewStub) findViewById(R.id.viewstub_loading));
    }

    private void tryInflateViewStub(ErrorState errorState) {
        switch (errorState) {
            case NetWorkOff:
                if (mNetworkOffHintView == null) {
                    mNetworkOffHintView = mVStubNetworkOff.inflate();
                }
                break;

            case RequestFail:
                if (mRequestFailHintView == null) {
                    mRequestFailHintView = mVStubRequestFail.inflate();
                }
                break;

            case EmptyData:
                if (mEmptyDataHintView == null) {
                    mEmptyDataHintView = mVStubNoData.inflate();
                }
                break;

            case NotFound:
                if (mNotFoundHintView == null) {
                    mNotFoundHintView = mVStubUnLogin.inflate();
                }
                break;

            case Loading:
                if (mLoadingHintView == null) {
                    mLoadingHintView = mVStubLoading.inflate();
                }
                break;
        }
    }

    private void updateEmptyDataView() {
        mEmptyDataHintView.setVisibility(View.VISIBLE);

        if (this.mNetworkOffHintView != null) {
            this.mNetworkOffHintView.setVisibility(View.INVISIBLE);
        }
        if (this.mRequestFailHintView != null) {
            this.mRequestFailHintView.setVisibility(View.INVISIBLE);
        }
        if (this.mNotFoundHintView != null) {
            this.mNotFoundHintView.setVisibility(View.INVISIBLE);
        }
        if (this.mLoadingHintView != null) {
            this.mLoadingHintView.setVisibility(View.INVISIBLE);
        }
    }

    private void updateLoadingView() {
        mLoadingHintView.setVisibility(View.VISIBLE);

        if (this.mNetworkOffHintView != null) {
            this.mNetworkOffHintView.setVisibility(View.INVISIBLE);
        }
        if (this.mRequestFailHintView != null) {
            this.mRequestFailHintView.setVisibility(View.INVISIBLE);
        }
        if (this.mNotFoundHintView != null) {
            this.mNotFoundHintView.setVisibility(View.INVISIBLE);
        }
        if (this.mEmptyDataHintView != null) {
            this.mEmptyDataHintView.setVisibility(View.INVISIBLE);
        }
    }

    private void updateNetworkOffView() {
        this.mNetworkOffHintView.setVisibility(View.VISIBLE);

        if (this.mEmptyDataHintView != null) {
            this.mEmptyDataHintView.setVisibility(View.INVISIBLE);
        }
        if (this.mRequestFailHintView != null) {
            this.mRequestFailHintView.setVisibility(View.INVISIBLE);
        }
        if (this.mNotFoundHintView != null) {
            this.mNotFoundHintView.setVisibility(View.INVISIBLE);
        }
        if (this.mLoadingHintView != null) {
            this.mLoadingHintView.setVisibility(View.INVISIBLE);
        }
    }

    private void updateNotFoundView() {
        this.mNotFoundHintView.setVisibility(View.VISIBLE);

        if (this.mEmptyDataHintView != null) {
            this.mEmptyDataHintView.setVisibility(View.INVISIBLE);
        }
        if (this.mNetworkOffHintView != null) {
            this.mNetworkOffHintView.setVisibility(View.INVISIBLE);
        }
        if (this.mRequestFailHintView != null) {
            this.mRequestFailHintView.setVisibility(View.INVISIBLE);
        }
        if (this.mLoadingHintView != null) {
            this.mLoadingHintView.setVisibility(View.INVISIBLE);
        }

    }

    private void updateRequestFailedView() {
        this.mRequestFailHintView.setVisibility(View.VISIBLE);

        if (this.mEmptyDataHintView != null) {
            this.mEmptyDataHintView.setVisibility(View.INVISIBLE);
        }
        if (this.mNetworkOffHintView != null) {
            this.mNetworkOffHintView.setVisibility(View.INVISIBLE);
        }
        if (this.mNotFoundHintView != null) {
            this.mNotFoundHintView.setVisibility(View.INVISIBLE);
        }
        if (this.mLoadingHintView != null) {
            this.mLoadingHintView.setVisibility(View.INVISIBLE);
        }
    }

    public void replaceLoadingHintView(AbsErrorStateView paramAbsErrorStateView) {
        int i = indexOfChild(this.mLoadingHintView);
        removeView(this.mLoadingHintView);
        addView(paramAbsErrorStateView, i);
        this.mLoadingHintView = paramAbsErrorStateView;
    }

    public void replaceNoDataHintView(AbsErrorStateView paramAbsErrorStateView) {
        tryInflateViewStub(ErrorState.EmptyData);
        int i = indexOfChild(this.mEmptyDataHintView);
        removeView(this.mEmptyDataHintView);
        addView(paramAbsErrorStateView, i);
        this.mEmptyDataHintView = paramAbsErrorStateView;
    }

    public void replaceNotFoundHintView(AbsErrorStateView paramAbsErrorStateView) {
        tryInflateViewStub(ErrorState.NotFound);
        int i = indexOfChild(this.mNotFoundHintView);
        removeView(this.mNotFoundHintView);
        addView(paramAbsErrorStateView, i);
        this.mNotFoundHintView = paramAbsErrorStateView;
    }

    public void replaceRequestFailHintView(AbsErrorStateView paramAbsErrorStateView) {
        tryInflateViewStub(ErrorState.RequestFail);
        int i = indexOfChild(this.mRequestFailHintView);
        removeView(this.mRequestFailHintView);
        addView(paramAbsErrorStateView, i);
        paramAbsErrorStateView.setOnRetryListener(this.mOnRetryListener);
        this.mRequestFailHintView = paramAbsErrorStateView;
    }

    public void replaceWifiOffHintView(AbsErrorStateView paramAbsErrorStateView) {
        tryInflateViewStub(ErrorState.NetWorkOff);
        int i = indexOfChild(this.mNetworkOffHintView);
        removeView(this.mNetworkOffHintView);
        addView(paramAbsErrorStateView, i);
        paramAbsErrorStateView.setOnRetryListener(this.mOnRetryListener);
        this.mNetworkOffHintView = paramAbsErrorStateView;
    }

    public void setEmptyDataDrawableResource(int paramInt) {
        if (this.mEmptyDataHintView == null) {
            this.mVStubNoData.setHintDrawableResource(paramInt);
            return;
        }
        this.mEmptyDataHintView.setHintDrawableResource(paramInt);
    }

    public void setEmptyDataHint(int paramInt) {
        setEmptyDataHint(getContext().getString(paramInt));
    }

    public void setEmptyDataHint(String paramString) {
        if (this.mEmptyDataHintView == null) {
            this.mVStubNoData.setHint(paramString);
            return;
        }
        this.mEmptyDataHintView.setHint(paramString);
    }

    public void setLoadingHint(int paramInt) {
        setLoadingHint(getContext().getString(paramInt));
    }

    public void setLoadingHint(String paramString) {
        if (this.mLoadingHintView == null) {
            this.mVStubLoading.setHint(paramString);
            return;
        }
        this.mLoadingHintView.setHint(paramString);
    }

    public void setOnRetryListener(OnRetryListener paramOnRetryListener) {
        this.mOnRetryListener = paramOnRetryListener;
        if (this.mRequestFailHintView != null) {
            this.mRequestFailHintView.setOnRetryListener(paramOnRetryListener);
        }
        if(this.mNetworkOffHintView != null) {
            this.mVStubNetworkOff.setOnRetryListener(paramOnRetryListener);
        }
    }

    public void setRequestFailedDrawableResource(int paramInt) {
        if (this.mRequestFailHintView == null) {
            this.mVStubRequestFail.setHintDrawableResource(paramInt);
            return;
        }
        this.mRequestFailHintView.setHintDrawableResource(paramInt);
    }

    public void setRequestFailedHint(int paramInt) {
        setRequestFailedHint(getContext().getString(paramInt));
    }

    public void setRequestFailedHint(String paramString) {
        if (this.mRequestFailHintView == null) {
            this.mVStubRequestFail.setHint(paramString);
            return;
        }
        this.mRequestFailHintView.setHint(paramString);
    }

    public void setShowLoadingView(boolean paramBoolean) {
        this.mShowLoadingView = paramBoolean;
        if (this.mLoadingHintView != null) {
            this.mLoadingHintView.setVisibility(View.INVISIBLE);
        }
    }

    public void setUnLoginHint(String paramString) {
        if (this.mNotFoundHintView == null) {
            this.mVStubUnLogin.setHint(paramString);
            return;
        }
        this.mNotFoundHintView.setHint(paramString);
    }

    public void setUnloginHint(int paramInt) {
        setUnLoginHint(getContext().getString(paramInt));
    }

    public void setWifiOffHint(int paramInt) {
        setWifiOffHint(getContext().getString(paramInt));
    }

    public void setWifiOffHint(String paramString) {
        if (this.mNetworkOffHintView == null) {
            this.mVStubNetworkOff.setHint(paramString);
            return;
        }
        this.mNetworkOffHintView.setHint(paramString);
    }

    public void updateState(ErrorState state) {
        if (this.mState == state) {
            return;
        }
        this.mState = state;
        tryInflateViewStub(state);
        if (state == ErrorState.NONE) {
            setVisibility(View.INVISIBLE);
        } else if (state == ErrorState.RequestFail) {
            updateRequestFailedView();
        } else if (state == ErrorState.EmptyData) {
            updateEmptyDataView();
        } else if (state == ErrorState.Loading) {
            updateLoadingView();
        } else if (state == ErrorState.NetWorkOff) {
            updateNetworkOffView();
        } else if (state == ErrorState.NotFound) {
            updateNotFoundView();
        }
    }
}
