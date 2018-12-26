package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Home.adapter.RecycleViewDivider;
import com.youzheng.zhejiang.robertmoog.Home.adapter.SearchResultAdapter;
import com.youzheng.zhejiang.robertmoog.Model.TestBean;
import com.youzheng.zhejiang.robertmoog.R;

import java.util.ArrayList;
import java.util.List;

public class SearchGoodsActivity extends BaseActivity {

    RecyclerView recyclerView;

    List<TestBean> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_goods_layout);

        initView();
    }

    private void initView() {
        ((TextView) findViewById(R.id.textHeadTitle)).setText("查找商品套餐");
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        List<String> mm = new ArrayList<>();
        mm.add("杨宇奇");
        mm.add("杨宇奇");
        mm.add("杨宇奇");
        TestBean bean = new TestBean("按时归还大", "1", new ArrayList<String>());
        TestBean bean1 = new TestBean("按时归", "2", mm);
        data.add(bean1);
        data.add(bean);
        data.add(bean1);
        data.add(bean);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        SearchResultAdapter addapter = new SearchResultAdapter();
        recyclerView.setAdapter(addapter);
        addapter.setDate(data, mContext);
        recyclerView.addItemDecoration(new RecycleViewDivider(
                mContext, LinearLayoutManager.VERTICAL, 10, getResources().getColor(R.color.bg_all)));

    }
}
