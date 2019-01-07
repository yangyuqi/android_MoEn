package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

public class AddNewAddressActivity extends BaseActivity {

    private String customerId ;
    private EditText edt_name ,edt_phone ,edt_provice ,edt_city ,edt_town ,edt_address ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_address_layout);
        initView();
    }

    private void initView() {
        ((TextView)findViewById(R.id.textHeadTitle)).setText(R.string.add_new_address);
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        customerId = getIntent().getStringExtra("customerId");
        edt_name = findViewById(R.id.edt_name);
        edt_phone = findViewById(R.id.edt_phone);
        edt_provice = findViewById(R.id.edt_provice);
        edt_city = findViewById(R.id.edt_city);
        edt_town = findViewById(R.id.edt_town);
        edt_address = findViewById(R.id.edt_address);

        findViewById(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addAddress();
            }
        });

        edt_provice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClientManager.postAsynJson(gson.toJson(new HashMap<>()), UrlUtils.GET_PRIVICE+"?access_token="+access_token, new OkHttpClientManager.StringCallback() {
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

    private void addAddress() {
        Map<String,Object> map = new HashMap<>();
        map.put("shipPerson",edt_name.getText().toString());
        map.put("shipMobile",edt_phone.getText().toString());
        map.put("shipProvince","110000");
        map.put("shipCity","110100");
        map.put("shipDistrict","110101");
        map.put("shipStreet","110101001");
        map.put("shipAddress",edt_address.getText().toString());
        map.put("customerId",customerId);
        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.ADD_ADDRESS+"?access_token="+access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()==PublicUtils.code){
                    finish();
                }
            }
        });
    }
}
