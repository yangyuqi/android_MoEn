package com.youzheng.zhejiang.robertmoog.Store.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.ProfessionalCustomerAdapter;
import com.youzheng.zhejiang.robertmoog.Store.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * 专业客户界面
 */
public class ProfessionalCustomerActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    /**
     * 3
     */
    private TextView tv_number;
    private PullLoadMoreRecyclerView lv_list;
    private List<String> list=new ArrayList<>();
    private ProfessionalCustomerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_customer);
        initView();
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("专业客户");
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        textHeadNext.setVisibility(View.GONE);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        iv_next.setVisibility(View.VISIBLE);
        iv_next.setImageResource(R.mipmap.group_93_1);
        iv_next.setOnClickListener(this);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        tv_number = (TextView) findViewById(R.id.tv_number);
        lv_list = (PullLoadMoreRecyclerView) findViewById(R.id.lv_list);
        initData();
    }

    private void initData() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lv_list.setLinearLayout();
        lv_list.setColorSchemeResources(R.color.colorPrimary);
        lv_list.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, 5, getResources().getColor(R.color.divider_color_item)));
        list.add("吴奇隆");
        list.add("吴奇隆");
        list.add("吴奇隆");
        list.add("吴奇隆");
        list.add("吴奇隆");

        adapter=new ProfessionalCustomerAdapter(list,this);
        lv_list.setAdapter(adapter);
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
            case R.id.iv_next:
                startActivity(new Intent(this,AddCustomerActivity.class));
                break;
        }
    }
}
