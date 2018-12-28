package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.bean.GoodsList;
import com.youzheng.zhejiang.robertmoog.Store.listener.OnRecyclerViewAdapterItemClickListener;

import java.util.List;

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.GoodsHolder> {
    private List<GoodsList.ProductListDetailDataBean> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnRecyclerViewAdapterItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnRecyclerViewAdapterItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public GoodsListAdapter(List<GoodsList.ProductListDetailDataBean> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    public void  setRefreshUI(List<GoodsList.ProductListDetailDataBean> list){
        this.list=list;
        notifyDataSetChanged();

    }

    @Override
    public GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.item_goods,parent,false);
        final GoodsHolder goodsHolder=new GoodsHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = goodsHolder.getLayoutPosition();
                //设置监听
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view ,position );
                }

            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //自己获取position
                int position = goodsHolder.getLayoutPosition();
                //设置监听
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemLongClick( v,position);
                }
                //true代表消费事件 不继续传递
                return true;
            }
        });


        return goodsHolder;
    }

    @Override
    public void onBindViewHolder(GoodsHolder holder, int position) {
        GoodsList.ProductListDetailDataBean bean=list.get(position);
        Glide.with(context).load(bean.getSmallImageUrl()).into(holder.iv_goods);
        holder.tv_goods_name.setText(bean.getSkuId());
        holder.tv_goods_content.setText(bean.getName());
        holder.tv_goods_money.setText("￥"+bean.getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GoodsHolder extends RecyclerView.ViewHolder {
        private ImageView iv_goods;
        private TextView tv_goods_name,tv_goods_content,tv_goods_money;
        public GoodsHolder(View itemView) {
            super(itemView);
            iv_goods=itemView.findViewById(R.id.iv_goods);
            tv_goods_name=itemView.findViewById(R.id.tv_goods_name);
            tv_goods_content=itemView.findViewById(R.id.tv_goods_content);
            tv_goods_money=itemView.findViewById(R.id.tv_goods_money);

        }
    }

}
