package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.bean.CouponDetail;
import com.youzheng.zhejiang.robertmoog.Store.bean.StoreCustomerDetail;

import java.util.List;

public class CouponDetailAdapter extends RecyclerView.Adapter<CouponDetailAdapter.InsideHolder> {
      private List<CouponDetail.CouponUsageRecordDetailBean> list;
      private LayoutInflater layoutInflater;
      private Context context;

    public CouponDetailAdapter(List<CouponDetail.CouponUsageRecordDetailBean> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    public  void refreshUI(List<CouponDetail.CouponUsageRecordDetailBean> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @Override
    public InsideHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.item_coupon_detail,parent,false);
        InsideHolder insideHolder=new InsideHolder(view);
        return insideHolder;
    }

    @Override
    public void onBindViewHolder(InsideHolder holder, int position) {
        CouponDetail.CouponUsageRecordDetailBean bean=list.get(position);
        holder.tv_date.setText(bean.getDate());
        holder.tv_code.setText(bean.getOrderCode());
        holder.tv_cut_money.setText(bean.getMoney());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class InsideHolder extends RecyclerView.ViewHolder {
        private TextView tv_date,tv_code,tv_cut_money;

       public InsideHolder(View itemView) {
           super(itemView);
           tv_date=itemView.findViewById(R.id.tv_date);
           tv_code=itemView.findViewById(R.id.tv_code);
           tv_cut_money=itemView.findViewById(R.id.tv_cut_money);
       }
   }
}
