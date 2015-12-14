package com.shlmlkzdh.bulletprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;


public class BulletProgressBar extends View {

    private static final int DEFAULT_BULLET_BACKGROUND_COLOR = Color.DKGRAY;
    private static final int DEFAULT_BULLET_COLOR = Color.rgb(255, 150, 33); // Orange
    private static final float DEFAULT_BORDER_WIDTH = 15f;
    private static final float DEFAULT_SHADOW_RADIUS = 0f;
    private static final int DEFAULT_LENGTH = 5;
    private static final int DEFAULT_PROGRESS = 0;

    private int mBulletBackgroundColor;
    private int mBulletColor;
    private float mBorderWidth;
    private float mShadowRadius;
    private int mLength;
    private int mProgress;

    private Paint mBulletBackgroundPaint;
    private Paint mBulletPaint;

    public BulletProgressBar(Context context) {
        super(context);
        initialize();
    }

    public BulletProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BulletProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme()
                .obtainStyledAttributes(
                        attrs,
                        R.styleable.BulletProgressBar,
                        defStyleAttr,
                        0
                );

        try {
            mBulletBackgroundColor = typedArray.getColor(
                    R.styleable.BulletProgressBar_bp_bullet_background_color,
                    DEFAULT_BULLET_BACKGROUND_COLOR
            );
            mBulletColor = typedArray.getColor(
                    R.styleable.BulletProgressBar_bp_bullet_color,
                    DEFAULT_BULLET_COLOR
            );
            mBorderWidth = typedArray.getDimension(
                    R.styleable.BulletProgressBar_bp_border_width,
                    DEFAULT_BORDER_WIDTH
            );
            mShadowRadius = typedArray.getDimension(
                    R.styleable.BulletProgressBar_bp_shadow_radius,
                    DEFAULT_SHADOW_RADIUS
            );
            mLength = typedArray.getInt(
                    R.styleable.BulletProgressBar_bp_length,
                    DEFAULT_LENGTH
            );
            mProgress = typedArray.getInt(
                    R.styleable.BulletProgressBar_bp_progress,
                    DEFAULT_PROGRESS
            );
            mProgress = Math.min(mProgress, mLength);
        } finally {
            typedArray.recycle();
        }

        initialize();

    }

    private void initialize() {

        mBulletBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBulletBackgroundPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mBulletBackgroundPaint.setStrokeWidth(mBorderWidth);
        mBulletBackgroundPaint.setColor(mBulletBackgroundColor);
        mBulletBackgroundPaint.setShadowLayer(
                mShadowRadius,
                0,
                mShadowRadius,
                mBulletBackgroundColor
        );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(LAYER_TYPE_SOFTWARE, mBulletBackgroundPaint);
        }
        mBulletPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBulletPaint.setStyle(Paint.Style.FILL);
        mBulletPaint.setColor(mBulletColor);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        if (mProgress > mLength) {
            throw new RuntimeException("Progress cannot be greater than the length.");
        }

        int width = getWidth();
        int height = getHeight();

        float radius = computeRadius(width, height);

        canvas.drawRect(
                radius + mBorderWidth + mShadowRadius,
                (((float) height / 2f) - (radius / 4f)),
                width - (radius + mBorderWidth + mShadowRadius),
                (((float) height / 2f) + (radius / 4f)),
                mBulletBackgroundPaint
        );

        float cellWidth = (width - (2 * (radius + mBorderWidth + mShadowRadius))) / (mLength - 1);

        for (int i = 0; i < mLength; i++) {
            canvas.drawCircle(
                    (radius + mBorderWidth + mShadowRadius) + (i * cellWidth),
                    height / 2,
                    radius,
                    mBulletBackgroundPaint
            );
        }

        if (mProgress >= 1) {
            canvas.drawRect(
                    radius + mBorderWidth + mShadowRadius,
                    (((float) height / 2f) - (radius / 4f)),
                    (radius + mBorderWidth + mShadowRadius) + ((mProgress - 1) * cellWidth),
                    (((float) height / 2f) + (radius / 4f)),
                    mBulletPaint
            );
        }

        for (int i = 0; i < mProgress; i++) {
            canvas.drawCircle(
                    (radius + mBorderWidth + mShadowRadius) + (i * cellWidth),
                    height / 2,
                    radius,
                    mBulletPaint
            );
        }

    }

    public int getBulletBackgroundColor() {
        return mBulletBackgroundColor;
    }

    public int getBulletColor() {
        return mBulletColor;
    }

    public float getBorderWidth() {
        return mBorderWidth;
    }

    public float getShadowRadius() {
        return mShadowRadius;
    }

    public int getLength() {
        return mLength;
    }

    public int getProgress() {
        return mProgress;
    }

    public void setBulletBackgroundColor(int color) {

        mBulletBackgroundColor = color;
        invalidate();
        requestLayout();

    }

    public void setBulletColor(int color) {

        mBulletColor = color;
        invalidate();
        requestLayout();

    }

    public void setBorderWidth(float width) {

        mBorderWidth = width;
        invalidate();
        requestLayout();

    }

    public void setShadowRadius(float radius) {

        mShadowRadius = radius;
        invalidate();
        requestLayout();

    }

    public void setLength(int length) {

        mLength = length;
        invalidate();
        requestLayout();

    }

    public void setProgress(int progress) {

        mProgress = progress;
        invalidate();
        requestLayout();

    }

    public float computeRadius(int width, int height) {
        return Math.min(
                ((float) height - (2f * mBorderWidth) - (2f * mShadowRadius)) / 2f,
                (float) height / 2
        );
    }

}
