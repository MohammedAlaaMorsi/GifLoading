package com.mohammedalaa.gifloading;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.view.View;


import java.io.InputStream;

@SuppressLint("ViewConstructor")
public class GifView extends View {
    public Movie mMovie;
    public long movieStart;



    public GifView(Context context, int resourceId) {
        super(context);
        initializeView(resourceId, context);
    }


    private void initializeView(final int id, Context context) {
        InputStream is = context.getResources().openRawResource(id);
        mMovie = Movie.decodeStream(is);
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


    private int measureHeight(int measureSpec) {
        int size = getPaddingTop() + getPaddingBottom();
        size +=getHeight()+mMovie.height();
        return resolveSizeAndState(size, measureSpec, 0);
    }

    private int measureWidth(int measureSpec) {
        int size = getPaddingLeft() + getPaddingRight();
        size+=getWidth()+mMovie.width();
        return resolveSizeAndState(size, measureSpec, 0);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }


}
