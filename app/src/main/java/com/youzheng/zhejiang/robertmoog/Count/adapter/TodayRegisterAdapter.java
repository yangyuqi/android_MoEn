package com.youzheng.zhejiang.robertmoog.Count.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Count.bean.TodayRegisterList;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.bean.StoreCustomerDetail;

import java.util.List;

public class TodayRegisterAdapter extends RecyclerView.Adapter<TodayRegisterAdapter.InsideHolder> {
      private List<TodayRegisterList.CustomerListBean> list;
      private LayoutInflater layoutInflater;
      private Context context;

    public TodayRegisterAdapter(List<TodayRegisterList.CustomerListBean> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    public  void refreshUI(List<TodayRegisterList.CustomerListBean> list){
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
        TodayRegisterList.CustomerListBean bean=list.get(position);
        holder.tv_date.setText(bean.getCreateDate());
        holder.tv_way.setText(bean.getChannel());
        holder.tv_phone.setText(bean.getPhone());
        holder.tv_people.setText(bean.getOperatePersonal());
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
