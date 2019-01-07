package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.MainActivity;
import com.youzheng.zhejiang.robertmoog.R;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        if (access_token.equals("")){
            startActivity(new Intent(mContext,LoginActivity.class));
        }else {
            startActivity(new Intent(mContext, MainActivity.class));
        }
        finish();
    }
}
