package com.youzheng.zhejiang.robertmoog.Home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Model.TestBean;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.utils.View.NoScrollListView;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int ITEM_TITLE = 1;
    private int ITEM_CONTENT = 2;
    private List<TestBean> objects;
    private Context context ;

    public void setDate(List<TestBean> objects ,Context context) {
        this.objects = objects;
        this.context = context ;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        if (viewType == ITEM_TITLE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_goods_vh_item, parent, false);
            holder = new CommonGoodsViewHolder(view);
        } else if (viewType == ITEM_CONTENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_goods_vh_ls_item, parent, false);
            holder = new CommonGoodsTypeViewHolder(view);
        } return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CommonGoodsViewHolder) {
            ((CommonGoodsViewHolder) holder).tv_name.setText(objects.get(position).getName());
        }else if (holder instanceof CommonGoodsTypeViewHolder){
            ((CommonGoodsTypeViewHolder) holder).tv_name.setText(objects.get(position).getName());
            ((CommonGoodsTypeViewHolder) holder).iv_show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommonAdapter<String> adapter = new CommonAdapter<String>(context,objects.get(position).getData(),R.layout.common_goods_vh_item) {
                        @Override
                        public void convert(ViewHolder helper, String item) {
                            helper.setText(R.id.tv_name,item);
                        }
                    };
                    ((CommonGoodsTypeViewHolder) holder).ls.setAdapter(adapter);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return objects == null ? 0 : objects.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (objects.size() == 0) {
//            return EMPTY_VIEW;
        } else if (objects.get(position) .getType().equals("1")) {
            return ITEM_TITLE;
        } else if (objects.get(position).getType().equals("2")) {
            return ITEM_CONTENT;
        } else {
            return super.getItemViewType(position);

        }
        return super.getItemViewType(position);
    }


    public class CommonGoodsViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name ;

        public CommonGoodsViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }

    public class CommonGoodsTypeViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name ;
        NoScrollListView ls ;
        ImageView iv_show ;
        public CommonGoodsTypeViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            ls = itemView.findViewById(R.id.no_ls);
            iv_show = itemView.findViewById(R.id.iv_show);
        }
    }
}
