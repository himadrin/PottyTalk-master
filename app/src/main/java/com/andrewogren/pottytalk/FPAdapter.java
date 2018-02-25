package com.andrewogren.pottytalk;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.ArrayList;

/**
 * Created by andrewogren on 1/13/18.
 */

public class FPAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments;
    public FPAdapter(FragmentManager fm, ArrayList<Fragment> fragments){
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Search";
        }
        else if (position == 1) {
            return "Best Nearby";
        }
        else if (position == 2) {
            return "Profile";
        } else {
            return null;
        }
    }
}

