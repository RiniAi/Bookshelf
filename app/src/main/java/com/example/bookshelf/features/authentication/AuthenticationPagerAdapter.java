package com.example.bookshelf.features.authentication;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class AuthenticationPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    AuthenticationPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    void addFragment(Fragment fragment) {
        fragmentList.add(fragment);
    }
}
