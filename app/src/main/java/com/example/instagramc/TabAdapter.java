package com.example.instagramc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class TabAdapter extends FragmentPagerAdapter {

    public TabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public TabAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int tabPosition) {

        switch (tabPosition){

            case 0:
                ProfileTab profileTab = new ProfileTab();
                return profileTab;

            case 1:
                return new UsersTab();

            case 2:
                return new SharePictureTab();

            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return 3;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){

            case 0 :
                return "Profile";

            case 1:
                return "Users";
            case 2:
                return "Share Picture";

            default:
                return null;

        }


    }
}
