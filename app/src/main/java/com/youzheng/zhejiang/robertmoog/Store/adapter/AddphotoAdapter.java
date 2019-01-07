package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.listener.OnRecyclerViewAdapterItemClickListener;

import java.util.List;

public class AddphotoAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnRecyclerViewAdapterItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnRecyclerViewAdapterItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public AddphotoAdapter(List<String> list, Context context) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.item_photo,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_photo=convertView.findViewById(R.id.iv_photo);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if (position == list.size()) {
            if (list.size()==9){
                viewHolder.iv_photo.setVisibility(View.GONE);
            }else {
                viewHolder.iv_photo.setImageResource(R.mipmap.group_44_1);//最后一个显示加号图片
                viewHolder.iv_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //设置监听
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick(view ,position);
                        }

                    }
                });
            }

        }else{
            Glide.with(context).load(list.get(position)).into(viewHolder.iv_photo);
        }



        return convertView;
    }
    class ViewHolder{
       private ImageView iv_photo;
    }
}
