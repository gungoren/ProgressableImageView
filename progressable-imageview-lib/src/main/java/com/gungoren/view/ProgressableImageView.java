package com.gungoren.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorRes;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class ProgressableImageView extends AppCompatImageView {

    private Paint grayPaint;
    private Paint dividerPaint;

    private final float DEFAULT_DIVIDER_WIDTH = 10.0f;
    private final int DEFAULT_DIVIDER_COLOR = Color.BLACK;
    private final float DEFAULT_PROGRESS = 0.3f;

    private Bitmap original;
    private float dividerWidth;
    private int dividerColor;
    private float progress;
    private Rect dividerRect = new Rect();
    private Rect grayRectF = new Rect();
    private Rect openRectF = new Rect();

    public ProgressableImageView(Context context) {
        this(context, null);
    }

    public ProgressableImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {

        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ProgressableImageView);
        dividerColor = typedArray.getColor(R.styleable.ProgressableImageView_dividerColor, DEFAULT_DIVIDER_COLOR);
        dividerWidth = typedArray.getDimension(R.styleable.ProgressableImageView_dividerWidth, DEFAULT_DIVIDER_WIDTH);
        progress = typedArray.getFloat(R.styleable.ProgressableImageView_progress, DEFAULT_PROGRESS);
        typedArray.recycle();

        if (progress > 1 || progress < 0) {
            progress = DEFAULT_PROGRESS;
        }

        grayPaint = new Paint();
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        grayPaint.setColorFilter(filter);

        dividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dividerPaint.setStyle(Paint.Style.FILL);
        updateDividerColor(dividerColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (original == null) {
            original = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas originalCanvas = new Canvas(original);
            super.onDraw(originalCanvas);
            updateRects();
        }

        if (progress >= 0 && progress <= 1) {
            canvas.drawBitmap(original, openRectF, openRectF,null);
            if (dividerWidth > 0) {
                canvas.drawRect(dividerRect, dividerPaint);
            }
            canvas.drawBitmap(original, grayRectF, grayRectF, grayPaint);
        } else {
            canvas.drawBitmap(original, 0,0, null);
        }
    }

    public void setDividerWidthAsPx(float px) {
        this.dividerWidth = px;
        updateRects();
        invalidate();
    }

    public void setDividerWidthAsDp(float dp) {
        this.dividerWidth = pxFromDp(dp);
        updateRects();
        invalidate();
    }

    public float pxFromDp(final float dp) {
        return dp * getContext().getResources().getDisplayMetrics().density;
    }

    public void setDividerColor(@ColorRes  int dividerColor) {
        this.dividerColor = dividerColor;
        updateDividerColor(dividerColor);
        invalidate();
    }

    public void setProgress(float progress) {
        if (progress < 0 || progress > 1)
            throw new RuntimeException("Progress must be between 0 and 1");
        this.progress = progress;
        updateRects();
        invalidate();
    }

    private void updateDividerColor(int dividerColor) {
        dividerPaint.setColor(dividerColor);
    }

    private void updateRects() {
        updateOpenRect();
        updateGrayRect();
        updateDividerRect();
    }

    private void updateOpenRect() {
        float w = getWidth() * progress - dividerWidth / 2;
        openRectF.set(0, 0, (int)w, getHeight());
    }

    private void updateDividerRect() {
        float left = getWidth() * progress;
        dividerRect.set((int)(left - dividerWidth / 2), 0, (int) (left + dividerWidth / 2), getHeight());
    }

    private void updateGrayRect() {
        float w = getWidth() * progress + dividerWidth / 2;
        grayRectF.set((int)w, 0, getWidth(), getHeight());
    }
}
