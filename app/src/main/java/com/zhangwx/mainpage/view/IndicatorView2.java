package com.zhangwx.mainpage.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.zhangwx.mainpage.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangweixiong
 */

public class IndicatorView2 extends View {

    private Paint mPaint;
    private int indicatorColor;
    private float indicatorWidth;
    private RectF rectF;
    private float left;
    private List<Float> leftMargins;
    private float indicatorContainerWidth;

    public IndicatorView2(Context context) {
        this(context, null);
    }

    public IndicatorView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView2, defStyleAttr, 0);
        indicatorColor = array.getColor(R.styleable.IndicatorView2_indicator2Color, 0);
        indicatorWidth = array.getDimension(R.styleable.IndicatorView2_indicator2Width, 20);
        array.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(indicatorWidth);
        mPaint.setColor(indicatorColor);
        rectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectF.set(left, 0, left + indicatorWidth, 4);
        canvas.drawRect(rectF, mPaint);
    }

    public void setIndeicateCount(int count) {
        if (count < 1) {
            setVisibility(GONE);
        } else {
            getPositions(count);
            setVisibility(VISIBLE);
        }
    }

    private void getPositions(int count) {
        leftMargins = new ArrayList<>(count);
        final int screenWidth = DrawUtil.getScreenWidth(getContext());
        indicatorContainerWidth = screenWidth / count;
        for (int i = 0; i < count; i++) {
            float leftMargin = (indicatorContainerWidth - indicatorWidth) / 2 + i * indicatorContainerWidth;
            leftMargins.add(leftMargin);
        }
    }

    public void startIndicate(int position, float positionOffset) {
        if (null != leftMargins) {
            left = leftMargins.get(position) + positionOffset * indicatorContainerWidth;
            postInvalidate();
        }
    }

}