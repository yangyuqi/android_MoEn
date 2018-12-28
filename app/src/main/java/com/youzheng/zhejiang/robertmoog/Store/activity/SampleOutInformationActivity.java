package com.youzheng.zhejiang.robertmoog.Store.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.SampleOutAdapter;
import com.youzheng.zhejiang.robertmoog.Store.view.MyListView;

import java.util.ArrayList;
import java.util.List;

public class SampleOutInformationActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    private MyListView lv_information;
    private MyListView lv_rain_information;
    private List<String> list=new ArrayList<>();
    private SampleOutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_out_information);
        initView();
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        textHeadTitle.setText("出样信息");
        textHeadNext.setText("编辑");
        textHeadNext.setOnClickListener(this);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        lv_information = (MyListView) findViewById(R.id.lv_information);
        lv_rain_information = (MyListView) findViewById(R.id.lv_rain_information);
        initData();
    }

    private void initData() {
        list.add("浴室柜组数");
        list.add("浴室柜组数");
        list.add("浴室柜组数");
        list.add("浴室柜组数");
        list.add("浴室柜组数");
        list.add("浴室柜组数");
        list.add("浴室柜组数");

        adapter=new SampleOutAdapter(list,this);
        lv_information.setAdapter(adapter);
        lv_rain_information.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.textHeadNext:
                startActivity(new Intent(this,SampleOutEditActivity.class));
                break;
        }
    }
}
