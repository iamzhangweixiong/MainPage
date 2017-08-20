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

public class ResideBgView extends View {

    public static final String TAG = "RainbowBgView";
    private static final int RED = 0xCCFF5D86;
    private static final int BLUE = 0xCC57C6FF;
    private static final int CYAN_GREEN = 0xCD3CF9FF;//青色
    private static final int GREEN = 0xCC83D851;
    private static final int YELLOW = 0xFFFFC720;
    private int mRedCircleRadius, mRedCircleX, mRedCircleY;
    private int mGreenCircleRadius, mGreenCircleX, mGreenCircleY;
    private int mCyanGreenCircleRadius, mCyanGreenCircleX, mCyanGreenCircleY;
    private int mYellowCircleRadius, mYellowCircleX, mYellowCircleY;
    private int mBlueCircleRadius, mBlueStokeWidth, mBlueCircleX, mBlueCircleY;
    private Paint mPaint;

    public ResideBgView(Context context) {
        this(context, null);
    }

    public ResideBgView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ResideBgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCyanGreenCircleX = DrawUtil.dip2px(getContext(), 25f);
        mCyanGreenCircleY = DrawUtil.dip2px(getContext(), -25f);
        mCyanGreenCircleRadius = DrawUtil.dip2px(getContext(), 85f);

        mRedCircleX = DrawUtil.dip2px(getContext(), 260f);
        mRedCircleY = DrawUtil.dip2px(getContext(), 80f);
        mRedCircleRadius = DrawUtil.dip2px(getContext(), 30f);

        mGreenCircleX = DrawUtil.dip2px(getContext(), 260f);
        mGreenCircleY = DrawUtil.dip2px(getContext(), 15f);
        mGreenCircleRadius = DrawUtil.dip2px(getContext(), 20f);

        mYellowCircleX = DrawUtil.dip2px(getContext(), 195f);
        mYellowCircleY = DrawUtil.dip2px(getContext(), 35f);
        mYellowCircleRadius = DrawUtil.dip2px(getContext(), 15f);

        mBlueCircleX = DrawUtil.dip2px(getContext(), 130f);
        mBlueCircleY = DrawUtil.dip2px(getContext(), 8f);
        mBlueStokeWidth = DrawUtil.dip2px(getContext(), 25f);
        mBlueCircleRadius = DrawUtil.dip2px(getContext(), 45f);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(CYAN_GREEN);
        canvas.drawCircle(mCyanGreenCircleX, mCyanGreenCircleY, mCyanGreenCircleRadius, mPaint);
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
