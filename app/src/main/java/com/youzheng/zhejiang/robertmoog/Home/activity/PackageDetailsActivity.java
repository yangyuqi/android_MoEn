package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.R;

public class PackageDetailsActivity extends BaseActivity {

    ListView ls ;
    TextView tv_name ,tv_desc ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.package_details_layout);
        initView();
    }

    private void initView() {
        ((TextView)findViewById(R.id.textHeadTitle)).setText("套餐详情");
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
