package com.hawahuri.expensemanager.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class EiFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> fragmentname = new ArrayList<>();

    public EiFragmentAdapter( FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    public CharSequence getPageTitle(int position) {
        return fragmentname.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        fragmentname.add(title);
    }
}
