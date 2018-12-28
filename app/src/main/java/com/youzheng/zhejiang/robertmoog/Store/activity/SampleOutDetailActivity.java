package com.youzheng.zhejiang.robertmoog.Store.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.SampleDetailAdapter;

import java.util.ArrayList;
import java.util.List;

public class SampleOutDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    /**
     * 李学东
     */
    private TextView tv_name;
    private GridView gv_photo;
    /**
     * 2016/01/19 09:19:04
     */
    private TextView tv_time;
    private List<Integer> list=new ArrayList<>();
    private SampleDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_out_detail);
        initView();
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("出样详情");
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        tv_name = (TextView) findViewById(R.id.tv_name);
        gv_photo = (GridView) findViewById(R.id.gv_photo);
        tv_time = (TextView) findViewById(R.id.tv_time);
        initData();
    }

    private void initData() {
        list.add(R.mipmap.pg);
        list.add(R.mipmap.pg);
        list.add(R.mipmap.pg);
        list.add(R.mipmap.pg);
        list.add(R.mipmap.pg);

        adapter=new SampleDetailAdapter(list,this);
        gv_photo.setAdapter(adapter);
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
        }
    }
}
