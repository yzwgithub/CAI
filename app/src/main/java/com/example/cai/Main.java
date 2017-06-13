package com.example.cai;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import adapter.SectionsPagerAdapter;
import fragement.CatagoryFragment;
import fragement.DiscoverFragment;
import fragement.HomeFragment;
import fragement.UserCentralFragment;

/**
 * Created by ASUS on 2017/6/13.
 */

public class Main extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener,ViewPager.OnPageChangeListener {

    BottomNavigationBar bottomNavigationBar;
    ViewPager viewPager;
    private List<Fragment> fragments;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.main);
        initBottonNavigation();
        initViewPager();
    }

    @Override
    public void onTabSelected(int position) {

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setCurrentItem(position);

}

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        bottomNavigationBar= (BottomNavigationBar) findViewById(R.id.buttom_bar);
        bottomNavigationBar.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void initBottonNavigation(){
        bottomNavigationBar= (BottomNavigationBar) findViewById(R.id.buttom_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_DEFAULT);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.home,"HOME")).setActiveColor("#ECECEC")
                .addItem(new BottomNavigationItem(R.drawable.book,"BOOK")).setActiveColor("#ECECEC")
                .addItem(new BottomNavigationItem(R.drawable.entry,"ENTRY")).setActiveColor("#ECECEC")
                .addItem(new BottomNavigationItem(R.drawable.mine,"MINE")).setActiveColor("#ECECEC")
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    private void initViewPager() {

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        fragments = new ArrayList<Fragment>();
        fragments.add(new HomeFragment());
        fragments.add(new CatagoryFragment());
        fragments.add(new DiscoverFragment());
        fragments.add(new UserCentralFragment());

        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(0);
    }

}
