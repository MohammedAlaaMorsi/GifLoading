package com.mohammedalaa.gifloading;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


public class LoadingView extends LinearLayout {


    private Context mContext;
    private int loadingTextSize;
    private String loadingText;
    private int resourceId;
    private int loadingTextColor;
    private boolean blockUiWhileLoading = false;

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context, attrs);
    }

    private void initializeView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.LoadingView);
        this.mContext = context;


        if (typedArray.hasValue(R.styleable.LoadingView_block_while_loading)) {
            blockUiWhileLoading = typedArray.getBoolean(R.styleable.LoadingView_block_while_loading, false);
        }

        if (typedArray.hasValue(R.styleable.LoadingView_loading_text)) {
            loadingText = typedArray.getString(R.styleable.LoadingView_loading_text);
        }

        if (typedArray.hasValue(R.styleable.LoadingView_loading_text_size)) {
            loadingTextSize = typedArray.getDimensionPixelSize(R.styleable.LoadingView_loading_text_size, 13);
        }
        if (typedArray.hasValue(R.styleable.LoadingView_loading_text_color)) {
            loadingTextColor = typedArray.getInt(R.styleable.LoadingView_loading_text_color, Color.BLACK);
        }

        if (typedArray.hasValue(R.styleable.LoadingView_srcImg)) {
            resourceId = typedArray.getResourceId(R.styleable.LoadingView_srcImg, R.drawable.loading_spinner);
        }
        drawViews(context);
        typedArray.recycle();
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(context, attrs);

    }


    private void drawViews(Context context) {

        LinearLayout.LayoutParams root = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        root.gravity = Gravity.CENTER;
        this.setGravity(Gravity.CENTER);
        this.setLayoutParams(root);
        this.setOrientation(VERTICAL);
        this.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        if (!TextUtils.isEmpty(loadingText)) {
            LinearLayout.LayoutParams textViewLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            textViewLayoutParams.gravity = Gravity.CENTER;
            TextView tvLoadingText = new TextView(context);
            tvLoadingText.setText(loadingText);
            tvLoadingText.setGravity(Gravity.CENTER);
            tvLoadingText.setTextColor(loadingTextColor);
            if (loadingTextSize > 0) {
                tvLoadingText.setTextSize(TypedValue.COMPLEX_UNIT_PX, loadingTextSize);
            }
            tvLoadingText.setBackgroundColor(Color.TRANSPARENT);
            tvLoadingText.setLayoutParams(textViewLayoutParams);
            GifView gifView = new GifView(context, resourceId);
            LinearLayout.LayoutParams gifLayoutParams =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            gifLayoutParams.gravity = Gravity.CENTER;
            gifView.setLayoutParams(gifLayoutParams);
            this.removeAllViews();
            this.addView(gifView, 0);
            this.addView(tvLoadingText, 1);

        } else {
            GifView gifView = new GifView(context, resourceId);
            LinearLayout.LayoutParams gifLayoutParams =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            gifLayoutParams.gravity = Gravity.CENTER;
            gifView.setLayoutParams(gifLayoutParams);
            this.addView(gifView, 0);
        }
        enableViewInteraction();
    }


    private void disableViewInteraction() {
        getActivity(mContext).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }


    private void enableViewInteraction() {
        getActivity(mContext).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public Activity getActivity(Context context) {
        if (context == null) return null;
        if (context instanceof Activity) return (Activity) context;
        if (context instanceof ContextWrapper)
            return getActivity(((ContextWrapper) context).getBaseContext());
        return null;
    }


    public void showLoading() {
        this.setVisibility(VISIBLE);
        if (blockUiWhileLoading) disableViewInteraction();
    }

    public void hideLoading() {
        this.setVisibility(GONE);
        if (blockUiWhileLoading) enableViewInteraction();

    }

}
