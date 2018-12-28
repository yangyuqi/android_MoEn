package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Home.adapter.CustomerGoodsAdapter;
import com.youzheng.zhejiang.robertmoog.Home.adapter.RecycleViewDivider;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.Model.Home.CustomerIntentDatas;
import com.youzheng.zhejiang.robertmoog.Model.Home.CustomerIntentListBean;
import com.youzheng.zhejiang.robertmoog.Model.Home.ShopPersonalListBean;
import com.youzheng.zhejiang.robertmoog.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;

public class AttentionManagerActivity extends BaseActivity {

    private ShopPersonalListBean listBean ;
    private TabLayout tabLayout ;
    private String personalId ,phone;
    private int pageNum = 1 ,pageSize=20;
    Map<String,Object> map = new HashMap<>();
    RecyclerView recyclerView ;
    List<CustomerIntentListBean> data = new ArrayList<>();
    CustomerGoodsAdapter adapter ;
    int widWidth ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attention_manager_layout);
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        widWidth = outMetrics.widthPixels;
        initView();
        initData();
    }

    private void initData() {
        if (personalId!=null){
            map.put("personalId",personalId);
        }
        if (phone!=null){
            map.put("phone",phone);
        }
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.CUSTOMER_INTENT + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()==PublicUtils.code){
                    CustomerIntentDatas datas = gson.fromJson(gson.toJson(baseModel.getDatas()),CustomerIntentDatas.class);
                    if (datas.getCustomerIntentList().size()>0){
                        List<CustomerIntentListBean> customerList = datas.getCustomerIntentList();
                        for (int  i = 0 ; i<customerList.size();i++){
                            customerList.get(i).setGoodsId(customerList.get(i).getProductList().get(0).getId());
                            customerList.get(i).setPhoto(customerList.get(i).getProductList().get(0).getPhoto());
                            customerList.get(i).setName(customerList.get(i).getProductList().get(0).getName());
                            customerList.get(i).setSku(customerList.get(i).getProductList().get(0).getSku());
                            customerList.get(i).setCreateDate(customerList.get(i).getProductList().get(0).getCreateDate());
                        }
                        adapter.setData(customerList,mContext);
                    }
                }
            }
        });
    }

    private void initView() {
        listBean = (ShopPersonalListBean) getIntent().getSerializableExtra("label");
        if (listBean!=null){
            personalId = listBean.getId();
        }
        tabLayout = findViewById(R.id.tab);
        ((TextView)findViewById(R.id.textHeadTitle)).setText(R.string.attention_intent_user);
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new CustomerGoodsAdapter(data,mContext ,widWidth);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.VERTICAL, 10, getResources().getColor(R.color.bg_all)));
        if (role.equals(PublicUtils.SHOP_SELLER)){
            tabLayout.setVisibility(View.VISIBLE);
        }else if (role.equals(PublicUtils.SHOP_LEADER)){
            tabLayout.setVisibility(View.GONE);
        }
    }
}
