package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.utils.View.MyCountDownTimer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

public class RegisterActivity  extends BaseActivity {

    EditText edt_phone ,edt_code;
    Button btn_send_code ;
    private MyCountDownTimer timer ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        initView();
    }

    private void initView() {
        ((TextView)findViewById(R.id.textHeadTitle)).setText("客户注册");
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.iv_next).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.iv_next)).setImageResource(R.mipmap.group_30_1);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_code = (EditText) findViewById(R.id.edt_code);
        btn_send_code = (Button) findViewById(R.id.btn_send_code);
        timer = new MyCountDownTimer(btn_send_code,60000,1000);
        findViewById(R.id.tv_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_phone.getText().toString().equals("")){
                    showToast(getString(R.string.phone_not_null));
                    return;
                }
                if (edt_code.getText().toString().equals("")){
                    showToast(getString(R.string.code_not_null));
                    return;
                }

                Map<String,Object> map = new HashMap<>();
                map.put("phone",edt_phone.getText().toString());
                map.put("checkCode",edt_code.getText().toString());
                OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.REGISTER_USER, new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(String response) {

                    }
                });

                startActivity(new Intent(mContext,RegisterSuccessActivity.class));
            }
        });

        btn_send_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_phone.getText().toString().equals("")){
                    showToast(getString(R.string.phone_not_null));
                    return;
                }
                Map<String,Object> map = new HashMap<>();
                map.put("phone",edt_phone.getText().toString());
                OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.SEND_CODE, new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                        if (baseModel.getCode()== PublicUtils.code){

                        }
                    }
                });
            }
        });
    }
}
