package com.zhangwx.mainpage.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangwx.mainpage.R;

/**
 * Created by zhangweixiong on 2017/8/3.
 * <p>
 * app:navigation_image="@mipmap/ic_launcher"
 * app:right_image="@mipmap/ic_launcher_round"
 * app:title_text_color="@android:color/holo_green_light"
 * app:title_text="snsfgjn"
 * app:title_text_size="12sp"
 * app:margin_status_bar="true"
 */

public class CustomTopBar extends RelativeLayout implements View.OnClickListener {

    public static final String TAG = "CustomTopBar";

    public static final int defaultTextSize = 15;
    public static final int defaultTextColor = 0xFF616161;

    private TextView mTitleText;
    private ImageView mNavigationView;
    private ImageView mRightView;
    private Space mSpace;

    private ITopBarClickListener mTopBarClickListener;

    public CustomTopBar(Context context) {
        this(context, null);
    }

    public CustomTopBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTopBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        if (attrs != null) {
            final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomTopBar);
            final Drawable navImage = ta.getDrawable(R.styleable.CustomTopBar_navigation_image);
            if (navImage != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mNavigationView.setBackground(navImage);
                }
            }
            final Drawable rightImage = ta.getDrawable(R.styleable.CustomTopBar_right_image);
            if (rightImage != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mRightView.setBackground(rightImage);
                }
            }
            final CharSequence title = ta.getText(R.styleable.CustomTopBar_title_text);
            if (!TextUtils.isEmpty(title)) {
                mTitleText.setText(title);
            }
            final int textSize = ta.getDimensionPixelSize(R.styleable.CustomTopBar_title_text_size, defaultTextSize);
            if (textSize >= 0) {
                mTitleText.setTextSize(textSize);
            }
            if (ta.hasValue(R.styleable.CustomTopBar_title_text_color)) {
                final int textColor = ta.getColor(R.styleable.CustomTopBar_title_text_color, defaultTextColor);
                mTitleText.setTextColor(textColor);
            }
            final boolean showSpace = ta.getBoolean(R.styleable.CustomTopBar_margin_status_bar, false);
            mSpace.setVisibility(showSpace ? VISIBLE : GONE);
            ta.recycle();
        }
    }

    private void initView() {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_top_bar_layout, this, true);
        mNavigationView = (ImageView) view.findViewById(R.id.top_bar_navigation);
        mTitleText = (TextView) view.findViewById(R.id.top_bar_title);
        mRightView = (ImageView) view.findViewById(R.id.top_bar_right_image);
        mSpace = (Space) view.findViewById(R.id.top_bar_margin_top);
        mNavigationView.setOnClickListener(this);
        mRightView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_navigation:
                if (mTopBarClickListener != null) {
                    mTopBarClickListener.onNavigationViewClick();
                }
                break;
            case R.id.top_bar_right_image:
                if (mTopBarClickListener != null) {
                    mTopBarClickListener.onRightViewClick();
                }
                break;
        }
    }

    public interface ITopBarClickListener {
        void onNavigationViewClick();

        void onRightViewClick();
    }

    public void setTopBarClickListener(ITopBarClickListener listener) {
        this.mTopBarClickListener = listener;
    }
}
