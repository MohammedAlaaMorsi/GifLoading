package com.mohammedalaa.gifloading;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;


public class LoadingView extends Dialog {


    private static LoadingView mInstance;

    public LoadingView(Context context, int resourceId) {
        super(context);
        initDialog(resourceId, context);

    }


    public static synchronized LoadingView getInstance(Context context, int resourceId) {
        if (mInstance == null) {
            mInstance = new LoadingView(context, resourceId);
        }
        return mInstance;
    }


    private void initDialog(int resourceId, Context context) {
        setCanceledOnTouchOutside(false);
        if (mInstance != null) {
            setContentView(GifView.getInstance(context, resourceId));
        } else {
            setContentView(new GifView(context, resourceId));
        }
        setCanceledOnTouchOutside(false);

        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setDimAmount(0);
    }


    public void setOnBackButtonPressedDismiss(final Boolean dismiss) {
        this.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
                    if (dismiss) {
                        dialog.dismiss();
                    }
                }
                return true;
            }
        });
    }

    public void showLoading() {
        this.show();
    }

    public void hideLoading() {
        this.dismiss();
    }

}
