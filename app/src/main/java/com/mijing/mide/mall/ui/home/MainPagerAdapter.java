package com.mijing.mide.mall.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mijing.mide.mall.ui.home.main.HomeFragment;
import com.mijing.mide.mall.ui.home.main.HomeFragment2;
import com.mijing.mide.mall.ui.home.subject.SubjectFragment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private List<String> data = Arrays.asList("首页", "服饰", "母婴", "男装", "美食", "家居", "电器", "运动");

    HomeFragment mainFragment;
    Map<String, SubjectFragment> subjectFragmentMap = new HashMap<>();

    //
    HomeFragment2 mainFragment2;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            if (mainFragment == null) {
                mainFragment = new HomeFragment();
            }
            return mainFragment;
        }else if(position == 2){
            if (mainFragment2 == null) {
                mainFragment2 = new HomeFragment2();
            }
            return mainFragment2;
        }

        else {
            String title = data.get(position);
            if (!subjectFragmentMap.containsKey(title)) {
                SubjectFragment fragment = SubjectFragment.newInstance(position, title);
                subjectFragmentMap.put(title, fragment);
            }
            return subjectFragmentMap.get(title);
        }

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position);
    }
}
