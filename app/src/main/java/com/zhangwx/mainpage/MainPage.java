package com.zhangwx.mainpage;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import com.zhangwx.mainpage.indicator.IconPageIndicator;
import com.zhangwx.mainpage.view.IndicatorLineView;
import com.zhangwx.mainpage.view.IndicatorView2;

public class MainPage extends FragmentActivity implements ViewPager.OnPageChangeListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    IconPageIndicator indicator;

    IndicatorView2 indicatorView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ActionBarUIFixTool.fixMutilSysBar(this);

        setContentView(R.layout.activity_main_page);
//        SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
//        systemBarTintManager.setStatusBarTintEnabled(true);
//        systemBarTintManager.setStatusBarTintColor(R.color.statusbar_bg);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
//
        indicator = (IconPageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);

//        LinePageIndicator indicator = (LinePageIndicator)findViewById(R.id.indicator);
//        indicator.setViewPager(mViewPager);

//        开关
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
        IndicatorLineView indicatorView = (IndicatorLineView) findViewById(R.id.indicatorview);
        indicatorView.setViewPager(mViewPager);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        indicator.updateUnreadCount(position, position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
