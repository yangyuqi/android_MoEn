package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.bean.PeopleMangerList;
import com.youzheng.zhejiang.robertmoog.Store.listener.OnRecyclerViewAdapterItemClickListener;

import java.util.List;

public class PeopleMangerAdapter extends RecyclerView.Adapter<PeopleMangerAdapter.ViewHolder> {
    private List<PeopleMangerList.ShopPersonalListBean> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnRecyclerViewAdapterItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnRecyclerViewAdapterItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    public PeopleMangerAdapter(List<PeopleMangerList.ShopPersonalListBean> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    public void setUI(List<PeopleMangerList.ShopPersonalListBean> list){
        this.list=list;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view=layoutInflater.inflate(R.layout.item_people,viewGroup,false);
        final ViewHolder viewHolder=new ViewHolder(view);


        viewHolder.tv_stop.setOnClickListener(new View.OnClickListener() {
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
        PeopleMangerList.ShopPersonalListBean bean=list.get(position);
        viewHolder.tv_phone.setText(bean.getPhone());
        viewHolder.tv_manger.setText(bean.getBusinessRole().getDes());
        viewHolder.tv_name.setText(bean.getName());

        if (bean.getBusinessRole().getId().equals("SHOPKEEPER")){
            viewHolder.iv_manger.setImageResource(R.mipmap.group_100_2);
        }else {
            viewHolder.iv_manger.setImageResource(R.mipmap.group_90_3);
        }

        if (bean.getStatus().getId().equals("Use")){
            viewHolder.iv_already_stop.setVisibility(View.GONE);
            viewHolder.tv_stop.setVisibility(View.VISIBLE);
        }else if (bean.getStatus().getId().equals("Stop")){
           viewHolder.iv_already_stop.setVisibility(View.VISIBLE);
           viewHolder.tv_stop.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


   public class ViewHolder extends RecyclerView.ViewHolder {
       private TextView tv_phone,tv_manger,tv_name,tv_stop;
       private ImageView iv_manger,iv_already_stop;
       private LinearLayout lin_name;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           tv_phone=itemView.findViewById(R.id.tv_phone);
           tv_manger=itemView.findViewById(R.id.tv_manger);
           tv_name=itemView.findViewById(R.id.tv_name);
           tv_stop=itemView.findViewById(R.id.tv_stop);
           iv_manger=itemView.findViewById(R.id.iv_manger);
           iv_already_stop=itemView.findViewById(R.id.iv_already_stop);
           lin_name=itemView.findViewById(R.id.lin_name);
       }
   }
}
