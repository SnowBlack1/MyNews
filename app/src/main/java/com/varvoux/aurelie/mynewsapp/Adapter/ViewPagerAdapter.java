package com.varvoux.aurelie.mynewsapp.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.varvoux.aurelie.mynewsapp.fragments.MostPopularFragment;
import com.varvoux.aurelie.mynewsapp.fragments.TopStoriesFragment;
import com.varvoux.aurelie.mynewsapp.fragments.TravelFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TopStoriesFragment.newInstance();
            case 1:
                return MostPopularFragment.newInstance();
            case 2:
                return TravelFragment.newInstance();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Top Stories";
            case 1:
                return "Most Popular";
            case 2:
                return "Travel";
            default:
                return null;
        }
    }
}
