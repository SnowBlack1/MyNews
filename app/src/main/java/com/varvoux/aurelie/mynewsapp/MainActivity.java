package com.varvoux.aurelie.mynewsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.varvoux.aurelie.mynewsapp.Adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureViewPager();
        this.configureViewPagerAndTabs();


    }

    private void configureViewPager(){
        ViewPager pager = findViewById(R.id.main_activity_viewpager);
        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }

    private void configureViewPagerAndTabs(){
        ViewPager pager = findViewById(R.id.main_activity_viewpager);
        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);

        tabs.setTabMode(TabLayout.MODE_FIXED);
    }
}
