package com.mohammedalaa.gifloading;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;

import java.io.InputStream;

public class GifView extends View {
    public  Movie mMovie;
    public long movieStart;
    private  int gifId;

    private static GifView mInstance;

    public static synchronized GifView getInstance(Context context,int resourceId) {
        if (mInstance == null) {
            mInstance = new GifView(context,resourceId);
        }
        return mInstance;
    }

    public GifView(Context context, int resourceId) {
        super(context);
        initializeView(resourceId,context);
    }

    public GifView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "src", 0),context);

    }

    public GifView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "src", 0),context);
    }

    private  void initializeView(final int id,Context context) {
        InputStream is = context.getResources().openRawResource(id);
        mMovie = Movie.decodeStream(is);
        gifId = id;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        super.onDraw(canvas);
        long now = android.os.SystemClock.uptimeMillis();

        if (movieStart == 0) {
            movieStart = now;
        }

        if (mMovie != null) {
            int relTime = (int) ((now - movieStart) % mMovie.duration());
            mMovie.setTime(relTime);
           mMovie.draw(canvas, getWidth() / 2 - mMovie.width() / 2,
                    (getHeight() / 2) - mMovie.height() / 2);
            this.invalidate();
        }
    }

    public  void setGIFResource(int resId,Context context) {
        gifId = resId;
        initializeView(gifId,context);
    }

    public int getGIFResource() {
        return this.gifId;
    }
}
