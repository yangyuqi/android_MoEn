package com.youzheng.zhejiang.robertmoog.Store.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.StoreCustomerInsideAdapter;

import java.util.ArrayList;
import java.util.List;
/**
 * 门店客户点击列表界面
 */
public class StoreCustomerInsideActivity extends BaseActivity {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    private ListView lv_list;
    private List<String> list=new ArrayList<>();
    private StoreCustomerInsideAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_customer_inside);
        initView();
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        lv_list = (ListView) findViewById(R.id.lv_list);

        for (int i = 0; i <5 ; i++) {
            list.add("2012");
        }
        adapter=new StoreCustomerInsideAdapter(list,this);
        lv_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
