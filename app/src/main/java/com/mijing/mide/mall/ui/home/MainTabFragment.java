package com.mijing.mide.mall.ui.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.mijing.mide.mall.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainTabFragment extends Fragment {

    @Bind(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    MainPagerAdapter homePagerAdapter;

    public MainTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        homePagerAdapter = new MainPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(homePagerAdapter);
        tabLayout.setViewPager(viewPager);
        viewPager.setOffscreenPageLimit(5);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
