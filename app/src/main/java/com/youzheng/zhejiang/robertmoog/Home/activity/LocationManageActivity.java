package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Home.adapter.CommonAdapter;
import com.youzheng.zhejiang.robertmoog.Home.adapter.ViewHolder;
import com.youzheng.zhejiang.robertmoog.R;

import java.util.ArrayList;
import java.util.List;

public class LocationManageActivity extends BaseActivity {

    ListView ls ;
    CommonAdapter<String> adapter ;
    List<String> data =new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_manage_layout);
        initView();
    }

    private void initView() {
        ((TextView)findViewById(R.id.textHeadTitle)).setText("地址管理");
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        data.clear();
        data.add("刘诗诗");data.add("刘诗诗");data.add("刘诗诗");data.add("刘诗诗");data.add("刘诗诗");data.add("刘诗诗");data.add("刘诗诗");
        ls = (ListView) findViewById(R.id.ls_location);
        adapter = new CommonAdapter<String>(mContext,data,R.layout.location_ls_item) {
            @Override
            public void convert(ViewHolder helper, String item) {
                helper.setText(R.id.tv_name,item);
            }
        };
        ls.setAdapter(adapter);

        findViewById(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,AddNewAddressActivity.class));
            }
        });
    }
}
