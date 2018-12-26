package com.youzheng.zhejiang.robertmoog.Store.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.StoreCustomerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 门店客户界面
 */
public class StoreCustomerActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    /**
     * 78
     */
    private TextView tv_number;
    private ListView lv_list;
    private List<String> list=new ArrayList<>();
    private StoreCustomerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_customer);
        initView();
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("门店客户");
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        tv_number = (TextView) findViewById(R.id.tv_number);
        lv_list = (ListView) findViewById(R.id.lv_list);

        for (int i = 0; i <5 ; i++) {
            list.add("2012");
        }
        adapter=new StoreCustomerAdapter(list,this);
        lv_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        lv_list.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
         finish();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this,StoreCustomerInsideActivity.class));
    }
}
