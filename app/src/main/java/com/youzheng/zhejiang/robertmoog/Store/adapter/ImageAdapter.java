package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youzheng.zhejiang.robertmoog.R;

import java.util.List;

/**
 * 图片浏览器适配器
 */
public class ImageAdapter extends PagerAdapter {
   private List<String> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public ImageAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//必须实现
        View view =layoutInflater.inflate(R.layout.item_image,container,false);
        ImageView imageView=view.findViewById(R.id.iv_image);
        Glide.with(context).load(list.get(position)).into(imageView);
        container.addView(view);
        return view;
    }

}
