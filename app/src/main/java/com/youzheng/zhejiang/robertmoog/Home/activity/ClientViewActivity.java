package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.Model.Home.CustomerBean;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.utils.QRcode.android.CaptureActivity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

public class ClientViewActivity extends BaseActivity {

    EditText tv_search ;
    ImageView iv_click ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_view_layout);

        initView();
    }

    private void initView() {
        findViewById(R.id.iv_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,ScanSellerActivity.class));
            }
        });
        ((TextView)findViewById(R.id.textHeadTitle)).setText("客户识别");
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_click = findViewById(R.id.iv_click);
        tv_search = findViewById(R.id.tv_search);

        iv_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_search.getText().toString().equals("")){
                    showToast(getString(R.string.phone_not_null));
                    return;
                }
                Map<String,Object> map = new HashMap<>();
                map.put("phone",tv_search.getText().toString());
                OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.GET_CUSTOMER + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                        if (baseModel.getCode()== PublicUtils.code){
                            Intent intent = new Intent(mContext, CaptureActivity.class);
                            CustomerBean customerBean = gson.fromJson(gson.toJson(baseModel.getDatas()),CustomerBean.class);
                            intent.putExtra("customerId",customerBean.getCustomer().getCustomerId());
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }
}
