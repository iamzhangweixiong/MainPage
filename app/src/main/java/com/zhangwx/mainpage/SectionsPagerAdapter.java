package com.zhangwx.mainpage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhangwx.mainpage.indicator.IconPagerAdapter;

/**
 * Created by zhangweixiong on 2017/7/31.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter implements IconPagerAdapter {

    public static final int FRAGMENT_ONE_POS = 0;
    public static final int FRAGMENT_TWO_POS = 1;
    public static final int FRAGMENT_THREE_POS = 2;
    public static final int FRAGMENT_COUNT = 3;


    private static final int[] ICONS = new int[]{
            R.drawable.perm_group_calendar,
            R.drawable.perm_group_camera,
            R.drawable.perm_group_device_alarms,
            R.drawable.perm_group_location};

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case FRAGMENT_ONE_POS:
                return new FragmentOne();
            case FRAGMENT_TWO_POS:
                return new FragmentTwo();
            case FRAGMENT_THREE_POS:
                return new FragmentThree();
        }
        return null;
    }

    @Override
    public int getIconResId(int index) {
        return ICONS[index];
    }

//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return "lia";
//    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }
}
