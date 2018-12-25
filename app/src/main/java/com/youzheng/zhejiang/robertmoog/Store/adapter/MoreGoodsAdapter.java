package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.youzheng.zhejiang.robertmoog.R;

import java.util.List;

public class MoreGoodsAdapter extends RecyclerView.Adapter<MoreGoodsAdapter.PicHolder>{
    private List<Integer> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public MoreGoodsAdapter(List<Integer> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public PicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.item_order_pic,parent,false);
        PicHolder picHolder=new PicHolder(view);
        return picHolder;
    }

    @Override
    public void onBindViewHolder(PicHolder holder, int position) {
         holder.iv_pic.setImageResource(list.get(position));
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }
    public class PicHolder extends RecyclerView.ViewHolder{
        ImageView iv_pic;
        public PicHolder(View itemView) {
            super(itemView);
            iv_pic=itemView.findViewById(R.id.iv_pic);
        }
    }
}
