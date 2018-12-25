package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.support.annotation.IntRange;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.youzheng.zhejiang.robertmoog.R;

import java.util.List;

public class MoreGoodAdapter extends BaseAdapter {
    private List<Integer> piclist;
    private Context context;
    private LayoutInflater layoutInflater;

    public MoreGoodAdapter(List<Integer> piclist, Context context) {
        this.piclist = piclist;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return piclist.size();
    }

    @Override
    public Object getItem(int position) {
        return piclist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.item_order_pic,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_pic=convertView.findViewById(R.id.iv_pic);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.iv_pic.setImageResource(piclist.get(position));

        return convertView;
    }
    class ViewHolder{
       private ImageView iv_pic;
    }
}
