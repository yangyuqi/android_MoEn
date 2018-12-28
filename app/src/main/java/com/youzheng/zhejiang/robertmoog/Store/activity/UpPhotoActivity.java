package com.youzheng.zhejiang.robertmoog.Store.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.AddphotoAdapter;

import java.util.ArrayList;
import java.util.List;

public class UpPhotoActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    private GridView gv_photo;
    private List<Integer> list=new ArrayList<>();
    private AddphotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_photo);
        initView();
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        textHeadNext.setOnClickListener(this);
        textHeadNext.setText("发表");
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        gv_photo = (GridView) findViewById(R.id.gv_photo);
        initData();
    }

    private void initData() {
        list.add(R.mipmap.pg);
        list.add(R.mipmap.pg);
        list.add(R.mipmap.pg);
        list.add(R.mipmap.pg);

        adapter=new AddphotoAdapter(list,this);
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
            case R.id.textHeadNext:
                break;
        }
    }
}
