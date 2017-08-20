package com.zhangwx.mainpage.indicator;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zhangwx.mainpage.R;

/**
 * Created by zhangweixiong on 2017/8/6.
 */

public class TabItemView extends FrameLayout {

    public static final String TAG = "TabItemView";
    private AppCompatImageView mItemImage;
    private TextView mItemUnread;

    public TabItemView(Context context) {
        this(context, null);
    }

    public TabItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.tab_item_layout, this, true);
        mItemImage = (AppCompatImageView) view.findViewById(R.id.tab_image);
        mItemUnread = (TextView) view.findViewById(R.id.tab_unread);
    }

    public void setItemImage(int itemImageID) {
        if (mItemImage == null) {
            Log.e(TAG, "setItemImage: " + "mItemImage == null");
            return;
        }
        mItemImage.setImageResource(itemImageID);
    }

    public void updateUnread(int count) {
        if (mItemUnread == null) {
            return;
        }
        if (count == 0) {
            mItemUnread.setVisibility(GONE);
            return;
        }
        String number = String.valueOf(count);
        if (count > 99) {
            number = "99+";
        }
        mItemUnread.setVisibility(VISIBLE);
        mItemUnread.setText(number);
        invalidate();
    }
}
