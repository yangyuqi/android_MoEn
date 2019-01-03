package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.Model.Home.OrderSetMealDatasBean;
import com.youzheng.zhejiang.robertmoog.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;

public class SalesActivity extends BaseActivity {

    LinearLayout ll_address ;
    private String customerId ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_layout);

        initView();

        initData();
    }

    private void initData() {

    }

    private void initView() {
        ((TextView)findViewById(R.id.textHeadTitle)).setText(R.string.seller_table);
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.iv_next).setVisibility(View.GONE);
        ll_address = findViewById(R.id.ll_address);
        customerId = getIntent().getStringExtra("customerId");
        ll_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,LocationManageActivity.class);
                intent.putExtra("customerId",customerId);
                startActivity(intent);
            }
        });
    }
}
