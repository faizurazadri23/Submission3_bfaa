package com.faizurazadri.submission3_bfaa.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.faizurazadri.submission3_bfaa.R;
import com.faizurazadri.submission3_bfaa.fragment.FollowerFragment;
import com.faizurazadri.submission3_bfaa.fragment.FollowingFragment;

public class AdapterPage extends FragmentPagerAdapter {

    private final Context context;

    public AdapterPage(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    private final int[] TAB=new int[]{
            R.string.pengikut,
            R.string.diikuti
    };

    public AdapterPage(@NonNull FragmentManager fm, int behavior, Context context) {
        super(fm, behavior);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment =null;
        switch (position){
            case 0:
                fragment = new FollowerFragment();
                break;

            case 1:
                fragment = new FollowingFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return TAB.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB[position]);
    }
}
