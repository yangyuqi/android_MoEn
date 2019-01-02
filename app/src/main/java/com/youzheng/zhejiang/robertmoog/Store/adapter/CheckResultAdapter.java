package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.bean.CheckStoreList;
import com.youzheng.zhejiang.robertmoog.Store.listener.OnRecyclerViewAdapterItemClickListener;

import java.util.List;

public class CheckResultAdapter extends RecyclerView.Adapter<CheckResultAdapter.CheckHolder> {
    private List<CheckStoreList.PatrolShopListBean> list;//调接口实体类为 CheckResultList
    private Context context;
    private LayoutInflater layoutInflater;
    private OnRecyclerViewAdapterItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnRecyclerViewAdapterItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    public CheckResultAdapter(List<CheckStoreList.PatrolShopListBean> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setUI(List<CheckStoreList.PatrolShopListBean> list){
        this.list=list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CheckHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item_child_content, viewGroup,false);
        final CheckHolder checkHolder = new CheckHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = checkHolder.getLayoutPosition();
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
                int position = checkHolder.getLayoutPosition();
                //设置监听
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemLongClick( v,position);
                }
                //true代表消费事件 不继续传递
                return true;
            }
        });

        return checkHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CheckHolder viewHolder, int i) {

        CheckStoreList.PatrolShopListBean bean=list.get(i);

        viewHolder.tv_date.setText(bean.getTime());
        viewHolder.tv_is_yes.setText(bean.getResult());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CheckHolder extends RecyclerView.ViewHolder {
        TextView tv_date;
        TextView tv_is_yes;

        public CheckHolder(@NonNull View itemView) {
            super(itemView);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_is_yes = (TextView) itemView.findViewById(R.id.tv_is_yes);
        }
    }

}
