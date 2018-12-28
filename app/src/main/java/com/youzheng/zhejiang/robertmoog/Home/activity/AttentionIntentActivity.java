package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Home.adapter.CommonAdapter;
import com.youzheng.zhejiang.robertmoog.Home.adapter.ViewHolder;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.Model.Home.NotLabelList;
import com.youzheng.zhejiang.robertmoog.Model.Home.ShopPersonalList;
import com.youzheng.zhejiang.robertmoog.Model.Home.ShopPersonalListBean;
import com.youzheng.zhejiang.robertmoog.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Request;


public class AttentionIntentActivity extends BaseActivity {

    ListView ls ;
    CommonAdapter<ShopPersonalListBean> adapter ;
    List<String> data = new ArrayList<>();
    TabLayout tabLayout ;

    int layout_id ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attention_intent_layout);
        if (access_token.equals("")){
            startActivity(new Intent(mContext,LoginActivity.class));
            return;
        }

        ((TextView)findViewById(R.id.textHeadTitle)).setText("门店员工");
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tabLayout = (TabLayout) findViewById(R.id.tab);
        ls = (ListView) findViewById(R.id.ls);
        if (role.equals(PublicUtils.SHOP_SELLER)){
            tabLayout.setVisibility(View.VISIBLE);
        }else if (role.equals(PublicUtils.SHOP_LEADER)){
            tabLayout.setVisibility(View.GONE);
        }
        initEvent();
        initData(0);
    }

    private void initData(final int i) {
        if (i==0){
            OkHttpClientManager.postAsynJson(gson.toJson(new HashMap<>()), UrlUtils.SHOP_PERSONAL + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(String response) {
                    BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                    if (baseModel.getCode()== PublicUtils.code){
                        ShopPersonalList list = gson.fromJson(gson.toJson(baseModel.getDatas()),ShopPersonalList.class);
                        initView(i,list.getShopPersonalList());
                    }
                }
            });
        }else if (i==1){
            OkHttpClientManager.postAsynJson(gson.toJson(new HashMap<>()), UrlUtils.NOT_LABEEL + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(String response) {
                    BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                    if (baseModel.getCode()== PublicUtils.code){
                        NotLabelList list = gson.fromJson(gson.toJson(baseModel.getDatas()),NotLabelList.class);
                       initView(i,list.getNotLabelList());
                    }
                }
            });
        }


    }

    private void initEvent() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               initData(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initView(final int i , List<ShopPersonalListBean> list) {
        if (i==0) {
            layout_id = R.layout.attention_intent_layout_item;
        }else if (i==1){
            layout_id = R.layout.attention_intent_layout_other_item;
        }

        adapter = new CommonAdapter<ShopPersonalListBean>(mContext,list,layout_id) {
            @Override
            public void convert(ViewHolder helper, final ShopPersonalListBean item) {
                if (i==0) {
                    helper.setText(R.id.tv_name, item.getName() + "  (" + item.getBusinessRole() + ")");

                    helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext,AttentionManagerActivity.class);
                            intent.putExtra("label",item);
                            startActivity(intent);
                        }
                    });

                }else if (i==1){
                    helper.setText(R.id.tv_time,item.getCreateDate());
                    helper.setText(R.id.tv_phone,item.getCustCode());
                    helper.setText(R.id.tv_from,item.getChannel());
                    helper.getView(R.id.iv_add).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext,AttentionGoodsActivity.class);
                            intent.putExtra("label",item);
                            startActivity(intent);
                        }
                    });
                }
            }
        };
        ls.setAdapter(adapter);
    }
}
