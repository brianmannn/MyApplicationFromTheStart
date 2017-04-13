package com.crazyhands.myapplicationfromthestart.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.crazyhands.myapplicationfromthestart.bookableFragments.AllBookablesFragment;
import com.crazyhands.myapplicationfromthestart.bookableFragments.NewAllBookablesFragment;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] { "all bookables"};
    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new AllBookablesFragment();
        } else if (position == 1){
            return new NewAllBookablesFragment();
        } else return null;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
    @Override
    public int getCount() {
        return 2;
    }
}
