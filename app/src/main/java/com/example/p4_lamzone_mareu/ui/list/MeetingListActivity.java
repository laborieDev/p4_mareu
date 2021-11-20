package com.example.p4_lamzone_mareu.ui.list;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.p4_lamzone_mareu.R;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeetingListActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.container)
    ViewPager mViewPager;

    MeetingListPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);
        ButterKnife.bind(this);

        mPagerAdapter = new MeetingListPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

    }

    //@OnClick(R.id.add_meeting)
    //void addNeighbour() {
        //AddNeighbourActivity.navigate(this);
    //}
}
