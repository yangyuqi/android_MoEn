package com.youzheng.zhejiang.robertmoog.Home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youzheng.zhejiang.robertmoog.Model.Home.ProductListBean;
import com.youzheng.zhejiang.robertmoog.Model.Home.ScanDatasBean;
import com.youzheng.zhejiang.robertmoog.Model.TestBean;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.utils.View.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int ITEM_TITLE = 1;
    private int ITEM_CONTENT = 2;
    private List<ScanDatasBean> objects;
    private Context context ;

    private String type = "";

    public void setDate(List<ScanDatasBean> objects , Context context ,String type) {
        this.objects = objects;
        this.context = context ;
        notifyDataSetChanged();
        this.type = type ;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        if (viewType == ITEM_TITLE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_goods_vh_item_search, parent, false);
            holder = new CommonGoodsViewHolder(view);
        } else if (viewType == ITEM_CONTENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_goods_vh_ls_item_search, parent, false);
            holder = new CommonGoodsTypeViewHolder(view);
        } return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CommonGoodsViewHolder) {
            ((CommonGoodsViewHolder) holder).tv_name.setText(objects.get(position).getName());
            Glide.with(context).load(objects.get(position).getPhoto()).error(R.mipmap.type_icon).into(((CommonGoodsViewHolder) holder).iv_icon);
            ((CommonGoodsViewHolder) holder).tv_desc.setText(objects.get(position).getCode());
            ((CommonGoodsViewHolder) holder).tv_price.setText("¥"+objects.get(position).getPrice());

            if (type.equals("1")){
                ((CommonGoodsViewHolder) holder).tv_confirm.setVisibility(View.VISIBLE);
            }else {
                ((CommonGoodsViewHolder) holder).tv_confirm.setVisibility(View.GONE);
            }

        }else if (holder instanceof CommonGoodsTypeViewHolder){
            ((CommonGoodsTypeViewHolder) holder).tv_name.setText(objects.get(position).getName());
            ((CommonGoodsTypeViewHolder) holder).iv_show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
             List<ProductListBean> productListBeanList = new ArrayList<>();
                    if (objects.get(position).getProductList().size()>0){
                        if (objects.get(position).isIsexpress()){
                            productListBeanList.clear();
                            objects.get(position).setIsexpress(false);
                            ((CommonGoodsTypeViewHolder) holder).iv_show.setImageResource(R.mipmap.group_14_1);
                        }else {
                            productListBeanList = objects.get(position).getProductList();
                            objects.get(position).setIsexpress(true);
                            ((CommonGoodsTypeViewHolder) holder).iv_show.setImageResource(R.mipmap.group_12_3);
                        }
                    }

                    CommonAdapter<ProductListBean> adapter = new CommonAdapter<ProductListBean>(context,productListBeanList,R.layout.common_goods_vh_item_search) {
                        @Override
                        public void convert(ViewHolder helper, ProductListBean item) {
                            helper.setText(R.id.tv_name,item.getName());
                            helper.setText(R.id.tv_desc,item.getSku());
                            helper.setText(R.id.tv_price,"¥"+item.getPrice());
                            Glide.with(mContext).load(item.getPhoto()).error(R.mipmap.type_icon).into((ImageView) helper.getView(R.id.iv_icon));
                        }
                    };
                    ((CommonGoodsTypeViewHolder) holder).ls.setAdapter(adapter);
                }
            });

            if (objects.get(position).getActivityName()!=null){
                ((CommonGoodsTypeViewHolder) holder).rl_activity.setVisibility(View.VISIBLE);
                ((CommonGoodsTypeViewHolder) holder).tv_activity.setText(objects.get(position).getActivityName());
            }else {
                ((CommonGoodsTypeViewHolder) holder).rl_activity.setVisibility(View.GONE);
            }

            ((CommonGoodsTypeViewHolder) holder).tv_price.setText("¥"+objects.get(position).getPrice());
            ((CommonGoodsTypeViewHolder) holder).tv_desc.setText(objects.get(position).getCode());
            Glide.with(context).load(objects.get(position).getPhoto()).error(R.mipmap.type_icon).into(((CommonGoodsTypeViewHolder) holder).iv_icon);
            ((CommonGoodsTypeViewHolder) holder).tv_golden.setText(objects.get(position).getComboDescribe());
            if (type.equals("1")){
                ((CommonGoodsTypeViewHolder) holder).tv_confirm.setVisibility(View.VISIBLE);
            }else if (type.equals("2")){
                ((CommonGoodsTypeViewHolder) holder).tv_confirm.setVisibility(View.GONE);
            }
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
        } else if (!objects.get(position).isSetMeal()) {
            return ITEM_TITLE;
        } else if (objects.get(position).isSetMeal()) {
            return ITEM_CONTENT;
        } else {
            return super.getItemViewType(position);

        }
        return super.getItemViewType(position);
    }


    public class CommonGoodsViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name ,tv_desc ,tv_price ,tv_confirm;
        ImageView iv_icon ;
        public CommonGoodsViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_confirm = itemView.findViewById(R.id.tv_confirm);
        }
    }

    public class CommonGoodsTypeViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name ,tv_confirm , tv_desc ,tv_price ,tv_golden ,tv_activity;
        NoScrollListView ls ;
        View rl_activity ;
        ImageView iv_show ,iv_icon ;
        public CommonGoodsTypeViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            ls = itemView.findViewById(R.id.no_ls);
            iv_show = itemView.findViewById(R.id.iv_show);
            tv_confirm = itemView.findViewById(R.id.tv_confirm);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_golden = itemView.findViewById(R.id.tv_golden);
            rl_activity = itemView.findViewById(R.id.rl_activity);
            tv_activity = itemView.findViewById(R.id.tv_activity);
        }
    }
}
