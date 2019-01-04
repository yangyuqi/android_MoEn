package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.youzheng.zhejiang.robertmoog.Model.Home.AddressDatas;
import com.youzheng.zhejiang.robertmoog.Model.Home.AddressDatasBean;
import com.youzheng.zhejiang.robertmoog.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;

public class LocationManageActivity extends BaseActivity {

    ListView ls ;
    CommonAdapter<AddressDatasBean> adapter ;
    List<AddressDatasBean> data =new ArrayList<>();
    private String customerId  , pageNum = "1" ,pageSize ="20";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_manage_layout);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        data.clear();
        Map<String,Object> map = new HashMap<>();
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        map.put("customerId",customerId);
        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.ADDRESS_MANAGER+"?access_token="+access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()== PublicUtils.code){
                    AddressDatas addressDatas = gson.fromJson(gson.toJson(baseModel.getDatas()),AddressDatas.class);
                    if (addressDatas.getAddressList().size()>0){
                        data.addAll(addressDatas.getAddressList());
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void initView() {
        ((TextView)findViewById(R.id.textHeadTitle)).setText("地址管理");
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        customerId = getIntent().getStringExtra("customerId");
        data.clear();
        ls = (ListView) findViewById(R.id.ls_location);
        adapter = new CommonAdapter<AddressDatasBean>(mContext,data,R.layout.location_ls_item) {
            @Override
            public void convert(ViewHolder helper, AddressDatasBean item) {
                helper.setText(R.id.tv_name,item.getShipPerson());
                helper.setText(R.id.tv_phone,item.getShipMobile());
                helper.setText(R.id.tv_details,item.getShipAddress());
            }
        };
        ls.setAdapter(adapter);

        findViewById(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,AddNewAddressActivity.class);
                intent.putExtra("customerId",customerId);
                startActivity(intent);
            }
        });
    }
}
