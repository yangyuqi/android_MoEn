package com.youzheng.zhejiang.robertmoog.Count.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Count.activity.GoodsTypeRankingDetailActivity;
import com.youzheng.zhejiang.robertmoog.Count.bean.GoodsTypeRankingList;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.listener.OnRecyclerViewAdapterItemClickListener;

import java.util.List;

public class GoodsTypeRankingAdapter extends RecyclerView.Adapter<GoodsTypeRankingAdapter.SaleHolder> {
    private List<GoodsTypeRankingList.CategoryListBean> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnRecyclerViewAdapterItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnRecyclerViewAdapterItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public GoodsTypeRankingAdapter(List<GoodsTypeRankingList.CategoryListBean> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    public void setUI(List<GoodsTypeRankingList.CategoryListBean> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SaleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view=layoutInflater.inflate(R.layout.item_goods_type_ranking,viewGroup,false);
        final SaleHolder saleHolder=new SaleHolder(view);

        saleHolder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = saleHolder.getLayoutPosition();
                //设置监听
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view ,position );
                }

            }
        });
        return saleHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SaleHolder saleHolder, int position) {
        final GoodsTypeRankingList.CategoryListBean bean=list.get(position);
        saleHolder.tv_name.setText(bean.getCategoryName());
        saleHolder.tv_name.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
        saleHolder.tv_name.getPaint().setAntiAlias(true);//抗锯齿

        saleHolder.tv_order_value.setText(bean.getCount());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SaleHolder extends RecyclerView.ViewHolder{

        private TextView tv_name,tv_order_value;

        public SaleHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_order_value=itemView.findViewById(R.id.tv_order_value);
        }


    }

}
