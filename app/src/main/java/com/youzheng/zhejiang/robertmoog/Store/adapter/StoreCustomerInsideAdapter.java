package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.bean.StoreCustomerDetail;

import java.util.List;

public class StoreCustomerInsideAdapter extends RecyclerView.Adapter<StoreCustomerInsideAdapter.InsideHolder> {
      private List<StoreCustomerDetail.MonthCoustomerDetailBean> list;
      private LayoutInflater layoutInflater;
      private Context context;

    public StoreCustomerInsideAdapter(List<StoreCustomerDetail.MonthCoustomerDetailBean> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    public  void refreshUI(List<StoreCustomerDetail.MonthCoustomerDetailBean> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @Override
    public InsideHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.item_customer_inside,parent,false);
        InsideHolder insideHolder=new InsideHolder(view);
        return insideHolder;
    }

    @Override
    public void onBindViewHolder(InsideHolder holder, int position) {
        StoreCustomerDetail.MonthCoustomerDetailBean monthCoustomerDetailBean=list.get(position);
        holder.tv_date.setText(monthCoustomerDetailBean.getRegisterDate());
        holder.tv_way.setText(monthCoustomerDetailBean.getSourceChannelEnum());
        holder.tv_phone.setText(monthCoustomerDetailBean.getAccount());
        holder.tv_people.setText(monthCoustomerDetailBean.getCustomerType()+":"+monthCoustomerDetailBean.getUpdateUserName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class InsideHolder extends RecyclerView.ViewHolder {
        private TextView tv_date,tv_way,tv_phone,tv_people;

       public InsideHolder(View itemView) {
           super(itemView);
           tv_date=itemView.findViewById(R.id.tv_date);
           tv_way=itemView.findViewById(R.id.tv_way);
           tv_phone=itemView.findViewById(R.id.tv_phone);
           tv_people=itemView.findViewById(R.id.tv_people);
       }
   }
}
