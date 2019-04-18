package com.mohammedalaa.gifloading;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;



public class LoadingView extends LinearLayout {


    private Context mContext;
    private boolean mDisable = false;


    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(
                context, attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "message", -1),
                attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "text_color", R.color.black),
                attrs.getAttributeBooleanValue("http://schemas.android.com/apk/res-auto", "block_while_loading", false),
                attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "srcImg", R.drawable.loading_spinner),
                attrs

        );


    }


    public LoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(
                context, attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "message", -1),
                attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "text_color", R.color.black),
                attrs.getAttributeBooleanValue("http://schemas.android.com/apk/res-auto", "block_while_loading", false),
                attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "srcImg", R.drawable.loading_spinner),
                attrs
        );
    }

    private void initializeView(Context context, int message, int color, boolean disable, int resourceId, AttributeSet attrs) {
        this.mContext = context;
        this.mDisable = disable;

        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LoadingView, 0, 0);
        float textSize = ta.getDimensionPixelSize(R.styleable.LoadingView_text_size, R.dimen.default_text_size);
        ta.recycle();
        LinearLayout.LayoutParams root =
                new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        root.gravity = Gravity.CENTER;
        this.setGravity(Gravity.CENTER);
        this.setLayoutParams(root);
        this.setOrientation(VERTICAL);
        this.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));

        if (message != -1) {
            LinearLayout.LayoutParams textViewLayoutParams =
                    new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textViewLayoutParams.gravity = Gravity.CENTER;
            TextView tvMessage = new TextView(context);
            tvMessage.setText(message);
            tvMessage.setGravity(Gravity.CENTER);
            tvMessage.setTextColor(context.getResources().getColor(color));
            tvMessage.setTextSize(textSize);
            tvMessage.setLayoutParams(textViewLayoutParams);

            GifView gifView = new GifView(context, resourceId);
            LinearLayout.LayoutParams gifLayoutParams =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            gifLayoutParams.gravity = Gravity.CENTER;
            gifView.setLayoutParams(gifLayoutParams);

            this.removeAllViews();
            this.addView(gifView, 0);
            this.addView(tvMessage, 1);

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
        if (mDisable) disableViewInteraction();
    }

    public void hideLoading() {
        this.setVisibility(GONE);
        if (mDisable) enableViewInteraction();

    }

}
