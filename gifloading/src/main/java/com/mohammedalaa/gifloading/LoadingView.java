package com.mohammedalaa.gifloading;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.inputmethodservice.Keyboard;
import android.preference.DialogPreference;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.ContentFrameLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.content.ContentValues.TAG;


public class LoadingView extends LinearLayout {


    private final static int paddingLeft = 5;
    private final static int paddingRight = 5;
    private final static int paddingTop = 5;
    private final static int paddingBottom = 5;

    private Context mContext;

    public LoadingView(Context context, int resourceId, int message, int color) {
        super(context);
        initializeView(resourceId, context, message, color);

    }


    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "src", R.drawable.loading_spinner),
                context, attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "message", -1),
                attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "text_color", R.color.black));


    }


    public LoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "src", R.drawable.loading_spinner),
                context, attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "message", -1),
                attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "text_color", R.color.black));
    }

    private void initializeView(int resourceId, Context context, int message, int color) {
        this.mContext = context;

        LinearLayout.LayoutParams root =
                new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        root.gravity=Gravity.CENTER;
        this.setGravity(Gravity.CENTER);
        this.setLayoutParams(root);
        this.setOrientation(VERTICAL);
        this.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));

        if (message != -1) {
            LinearLayout.LayoutParams textview_LayoutParams =
                    new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textview_LayoutParams.gravity=Gravity.CENTER;
            TextView tvMessage = new TextView(context);
            tvMessage.setText(message);
            tvMessage.setGravity(Gravity.CENTER);
            tvMessage.setTextColor(context.getResources().getColor(color));
            tvMessage.setLayoutParams(textview_LayoutParams);

            GifView gifView=new GifView(context,resourceId);
            LinearLayout.LayoutParams gif_LayoutParams =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            gif_LayoutParams.gravity = Gravity.CENTER;
            gifView.setLayoutParams(gif_LayoutParams);

            this.removeAllViews();
            this.addView(gifView, 0);
            this.addView(tvMessage, 1);

        } else {
            GifView gifView=new GifView(context,resourceId);
            LinearLayout.LayoutParams gif_LayoutParams =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            gif_LayoutParams.gravity = Gravity.CENTER;
            gifView.setLayoutParams(gif_LayoutParams);
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
        disableViewInteraction();
    }

    public void hideLoading() {
        this.setVisibility(GONE);
        enableViewInteraction();

    }

}
