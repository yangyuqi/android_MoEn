package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.List;

public class GoodsPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private String[] titles = new String[]{"全部", "龙头", "水槽", "马桶", "浴室柜", "实木衣柜", "窗帘"};

    public GoodsPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    //重写这个方法，将设置每个Tab的标题，避免tab中文字不显示
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
