package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

public class LoginActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        initView();
    }

    private void initView() {
        findViewById(R.id.tv_login_by_code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLayout(null);
            }
        });

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLayout("");
            }
        });

        findViewById(R.id.tv_login_by_pwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLayout("");
            }
        });

        findViewById(R.id.tv_login_for_pwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,ForgetPwdActivity.class));
            }
        });

        findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initLogin();
            }
        });
    }

    private void initLogin() {
        Map<String,Object> map = new HashMap<>();
       OkHttpClientManager.getAsyn("https://demo.waycomtech.com/api/oauth/token?grant_type=password&username=18101399087&password="+ PublicUtils.getSHA256StrJava("111111")+"&client_id=app&client_secret=appSecret", new OkHttpClientManager.StringCallback() {
           @Override
           public void onFailure(Request request, IOException e) {

           }

           @Override
           public void onResponse(String response) {
               Log.e("ssssssss",response);
           }
       });
    }

    void changeLayout(String type){
        if (type!=null) {
            findViewById(R.id.tv_login_for_pwd).setVisibility(View.VISIBLE);
            findViewById(R.id.tv_login_by_code).setVisibility(View.VISIBLE);
            findViewById(R.id.ll_show_pwd).setVisibility(View.VISIBLE);
            findViewById(R.id.rl_show_code).setVisibility(View.GONE);
            findViewById(R.id.tv_login_by_pwd).setVisibility(View.GONE);
            findViewById(R.id.iv_back).setVisibility(View.GONE);
        }else {
            findViewById(R.id.tv_login_for_pwd).setVisibility(View.GONE);
            findViewById(R.id.tv_login_by_code).setVisibility(View.GONE);
            findViewById(R.id.ll_show_pwd).setVisibility(View.GONE);
            findViewById(R.id.rl_show_code).setVisibility(View.VISIBLE);
            findViewById(R.id.tv_login_by_pwd).setVisibility(View.VISIBLE);
            findViewById(R.id.iv_back).setVisibility(View.VISIBLE);
        }
    }
}
