package com.youzheng.zhejiang.robertmoog.Home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.youzheng.zhejiang.robertmoog.Model.Home.BannerImageDataBean;
import com.youzheng.zhejiang.robertmoog.R;


import java.util.List;

/**
 * Created by qiuweiyu on 2018/2/7.
 */

public class BannerNormalAdapter extends StaticPagerAdapter {

    private List<BannerImageDataBean> banner_date;
    private String accessToken ;

    public BannerNormalAdapter(List<BannerImageDataBean> entity, String accessToken) {
        banner_date = entity;
        this.accessToken = accessToken ;
    }

    @Override
    public View getView(final ViewGroup container, final int position) {
        View new_view = LayoutInflater.from(container.getContext()).inflate(R.layout.image_new_layout,null);
        ImageView view = (ImageView) new_view.findViewById(R.id.iv_new);
        Glide.with(container.getContext()).load(banner_date.get(position).getBannerImageUrl()).into(view);
        return new_view;
    }


    @Override
    public int getCount() {
        return banner_date.size();
    }

}