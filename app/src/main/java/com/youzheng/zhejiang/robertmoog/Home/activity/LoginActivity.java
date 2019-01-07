package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.MainActivity;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.Model.login.LoginBean;
import com.youzheng.zhejiang.robertmoog.Model.login.UserConfigDataBean;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.utils.QRcode.android.CaptureActivity;
import com.youzheng.zhejiang.robertmoog.utils.SharedPreferencesUtils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Request;

public class LoginActivity extends BaseActivity {

    private EditText edt_phone ,edt_password ;

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

        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_password = (EditText) findViewById(R.id.edt_password);

        findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_phone.getText().toString().equals("")){
                    showToast(getString(R.string.phone_not_null));
                    return;
                }
                if (edt_password.getText().toString().equals("")){
                    showToast(getString(R.string.pwd_not_null));
                    return;
                }

                initLogin();
            }
        });
    }

    private void initLogin() {
       OkHttpClientManager.getAsyn(UrlUtils.LOGIN+"?grant_type=password&username="+edt_phone.getText().toString()+"&password="+ PublicUtils.getSHA256StrJava(edt_password.getText().toString())+"&client_id=app&client_secret=appSecret", new OkHttpClientManager.StringCallback() {
           @Override
           public void onFailure(Request request, IOException e) {

           }

           @Override
           public void onResponse(String response) {
               BaseModel model = gson.fromJson(response,BaseModel.class);
               if (model.getCode()==PublicUtils.code){
                   LoginBean loginBean = gson.fromJson(gson.toJson(model.getDatas()), LoginBean.class);
                   SharedPreferencesUtils.setParam(mContext,PublicUtils.access_token,loginBean.getAccess_token());
                   OkHttpClientManager.postAsynJson(gson.toJson(new HashMap<>()), UrlUtils.GET_USER_ONFO + "?access_token=" + loginBean.getAccess_token(), new OkHttpClientManager.StringCallback() {
                       @Override
                       public void onFailure(Request request, IOException e) {

                       }

                       @Override
                       public void onResponse(String response) {
                           BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                           if (baseModel.getCode()==PublicUtils.code){
                               UserConfigDataBean dataBean = gson.fromJson(gson.toJson(baseModel.getDatas()),UserConfigDataBean.class);
                               SharedPreferencesUtils.setParam(mContext,PublicUtils.role,dataBean.getUserConfigData().getUserRole());
                               startActivity(new Intent(mContext, MainActivity.class));
                               finish();
                           }
                       }
                   });

               }else {
                   showToast(model.getMsg());
               }
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
