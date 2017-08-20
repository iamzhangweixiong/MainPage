package com.zhangwx.mainpage.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangweixiong on 2017/8/3.
 */

public class RainbowBgView extends View {

    public static final String TAG = "RainbowBgView";
    private static final int RED = 0xFFFF5D86;
    private static final int BLUE = 0xCC6089FF;
    private static final int GREEN = 0xCC3CF9FF;
    private static final int YELLOW = 0xFFFFC720;
    private int mRedCircleRadius, mRedCircleX, mRedCircleY;
    private int mGreenCircleRadius, mGreenCircleX, mGreenCircleY;
    private int mYellowCircleRadius, mYellowCircleX, mYellowCircleY;
    private int mBlueCircleRadius, mBlueStokeWidth, mBlueCircleX, mBlueCircleY;
    private Paint mPaint;

    public RainbowBgView(Context context) {
        this(context, null);
    }

    public RainbowBgView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RainbowBgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mGreenCircleX = DrawUtil.dip2px(getContext(), 20f);
        mGreenCircleY = DrawUtil.dip2px(getContext(), 3f);
        mGreenCircleRadius = DrawUtil.dip2px(getContext(), 40f);

        mRedCircleX = DrawUtil.dip2px(getContext(), 140f);
        mRedCircleY = DrawUtil.dip2px(getContext(), -10f);
        mRedCircleRadius = DrawUtil.dip2px(getContext(), 25f);

        mYellowCircleX = DrawUtil.dip2px(getContext(), 110f);
        mYellowCircleY = DrawUtil.dip2px(getContext(), 22f);
        mYellowCircleRadius = DrawUtil.dip2px(getContext(), 10f);

        mBlueCircleX = DrawUtil.dip2px(getContext(), 65f);
        mBlueCircleY = DrawUtil.dip2px(getContext(), -10f);
        mBlueStokeWidth = DrawUtil.dip2px(getContext(), 15f);
        mBlueCircleRadius = DrawUtil.dip2px(getContext(), 30f);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(GREEN);
        canvas.drawCircle(mGreenCircleX, mGreenCircleY, mGreenCircleRadius, mPaint);
        mPaint.setColor(RED);
        canvas.drawCircle(mRedCircleX, mRedCircleY, mRedCircleRadius, mPaint);
        mPaint.setColor(YELLOW);
        canvas.drawCircle(mYellowCircleX, mYellowCircleY, mYellowCircleRadius, mPaint);
        mPaint.setColor(BLUE);

        mPaint.setStrokeWidth(mBlueStokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mBlueCircleX, mBlueCircleY, mBlueCircleRadius, mPaint);

        mPaint.reset();
    }


    public void setViewPager(ViewPager viewPager) {

    }
}
