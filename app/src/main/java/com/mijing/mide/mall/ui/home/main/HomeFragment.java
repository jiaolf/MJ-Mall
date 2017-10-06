package com.mijing.mide.mall.ui.home.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mijing.mide.mall.R;
import com.mijing.mide.mall.app.UI;
import com.mijing.mide.mall.view.RecyclerViewDivider;
import com.mijing.mide.mall.widget.autoscrollviewpager.AutoScrollViewPager;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/28.
 */

public class HomeFragment extends Fragment {

    @Bind(R.id.indicator)
    CirclePageIndicator indicator;
    @Bind(R.id.bannerPager)
    AutoScrollViewPager bannerPager;
    @Bind(R.id.recyclerView_home)
    RecyclerView recyclerViewHome;

    List<String> mDatas = new ArrayList<>();
    HomeProductAdapter mRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_main, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View rootView) {
        View header = rootView.findViewById(R.id.layout_home_main_header);
        HeaderViewHolder headerViewHolder = new HeaderViewHolder(header, getContext());

        //recyclerViewHome.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        RecyclerViewDivider dividerItemDecoration = new RecyclerViewDivider(getContext(), LinearLayoutManager.VERTICAL, UI.dip2px(8), Color.parseColor("#EEEEEE"));
        recyclerViewHome.addItemDecoration(dividerItemDecoration);
        recyclerViewHome.setHasFixedSize(true);
        recyclerViewHome.setItemAnimator(new DefaultItemAnimator());
        for (int i = 0; i < 30; i++) {
            mDatas.add("test product - " + i);
        }
        mRecyclerAdapter = new HomeProductAdapter(getContext(), mDatas);
        recyclerViewHome.setAdapter(mRecyclerAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
