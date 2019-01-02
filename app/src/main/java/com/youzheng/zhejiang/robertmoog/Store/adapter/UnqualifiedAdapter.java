package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.bean.UnqualifiedContent;

import java.util.List;

public class UnqualifiedAdapter extends BaseAdapter {
    private List<UnqualifiedContent.PatrolShopProblemDetailBean.QuestionImagesBean> list;
    private Context context;
    private LayoutInflater layoutInflater;


    public UnqualifiedAdapter(List<UnqualifiedContent.PatrolShopProblemDetailBean.QuestionImagesBean> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    public void setPic(List<UnqualifiedContent.PatrolShopProblemDetailBean.QuestionImagesBean> list){
        this.list=list;
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
            convertView=layoutInflater.inflate(R.layout.item_photo,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_photo=convertView.findViewById(R.id.iv_photo);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getImageImgUrl()).into(viewHolder.iv_photo);
        return convertView;
    }
    class ViewHolder{
       private ImageView iv_photo;
    }
}
