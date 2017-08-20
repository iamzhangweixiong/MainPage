package com.zhangwx.mainpage.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class DrawUtil {
	private static final float MIN_FLOAT_EQ = 0.00001f;
	private static int sScreenWidth, sScreenHeight, sStatusBarHeight;
	private static float sScale = 0.0f;

	public static int dip2px(Context context, float dipValue) {
		if (Math.abs(sScale) < MIN_FLOAT_EQ) {
			sScale = context.getResources().getDisplayMetrics().density;
		}
		return (int) (dipValue * sScale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		if (Math.abs(sScale) < MIN_FLOAT_EQ) {
			sScale = context.getResources().getDisplayMetrics().density;
		}
		return (int) (pxValue / sScale + 0.5f);
	}

	public static float dip2pxF(Context context, float dipValue) {
		if (Math.abs(sScale) < MIN_FLOAT_EQ) {
			sScale = context.getResources().getDisplayMetrics().density;
		}
		return dipValue * sScale;
	}

	public static float px2dipF(Context context, float pxValue) {
		if (Math.abs(sScale) < MIN_FLOAT_EQ) {
			sScale = context.getResources().getDisplayMetrics().density;
		}
		return pxValue / sScale;
	}

	public static int getScreenWidth(Context context) {
		if (sScreenWidth == 0 || sScreenHeight == 0) {
			DisplayMetrics mDm = context.getResources().getDisplayMetrics();
			sScreenWidth = mDm.widthPixels;
			sScreenHeight = mDm.heightPixels;
		}
		return sScreenWidth;
	}

	public static int getScreenHeight(Context context) {
		if (sScreenWidth == 0 || sScreenHeight == 0) {
			DisplayMetrics mDm = context.getResources().getDisplayMetrics();
			sScreenWidth = mDm.widthPixels;
			sScreenHeight = mDm.heightPixels;
		}
		return sScreenHeight;
	}

	public static float accele(float startS, float endS, long totalTime,
			long currTime) {
		if (currTime < 0) {
			return endS;
		} else if (currTime > totalTime) {
			return endS;
		}
		final float accele = (endS - startS) * 2 / (totalTime * totalTime);
		return startS + accele * currTime * (currTime >> 1);
	}


	public static int getStatusBarHeight(Context context) {
		if (sStatusBarHeight != -1) {
			return sStatusBarHeight;
		}

		//这个方法更快
		try {
			sStatusBarHeight = Resources.getSystem().getDimensionPixelSize(
					Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return sStatusBarHeight;
	}
}
