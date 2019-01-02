package com.youzheng.zhejiang.robertmoog.Count.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.R;

import java.util.List;

public class CheckRuleAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private int select;


    public CheckRuleAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    public void setSelectItem(int selectItem) {
        this.select = selectItem;
        notifyDataSetChanged();
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
            convertView=layoutInflater.inflate(R.layout.item_rule,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_yes=convertView.findViewById(R.id.iv_yes);
            viewHolder.tv_rule=convertView.findViewById(R.id.tv_rule);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_rule.setText(list.get(position));

        final ViewHolder finalViewHolder = viewHolder;


        if (select==position){
            viewHolder.tv_rule.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            viewHolder.iv_yes.setVisibility(View.VISIBLE);
        }else {
            viewHolder.tv_rule.setTextColor(context.getResources().getColor(R.color.text_main));
            viewHolder.iv_yes.setVisibility(View.GONE);
        }


        return convertView;
    }
    class ViewHolder{
       private ImageView iv_yes;
       private TextView tv_rule;
    }
}
