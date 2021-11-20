package com.example.p4_lamzone_mareu.ui.list;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MeetingListPagerAdapter extends FragmentPagerAdapter {

    public MeetingListPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return MeetingFragment.newInstance();
    }

    /**
     * get the number of pages
     * @return int
     */
    @Override
    public int getCount() {
        return 1;
    }
}
