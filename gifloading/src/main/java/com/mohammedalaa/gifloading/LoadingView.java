package com.mohammedalaa.gifloading;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


public class LoadingView extends LinearLayout {


    private Context context;
    public LoadingView(Context context, int resourceId, int message) {
        super(context);
        initializeView(resourceId, context, message);
    }


    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "src", 0), context, attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "message", R.string.loading));

    }

    public LoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "src", 0), context, attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "message", R.string.loading));
    }


    private void initializeView(int resourceId, Context context, int message) {
        this.context = context;

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(params);
        this.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_dark));
        this.setOrientation(LinearLayout.VERTICAL);
        this.setGravity(Gravity.CENTER);
        this.addView(new GifView(context,resourceId));
        enableViewInteraction();
    }

    private void disableViewInteraction() {
        getActivity(context).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void enableViewInteraction() {
        getActivity(context).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public static Activity getActivity(Context context) {
        if (context == null) return null;
        if (context instanceof Activity) return (Activity) context;
        if (context instanceof ContextWrapper)
            return getActivity(((ContextWrapper) context).getBaseContext());
        return null;
    }

    public void setOnBackButtonPressedDismiss(final Boolean dismiss) {
        this.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
                    if (dismiss) {
                        hideLoading();
                    }
                }
                return true;
            }
        });
    }

    public void showLoading() {
        this.setVisibility(VISIBLE);
        disableViewInteraction();
    }

    public void hideLoading() {
        this.setVisibility(GONE);
        enableViewInteraction();

    }
}
