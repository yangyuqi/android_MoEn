package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.bean.CustomerList;
import com.youzheng.zhejiang.robertmoog.Store.bean.ProfessionalCustomerList;
import com.youzheng.zhejiang.robertmoog.Store.listener.OnRecyclerViewAdapterItemClickListener;

import java.util.List;

public class ProfessionalCustomerAdapter extends RecyclerView.Adapter<ProfessionalCustomerAdapter.InsideHolder> {
      private List<ProfessionalCustomerList.SpecialtyCustomerListBean> list;
      private LayoutInflater layoutInflater;
      private Context context;
      private OnRecyclerViewAdapterItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnRecyclerViewAdapterItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setListRefreshUi(List<ProfessionalCustomerList.SpecialtyCustomerListBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public ProfessionalCustomerAdapter(List<ProfessionalCustomerList.SpecialtyCustomerListBean> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public InsideHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.item_customer_inside,parent,false);
        final InsideHolder insideHolder=new InsideHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = insideHolder.getLayoutPosition();
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
                int position = insideHolder.getLayoutPosition();
                //设置监听
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemLongClick( v,position);
                }
                //true代表消费事件 不继续传递
                return true;
            }
        });

        return insideHolder;
    }

    @Override
    public void onBindViewHolder(InsideHolder holder, int position) {
        ProfessionalCustomerList.SpecialtyCustomerListBean bean=list.get(position);
        holder.tv_date.setText(bean.getRegisterDate());
        holder.tv_way.setText(bean.getSpecialtyType());
        holder.tv_phone.setText(bean.getCustCode());
        holder.tv_people.setText(bean.getCustName());
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
