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
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ProgressableImageView extends AppCompatImageView {

    private Paint grayPaint;
    private Paint dividerPaint;

    private final float DEFAULT_DIVIDER_WIDTH = 10.0f;
    private final int DEFAULT_DIVIDER_COLOR = Color.BLACK;
    private final float DEFAULT_PROGRESS = 0.3f;
    private final int DEFAULT_DIRECTION = 0;
    private final boolean DEFAULT_TOUCHABLE = false;

    private Bitmap original;
    private float dividerWidth;
    private int dividerColor;
    private float progress;
    private ProgressDirection direction;
    private boolean touchEnabled;
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
        direction = ProgressDirection.fromId(typedArray.getInt(R.styleable.ProgressableImageView_direction, DEFAULT_DIRECTION));
        touchEnabled = typedArray.getBoolean(R.styleable.ProgressableImageView_touchEnabled, DEFAULT_TOUCHABLE);
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

    public void setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        updateDividerColor(dividerColor);
        invalidate();
    }

    public void setProgress(float progress) {
        if (progress < 0 || progress > 1)
            throw new IllegalArgumentException("Progress must be between 0 and 1");
        this.progress = progress;
        updateRects();
        invalidate();
    }

    public ProgressDirection getDirection() {
        return direction;
    }

    public void setDirection(ProgressDirection direction) {
        this.direction = direction;
        updateRects();
        invalidate();
    }

    private void updateDividerColor(int dividerColor) {
        dividerPaint.setColor(dividerColor);
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        original = null;
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        original = null;
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        original = null;
    }

    @Override
    public void setImageIcon(@Nullable Icon icon) {
        super.setImageIcon(icon);
        original = null;
    }

    @Override
    public void setImageURI(@Nullable Uri uri) {
        super.setImageURI(uri);
        original = null;
    }

    @Override
    public void invalidateDrawable(@NonNull Drawable dr) {
        super.invalidateDrawable(dr);
        original = null;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        updateRects();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        updateRects();
    }

    private void updateRects() {
        updateOpenRect();
        updateGrayRect();
        updateDividerRect();
    }

    private void updateOpenRect() {
        float w = getWidth() * progress - dividerWidth / 2;
        float h = getHeight() * progress - dividerWidth / 2;
        switch (direction){
            case left_to_right:
                openRectF.set(0, 0, (int)w, getHeight());
                break;
            case right_to_left:
                openRectF.set((int)(getWidth() - w), 0, getWidth(), getHeight());
                break;
            case top_to_bottom:
                openRectF.set(0, 0, getWidth(), (int)h);
                break;
            case bottom_to_top:
                openRectF.set(0, (int)(getHeight() - h), getWidth(), getHeight());
                break;
        }
    }

    private void updateDividerRect() {
        float left = getWidth() * progress;
        float top = getHeight() * progress;
        switch (direction){
            case left_to_right:
                dividerRect.set((int)(left - dividerWidth / 2), 0, (int) (left + dividerWidth / 2), getHeight());
                break;
            case right_to_left:
                dividerRect.set((int)(getWidth() - (left - dividerWidth / 2)), 0, (int) (getWidth() - (left + dividerWidth / 2)), getHeight());
                break;
            case top_to_bottom:
                dividerRect.set(0, (int)(top - dividerWidth / 2), getWidth(), (int) (top + dividerWidth / 2));
                break;
            case bottom_to_top:
                dividerRect.set(0, (int)(getHeight() - (top - dividerWidth / 2)), getWidth(), (int) (getHeight() - (top + dividerWidth / 2)));
                break;
        }
    }

    private void updateGrayRect() {
        float left = getWidth() * progress + dividerWidth / 2;
        float top = getHeight() * progress + dividerWidth / 2;

        switch (direction){
            case left_to_right:
                grayRectF.set((int)left, 0, getWidth(), getHeight());
                break;
            case right_to_left:
                grayRectF.set(0, 0, (int)(getWidth() - left), getHeight());
                break;
            case top_to_bottom:
                grayRectF.set(0, (int)top, getWidth(), getHeight());
                break;
            case bottom_to_top:
                grayRectF.set(0, 0, getWidth(), (int)(getHeight() - top));
                break;
        }
    }

    public boolean isTouchEnabled() {
        return touchEnabled;
    }

    public void setTouchEnabled(boolean touchEnabled) {
        this.touchEnabled = touchEnabled;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!touchEnabled || !isViewTouched(this, event))
            return super.onTouchEvent(event);

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                calculateProgress(event);
                break;
        }

        return true;
    }

    public static boolean isViewTouched(View view, MotionEvent motionEvent) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return !(motionEvent.getRawX() < ((float) i) || motionEvent.getRawX() > ((float) (i + view.getWidth())) || motionEvent.getRawY() < ((float) i2) || motionEvent.getRawY() > ((float) (i2 + view.getHeight())));
    }

    private void calculateProgress(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        float progress = 0;
        switch (direction){
            case left_to_right:
                progress = x / getWidth();
                break;
            case right_to_left:
                progress = 1 - x / getWidth();
                break;
            case top_to_bottom:
                progress = y / getHeight();
                break;
            case bottom_to_top:
                progress = 1 - y / getHeight();
                break;
        }
        if (progress != this.progress) {
            progress = Math.min(1, Math.max(0, progress));
            setProgress(progress);
        }
    }
}
