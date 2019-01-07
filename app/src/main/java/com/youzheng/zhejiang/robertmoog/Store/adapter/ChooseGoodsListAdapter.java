package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.bean.OrderList;
import com.youzheng.zhejiang.robertmoog.Store.listener.OnRecyclerViewAdapterItemClickListener;

import java.util.List;

public class ChooseGoodsListAdapter extends RecyclerView.Adapter {
    private List<OrderList> list;
    private List<Integer> piclist;
    private Context context;
    private LayoutInflater layoutInflater;
    public static final int TYPE_ONE_IMAGE = 0;
    public static final int TYPE_MORE_IMAGE = 1;
    private OnRecyclerViewAdapterItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnRecyclerViewAdapterItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public ChooseGoodsListAdapter(List<OrderList> list, List<Integer> piclist, Context context) {
        this.list = list;
        this.piclist=piclist;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        OrderList orderList = list.get(position);
        if (orderList.type == 0) {
            return TYPE_ONE_IMAGE;
        } else {
            return TYPE_MORE_IMAGE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE_IMAGE) {
            View view = layoutInflater.inflate(R.layout.item_choose_orderlist, parent,false);
            final OneImageHolder oneImageHolder=new OneImageHolder(view);

            return oneImageHolder;
        } else {
            View view = layoutInflater.inflate(R.layout.item_choose_order_style, parent,false);
            final MoreImageHolder moreImageHolder=new MoreImageHolder(view);
            
            return moreImageHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OneImageHolder) {
            setOneImageData((OneImageHolder) holder,position);
        } else {
            setMoreImageData((MoreImageHolder) holder,position);
        }
    }

    private void setMoreImageData(MoreImageHolder holder,int position) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.mRvListPic.setLayoutManager(linearLayoutManager);
        MoreGoodsAdapter moreGoodsAdapter=new MoreGoodsAdapter(piclist,context);
        holder.mRvListPic.setAdapter(moreGoodsAdapter);
        moreGoodsAdapter.notifyDataSetChanged();
//        holder.mTvDate.setText(list.get(position).getText());
//        holder.mTvOrderNum.setText(list.get(position).getText());
//        holder.mTvCount.setText(list.get(position).getText());
//        holder.mTvMoney.setText(list.get(position).getText());

    }

    private void setOneImageData(OneImageHolder holder,int position) {
        holder.tv_date.setText(list.get(position).getText());
        holder.tv_order_num.setText(list.get(position).getText());
        holder.iv_goods.setImageResource(list.get(position).getPic());
        holder.tv_goods_number.setText(list.get(position).getText());
        //holder.tv_goods_content.setText(list.get(position).getText());
        holder.tv_count.setText(list.get(position).getText());
        holder.tv_money.setText(list.get(position).getText());
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    private class OneImageHolder extends RecyclerView.ViewHolder {
        TextView tv_date;
        TextView tv_order_num;
        ImageView iv_goods;
        TextView tv_goods_number;
        TextView tv_goods_content;
        TextView tv_count;
        TextView tv_money;

        TextView tv_return_all;
        TextView tv_return_partial;


        public OneImageHolder(View itemView) {
            super(itemView);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_order_num = itemView.findViewById(R.id.tv_order_num);
            iv_goods = itemView.findViewById(R.id.iv_goods);
            tv_goods_number = itemView.findViewById(R.id.tv_goods_number);
            tv_goods_content = itemView.findViewById(R.id.tv_goods_content);
            tv_count = itemView.findViewById(R.id.tv_count);
            tv_money = itemView.findViewById(R.id.tv_money);
            tv_return_all=itemView.findViewById(R.id.tv_return_all);
            tv_return_partial=itemView.findViewById(R.id.tv_return_partial);
        }
    }

    private class MoreImageHolder extends RecyclerView.ViewHolder {
        TextView mTvDate;
        TextView mTvOrderNum;
        RecyclerView mRvListPic;
        TextView mTvCount;
        TextView mTvMoney;
        TextView tv_return_all;
        TextView tv_return_partial;
        public MoreImageHolder(View itemView) {
            super(itemView);
            mTvDate = itemView.findViewById(R.id.tv_date);
            mTvOrderNum = itemView.findViewById(R.id.tv_order_num);
            mRvListPic = itemView.findViewById(R.id.rv_list_pic);
            mTvCount = itemView.findViewById(R.id.tv_count);
            mTvMoney = itemView.findViewById(R.id.tv_money);
            tv_return_all=itemView.findViewById(R.id.tv_return_all);
            tv_return_partial=itemView.findViewById(R.id.tv_return_partial);
        }
    }


}
