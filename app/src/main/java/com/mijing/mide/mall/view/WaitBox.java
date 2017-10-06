package com.mijing.mide.mall.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mijing.mide.mall.R;

/**
 * Created by jiaolongfei on 2016/7/8.
 */
public class WaitBox extends FrameLayout {

    public WaitBox(Context context) {
        super(context);
        initialize();
    }

    public WaitBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    private void initialize() {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_wait_box, this);

        messageTextView = (TextView)findViewById(R.id.messageTextView);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
    }

    private TextView messageTextView;
    private ProgressBar progressBar;

    public void setMessage(String message) {
        messageTextView.setText(message);
    }

    public void setTextColor(int color) {
        messageTextView.setTextColor(color);
    }

    public void setIsTransparent(boolean isTransparent) {
        if (isTransparent) {
            setBackgroundColor(Color.argb(0, 0, 0, 0));
            messageTextView.setTextColor(Color.parseColor("#aaaaaa"));
        }
        else {
            setBackgroundColor(Color.WHITE);
            messageTextView.setTextColor(Color.BLACK);
        }
    }

    public void startAnimation() {
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(VISIBLE);
    }

    public void stopAnimation() {
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(GONE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
