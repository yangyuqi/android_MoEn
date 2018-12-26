package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.listener.OnRecyclerViewAdapterItemClickListener;

import java.util.List;

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.GoodsHolder> {
    private List<String> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnRecyclerViewAdapterItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnRecyclerViewAdapterItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public GoodsListAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
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
        holder.iv_goods.setImageResource(R.mipmap.ic_launcher);
        holder.tv_goods_number.setText(list.get(position));
        holder.tv_goods_content.setText(list.get(position));
        holder.tv_goods_money.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GoodsHolder extends RecyclerView.ViewHolder {
        private ImageView iv_goods;
        private TextView tv_goods_number,tv_goods_content,tv_goods_money;
        public GoodsHolder(View itemView) {
            super(itemView);
            iv_goods=itemView.findViewById(R.id.iv_goods);
            tv_goods_number=itemView.findViewById(R.id.tv_goods_number);
            tv_goods_content=itemView.findViewById(R.id.tv_goods_content);
            tv_goods_money=itemView.findViewById(R.id.tv_goods_money);

        }
    }

}
