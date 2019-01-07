package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.bean.CouponRecord;
import com.youzheng.zhejiang.robertmoog.Store.bean.CustomerList;
import com.youzheng.zhejiang.robertmoog.Store.listener.OnRecyclerViewAdapterItemClickListener;

import java.util.List;

public class CouponRecordAdapter extends RecyclerView.Adapter<CouponRecordAdapter.ViewHolder> {
    private List<CouponRecord.OrderMonthDataListBean> list;
    private LayoutInflater layoutInflater;
    private Context context;
    private OnRecyclerViewAdapterItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnRecyclerViewAdapterItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public CouponRecordAdapter(List<CouponRecord.OrderMonthDataListBean> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    public void setListRefreshUi(List<CouponRecord.OrderMonthDataListBean> list){
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=layoutInflater.inflate(R.layout.item_store_customer_list,viewGroup,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = viewHolder.getLayoutPosition();
                //设置监听
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view ,position );
                }

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        CouponRecord.OrderMonthDataListBean bean=list.get(position);
        viewHolder.tv_date.setText(bean.getMonth());
        viewHolder.tv_number.setText(bean.getNumPrice()+"");
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



   public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_date,tv_number;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           tv_date=itemView.findViewById(R.id.tv_date);
           tv_number=itemView.findViewById(R.id.tv_number);
       }
   }
}
