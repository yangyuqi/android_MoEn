package com.youzheng.zhejiang.robertmoog.Home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Model.Home.CustomerIntentData;
import com.youzheng.zhejiang.robertmoog.Model.Home.CustomerIntentListBean;
import com.youzheng.zhejiang.robertmoog.Model.Home.ProductListBean;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.utils.SharedPreferencesUtils;
import com.youzheng.zhejiang.robertmoog.utils.View.DeleteDialog;
import com.youzheng.zhejiang.robertmoog.utils.View.DeleteDialogInterface;
import com.youzheng.zhejiang.robertmoog.utils.View.NoScrollListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;

public class CustomerGoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<CustomerIntentListBean> data ;
    private List<ProductListBean> productList = new ArrayList<>();
    public Context context ;
    public int widWidth ;
    public String token ;
    public Gson gson = new Gson() ;

    public CustomerGoodsAdapter(List<CustomerIntentListBean> dataList, Context context ,int widWidth) {
        data = dataList ;
        this.context = context ;
        this.widWidth = widWidth ;
        this.token = (String) SharedPreferencesUtils.getParam(context,PublicUtils.access_token,"");
    }

    public void setData(List<CustomerIntentListBean> dataList  , Context context){
        data = dataList ;
        this.context = context ;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customer_rv_item,null);
        return new CustomerIntentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {

        if (viewHolder instanceof CustomerIntentViewHolder){
            if (data.get(i).getProductList().size()>1){
                ((CustomerIntentViewHolder) viewHolder).rl_show.setVisibility(View.VISIBLE);
                ((CustomerIntentViewHolder) viewHolder).tv_num.setText(String.valueOf(data.get(i).getProductList().size()-1));

                ((CustomerIntentViewHolder) viewHolder).rl_show.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (productList.size()>0){
                            productList.clear();
                        }else {
                            productList = data.get(i).getProductList().subList(1,data.get(i).getProductList().size());
                        }
                        CommonAdapter<ProductListBean> commonAdapter = new CommonAdapter<ProductListBean>(context,productList,R.layout.customer_nols_item) {
                            @Override
                            public void convert(ViewHolder helper, ProductListBean item) {
                                helper.setText(R.id.tv_name,item.getName());
                                helper.setText(R.id.tv_time,item.getCreateDate());
                                helper.setText(R.id.tv_desc,item.getSku());
                                Glide.with(context).load(item.getPhoto()).into((ImageView) helper.getView(R.id.iv_icon));
                                LinearLayout view = ((CustomerIntentViewHolder) viewHolder).ll_width;
                                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
                                params.width = (int) PublicUtils.dip2px(PublicUtils.px2dip(widWidth));
                                view.setLayoutParams(params);
                                helper.getView(R.id.main_right_drawer_layout).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                            }
                        };
                        ((CustomerIntentViewHolder) viewHolder).ls.setAdapter(commonAdapter);
                    }
                });

            }else {
                ((CustomerIntentViewHolder) viewHolder).rl_show.setVisibility(View.GONE);
            }

            LinearLayout view = ((CustomerIntentViewHolder) viewHolder).ll_width;
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            params.width = (int) PublicUtils.dip2px(PublicUtils.px2dip(widWidth));
            view.setLayoutParams(params);

            ((CustomerIntentViewHolder) viewHolder).tv_phone.setText(data.get(i).getCustCode());
            ((CustomerIntentViewHolder) viewHolder).tv_name.setText(data.get(i).getName());
            ((CustomerIntentViewHolder) viewHolder).tv_desc.setText(data.get(i).getSku());
            ((CustomerIntentViewHolder) viewHolder).tv_time.setText(data.get(i).getCreateDate());
            Glide.with(context).load(data.get(i).getPhoto()).into(((CustomerIntentViewHolder) viewHolder).iv_icon);

            ((CustomerIntentViewHolder) viewHolder).iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (token.equals("")){
                        return;
                    }

                    DeleteDialog dialog = new DeleteDialog(context,"提示","是否删除此意向","确定");
                    dialog.show();
                    dialog.OnDeleteBtn(new DeleteDialogInterface() {
                        @Override
                        public void isDelete(boolean isdelete) {
                            Map<String,Object> map = new HashMap<>() ;
                            map.put("id",data.get(i).getId());
                            OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.DELETE_INTENT + "?access_token=" + token, new OkHttpClientManager.StringCallback() {
                                @Override
                                public void onFailure(Request request, IOException e) {

                                }

                                @Override
                                public void onResponse(String response) {

                                }
                            });
                        }
                    });
                }
            });

            ((CustomerIntentViewHolder) viewHolder).main_right_drawer_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (token.equals("")){
                        return;
                    }
                    DeleteDialog dialog = new DeleteDialog(context,"提示","是否删除此意向商品","确定");
                    dialog.show();
                    dialog.OnDeleteBtn(new DeleteDialogInterface() {
                        @Override
                        public void isDelete(boolean isdelete) {
                            Map<String,Object> map = new HashMap<>();
                            map.put("id",data.get(i).getGoodsId());
                            OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.DELETE_GOODS + "?access_token=" + token, new OkHttpClientManager.StringCallback() {
                                @Override
                                public void onFailure(Request request, IOException e) {

                                }

                                @Override
                                public void onResponse(String response) {

                                }
                            });
                        }
                    });

                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class  CustomerIntentViewHolder extends RecyclerView.ViewHolder{

        View rl_show ,main_right_drawer_layout;
        TextView tv_phone ,tv_time ,tv_name ,tv_desc ,tv_num;
        ImageView iv_mesg , iv_delete ,iv_icon;
        NoScrollListView ls ;
        LinearLayout ll_width ;

        public CustomerIntentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            rl_show = itemView.findViewById(R.id.rl_show);
            tv_num = itemView.findViewById(R.id.tv_num);
            ls = itemView.findViewById(R.id.no_ls);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            ll_width = itemView.findViewById(R.id.ll_width);
            main_right_drawer_layout = itemView.findViewById(R.id.main_right_drawer_layout);
        }
    }
}
