package com.youzheng.zhejiang.robertmoog.Home.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Model.Home.ProductListBean;
import com.youzheng.zhejiang.robertmoog.Model.Home.ScanDatasBean;
import com.youzheng.zhejiang.robertmoog.Model.TestBean;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.utils.View.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class SearchResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int ITEM_TITLE = 1;
    private int ITEM_CONTENT = 2;
    private List<ScanDatasBean> objects;
    private Context context ;
    private int widWidth ;
    private String type = "";//1表示搜索2.表示扫一扫3.表示卖货

    public void setDate(List<ScanDatasBean> objects , Context context ,String type ,int widWidth) {
        this.objects = objects;
        this.context = context ;
        notifyDataSetChanged();
        this.type = type ;
        this.widWidth = widWidth ;
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

            RelativeLayout view = ((CommonGoodsViewHolder) holder).rl_width;
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            params.width = (int) PublicUtils.dip2px(PublicUtils.px2dip(widWidth));
            view.setLayoutParams(params);

            ((CommonGoodsViewHolder) holder).tv_name.setText(objects.get(position).getName());
            Glide.with(context).load(objects.get(position).getPhoto()).error(R.mipmap.type_icon).into(((CommonGoodsViewHolder) holder).iv_icon);
            ((CommonGoodsViewHolder) holder).tv_desc.setText(objects.get(position).getCode());
            ((CommonGoodsViewHolder) holder).tv_price.setText("¥"+objects.get(position).getPrice());

            if (type.equals("1")){
                ((CommonGoodsViewHolder) holder).tv_get_num.setVisibility(View.GONE);
                ((CommonGoodsViewHolder) holder).tv_confirm.setVisibility(View.VISIBLE);
                ((CommonGoodsViewHolder) holder).tv_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().post(objects.get(position));
                        ((BaseActivity)context).finish();
                    }
                });

                ((CommonGoodsViewHolder) holder).hsv.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });

            }else if (type.equals("2")){
                ((CommonGoodsViewHolder) holder).tv_confirm.setVisibility(View.GONE);
                ((CommonGoodsViewHolder) holder).ll_num.setVisibility(View.VISIBLE);
                ((CommonGoodsViewHolder) holder).tv_get_num.setVisibility(View.GONE);
                ((CommonGoodsViewHolder) holder).edt_num.setText(""+objects.get(position).getNum());
            }else if (type.equals("3")){
                ((CommonGoodsViewHolder) holder).tv_confirm.setVisibility(View.GONE);
                ((CommonGoodsViewHolder) holder).ll_num.setVisibility(View.GONE);
                ((CommonGoodsViewHolder) holder).tv_get_num.setVisibility(View.VISIBLE);
                ((CommonGoodsViewHolder) holder).tv_get_num.setText(context.getResources().getString(R.string.label_X)+objects.get(position).getNum());
            }

        }else if (holder instanceof CommonGoodsTypeViewHolder){
            LinearLayout view = ((CommonGoodsTypeViewHolder) holder).ll_width;
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            params.width = (int) PublicUtils.dip2px(PublicUtils.px2dip(widWidth));
            view.setLayoutParams(params);
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

                    CommonAdapter<ProductListBean> adapter = new CommonAdapter<ProductListBean>(context,productListBeanList,R.layout.common_goods_ls_search) {
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
                ((CommonGoodsTypeViewHolder) holder).tv_get_num.setVisibility(View.GONE);
                ((CommonGoodsTypeViewHolder) holder).tv_confirm.setVisibility(View.VISIBLE);
                ((CommonGoodsTypeViewHolder) holder).tv_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().post(objects.get(position));
                        ((BaseActivity)context).finish();
                    }
                });
                ((CommonGoodsTypeViewHolder) holder).hsv.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });
            }else if (type.equals("2")){
                ((CommonGoodsTypeViewHolder) holder).tv_get_num.setVisibility(View.GONE);
                ((CommonGoodsTypeViewHolder) holder).tv_confirm.setVisibility(View.GONE);
                ((CommonGoodsTypeViewHolder) holder).ll_num.setVisibility(View.VISIBLE);
                ((CommonGoodsTypeViewHolder) holder).edt_num.setText(""+objects.get(position).getNum());
            }else if (type.equals("3")){
                ((CommonGoodsTypeViewHolder) holder).tv_get_num.setVisibility(View.VISIBLE);
                ((CommonGoodsTypeViewHolder) holder).tv_confirm.setVisibility(View.GONE);
                ((CommonGoodsTypeViewHolder) holder).ll_num.setVisibility(View.GONE);
                ((CommonGoodsTypeViewHolder) holder).tv_get_num.setText(context.getResources().getString(R.string.label_X)+objects.get(position).getNum());
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

        TextView tv_name ,tv_desc ,tv_price ,tv_confirm ,tv_get_num;
        ImageView iv_icon ;
        RelativeLayout rl_width ;
        HorizontalScrollView hsv;
        View ll_num ;
        EditText edt_num ;
        public CommonGoodsViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_confirm = itemView.findViewById(R.id.tv_confirm);
            rl_width = itemView.findViewById(R.id.rl_width);
            hsv = itemView.findViewById(R.id.hsv);
            ll_num = itemView.findViewById(R.id.ll_num);
            tv_get_num = itemView.findViewById(R.id.tv_get_num);
            edt_num = itemView.findViewById(R.id.edt_num);
        }
    }

    public class CommonGoodsTypeViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name ,tv_confirm , tv_desc ,tv_price ,tv_golden ,tv_activity ,tv_get_num;
        NoScrollListView ls ;
        View rl_activity ,ll_num;
        ImageView iv_show ,iv_icon ;
        LinearLayout ll_width ;
        HorizontalScrollView hsv ;
        EditText edt_num ;
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
            ll_width = itemView.findViewById(R.id.ll_width);
            hsv = itemView.findViewById(R.id.hsv);
            ll_num = itemView.findViewById(R.id.ll_num);
            tv_get_num = itemView.findViewById(R.id.tv_get_num);
            edt_num = itemView.findViewById(R.id.edt_num);
        }
    }
}
