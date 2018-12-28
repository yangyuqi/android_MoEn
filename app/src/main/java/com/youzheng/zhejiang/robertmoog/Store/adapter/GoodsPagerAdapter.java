package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.youzheng.zhejiang.robertmoog.Store.bean.GoodsType;

import java.util.List;

public class GoodsPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
//    private String[] titles = new String[]{"全部", "龙头", "水槽", "马桶", "浴室柜", "实木衣柜", "窗帘"};
    private List<GoodsType.ListDataBean> titlelist;

    public GoodsPagerAdapter(FragmentManager fm, List<Fragment> list,List<GoodsType.ListDataBean> titlelist) {
        super(fm);
        this.list = list;
        this.titlelist=titlelist;
    }

    public void setUI(List<GoodsType.ListDataBean> titlelist){
        this.titlelist=titlelist;
        notifyDataSetChanged();
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
        return titlelist.get(position).getName();
    }
}
