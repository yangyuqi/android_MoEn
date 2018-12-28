package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.bean.GoodsType;

import java.util.List;

public class GoodsTitleAdapter extends BaseAdapter {
    private List<GoodsType.ListDataBean> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private int selectItem =0;

    public GoodsTitleAdapter(List<GoodsType.ListDataBean> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    public void refresgUI(List<GoodsType.ListDataBean> list){
        this.list=list;
        notifyDataSetChanged();
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
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
            convertView=layoutInflater.inflate(R.layout.item_title,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.tv_title=convertView.findViewById(R.id.tv_title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_title.setText(list.get(position).getName());

        if (selectItem==position){
            viewHolder.tv_title.setSelected(true);
            viewHolder.tv_title.setBackgroundResource(R.drawable.titlle_on);
            viewHolder.tv_title.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }else {
            viewHolder.tv_title.setSelected(false);
            viewHolder.tv_title.setBackgroundResource(R.drawable.title_off);
            viewHolder.tv_title.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }

        return convertView;
    }
    class ViewHolder{
        private TextView tv_title;
    }
}
