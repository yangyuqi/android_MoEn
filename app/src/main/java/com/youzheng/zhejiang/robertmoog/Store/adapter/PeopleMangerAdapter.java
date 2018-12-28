package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.R;

import java.util.List;

public class PeopleMangerAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    private LayoutInflater layoutInflater;


    public PeopleMangerAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
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
            convertView=layoutInflater.inflate(R.layout.item_people,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_phone=convertView.findViewById(R.id.tv_phone);
            viewHolder.tv_manger=convertView.findViewById(R.id.tv_manger);
            viewHolder.tv_name=convertView.findViewById(R.id.tv_name);
            viewHolder.tv_stop=convertView.findViewById(R.id.tv_stop);
            viewHolder.iv_manger=convertView.findViewById(R.id.iv_manger);
            viewHolder.iv_already_stop=convertView.findViewById(R.id.iv_already_stop);
            viewHolder.lin_name=convertView.findViewById(R.id.lin_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        //viewHolder.tv_phone.setText(list.get(position));
        viewHolder.tv_manger.setText(list.get(position));

        return convertView;
    }


    class ViewHolder{
       private TextView tv_phone,tv_manger,tv_name,tv_stop;
       private ImageView iv_manger,iv_already_stop;
       private LinearLayout lin_name;
    }
}
