package com.zhangwx.mainpage.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.zhangwx.mainpage.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhangweixiong on 2017/8/3.
 */

public class IndicatorLineView extends View implements ViewPager.OnPageChangeListener {

    public static final String TAG = "IndicatorTabView";

    public static final int DEFAULT_COLOR = 0;
    public static final int DEFAULT_WIDTH = 20;
    public static final int DEFAULT_HEIGHT = 6;

    public static final int DEFAULT_PADDING_BOTTOM = 6;

    private int mIndicatorColor;
    private float mLeft;
    private float mIndicatorWidth;
    private float mIndicatorHeight;
    private float mTabAreaWidth;
    private Paint mPaint;
    private RectF mIndicatorRectF;
    private ViewPager mViewPager;
    private List<Float> mLeftMargins;
    private boolean mHasGetLeft = false;

    public IndicatorLineView(Context context) {
        this(context, null);
    }

    public IndicatorLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        if (attrs != null) {
            final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.IndicatorLineView, defStyleAttr, 0);
            mIndicatorColor = array.getColor(R.styleable.IndicatorLineView_indicatorColor, DEFAULT_COLOR);
            mIndicatorWidth = array.getDimension(R.styleable.IndicatorLineView_indicatorWidth, DEFAULT_WIDTH);
            mIndicatorHeight = array.getDimension(R.styleable.IndicatorLineView_indicatorHeight, DEFAULT_HEIGHT);
            array.recycle();
        }
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mIndicatorWidth);
        mPaint.setColor(mIndicatorColor);
        mIndicatorRectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw indicator line
        mIndicatorRectF.set(mLeft, getHeight() - mIndicatorHeight - DEFAULT_PADDING_BOTTOM, mLeft + mIndicatorWidth, getHeight() - DEFAULT_PADDING_BOTTOM);
        canvas.drawRect(mIndicatorRectF, mPaint);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        postIndicating(position, positionOffset);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setViewPager(ViewPager viewPager) {
        if (viewPager == null) {
            throw new NullPointerException("ViewPager is null.");
        }
        if (mViewPager == viewPager) {
            return;
        }
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter.");
        }
        if (mViewPager != null) {
            mViewPager.removeOnPageChangeListener(this);
        }
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(this);
    }

    private void setTabsLeftMargin(int count) {
        if (count < 1) {
            setVisibility(GONE);
        } else {
            mLeftMargins = new ArrayList<>(count);
            mTabAreaWidth = getWidth() / count;
            for (int i = 0; i < count; i++) {
                final float leftMargin = (mTabAreaWidth - mIndicatorWidth) / 2 + i * mTabAreaWidth;
                mLeftMargins.add(leftMargin);
            }
            setVisibility(VISIBLE);
            postIndicating(0, 0);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (!mHasGetLeft) {
            if (mViewPager != null) {
                setTabsLeftMargin(mViewPager.getAdapter().getCount());
                mHasGetLeft = true;
            }
        }
    }

    private void postIndicating(int position, float positionOffset) {
        if (null != mLeftMargins) {
            mLeft = mLeftMargins.get(position) + positionOffset * mTabAreaWidth;
            postInvalidate();
        }
    }

    public void destroy() {
        if (mViewPager != null) {
            mViewPager.removeOnPageChangeListener(this);
        }
    }

}
