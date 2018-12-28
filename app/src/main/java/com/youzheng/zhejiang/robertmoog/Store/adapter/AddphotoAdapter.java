package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.R;

import java.util.List;

public class AddphotoAdapter extends BaseAdapter {
    private List<Integer> list;
    private Context context;
    private LayoutInflater layoutInflater;


    public AddphotoAdapter(List<Integer> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.item_photo,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_photo=convertView.findViewById(R.id.iv_photo);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if (position < list.size()) {
            viewHolder.iv_photo.setImageResource(list.get(position));
        }else{
            viewHolder.iv_photo.setImageResource(R.mipmap.group_44_1);//最后一个显示加号图片
        }

        return convertView;
    }
    class ViewHolder{
       private ImageView iv_photo;
    }
}
