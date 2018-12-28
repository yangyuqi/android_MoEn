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
import com.youzheng.zhejiang.robertmoog.Store.bean.GoodsList;

import java.util.List;

public class GoodsSearchAdapter extends BaseAdapter {
    private List<GoodsList.ProductListDetailDataBean> list;
    private Context context;
    private LayoutInflater layoutInflater;


    public GoodsSearchAdapter(List<GoodsList.ProductListDetailDataBean> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }
    public void  setRefreshUI(List<GoodsList.ProductListDetailDataBean> list){
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
            convertView=layoutInflater.inflate(R.layout.item_goods,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_goods=convertView.findViewById(R.id.iv_goods);
            viewHolder. tv_goods_name=convertView.findViewById(R.id.tv_goods_name);
            viewHolder.tv_goods_content=convertView.findViewById(R.id.tv_goods_content);
            viewHolder.tv_goods_money=convertView.findViewById(R.id.tv_goods_money);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        GoodsList.ProductListDetailDataBean bean=list.get(position);
        Glide.with(context).load(bean.getSmallImageUrl()).into(viewHolder.iv_goods);
        viewHolder.tv_goods_name.setText(bean.getSkuId());
        viewHolder.tv_goods_content.setText(bean.getName());
        viewHolder.tv_goods_money.setText("￥"+bean.getPrice());

        return convertView;
    }
    class ViewHolder{
        private ImageView iv_goods;
        private TextView tv_goods_name,tv_goods_content,tv_goods_money;
    }
}
