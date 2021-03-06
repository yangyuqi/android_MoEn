package com.youzheng.zhejiang.robertmoog.Count.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Count.bean.MealRankingList;
import com.youzheng.zhejiang.robertmoog.R;

import java.util.List;

public class MealRankingAdapter extends RecyclerView.Adapter<MealRankingAdapter.SaleHolder> {
    private List<MealRankingList.SetMealListBean> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public MealRankingAdapter(List<MealRankingList.SetMealListBean> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    public void setUI(List<MealRankingList.SetMealListBean> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SaleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view=layoutInflater.inflate(R.layout.item_goods_sale,viewGroup,false);
        SaleHolder saleHolder=new SaleHolder(view);
        return saleHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SaleHolder saleHolder, int position) {
        MealRankingList.SetMealListBean bean=list.get(position);
        saleHolder.tv_goods_id.setText(bean.getComboCode());
        saleHolder.tv_goods_name.setText(bean.getComboDescribe());
        saleHolder.tv_goods_number.setText(bean.getNumOrPrice());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SaleHolder extends RecyclerView.ViewHolder{

        private TextView tv_goods_id,tv_goods_name,tv_goods_number;

        public SaleHolder(@NonNull View itemView) {
            super(itemView);
            tv_goods_id=itemView.findViewById(R.id.tv_goods_id);
            tv_goods_name=itemView.findViewById(R.id.tv_goods_name);
            tv_goods_number=itemView.findViewById(R.id.tv_goods_number);
        }


    }

}
