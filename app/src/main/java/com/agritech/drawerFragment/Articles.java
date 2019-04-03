package com.agritech.drawerFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agritech.R;
import com.agritech.adapter.ViewPagerAdapter;
import com.agritech.articleFragment.AnimalProduction;
import com.agritech.articleFragment.CropProduction;
import com.agritech.articleFragment.Trend;

public class Articles extends Fragment {
    public Articles() { }

    View view;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.articles_fragment, container, false);
        viewPager = view.findViewById(R.id.view_pager);
        tabLayout = view.findViewById(R.id.tab_layout);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new CropProduction(), "Crop Production");
        adapter.addFragment(new AnimalProduction(), "Animal Production");
        adapter.addFragment(new Trend(), "Trends");

        viewPager.setAdapter(adapter);

        return view;
    }
}
