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

public class CheckStoreDetailAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public CheckStoreDetailAdapter(List<String> list, Context context) {
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
            convertView=layoutInflater.inflate(R.layout.item_result_detail,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_content=convertView.findViewById(R.id.tv_content);
            viewHolder.tv_yes_or_no=convertView.findViewById(R.id.tv_yes_or_no);
            viewHolder.iv_ok=convertView.findViewById(R.id.iv_ok);
            viewHolder.iv_no=convertView.findViewById(R.id.iv_no);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_content.setText(list.get(position));


        return convertView;
    }
    class ViewHolder{
       private TextView tv_content,tv_yes_or_no;
       private ImageView iv_ok,iv_no;
    }
}
