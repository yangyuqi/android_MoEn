package com.youzheng.zhejiang.robertmoog.Count.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Count.adapter.TodayGoodsTypeSalesBestAdapter;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

public class TodayGoodsTypeSaleBestActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    /**
     * 销售数量
     */
    private TextView tv_rule;
    private LinearLayout lin_title;
    private PullLoadMoreRecyclerView pr_list;
    private TodayGoodsTypeSalesBestAdapter adapter;
    private List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_goods_type_sale_best);
        initView();
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        tv_rule = (TextView) findViewById(R.id.tv_rule);
        lin_title = (LinearLayout) findViewById(R.id.lin_title);
        pr_list = (PullLoadMoreRecyclerView) findViewById(R.id.pr_list);

        pr_list.setLinearLayout();
        pr_list.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, 5, getResources().getColor(R.color.divider_color_item)));
        pr_list.setColorSchemeResources(R.color.colorPrimary);
        initData();
    }

    private void initData() {
        list.add("花洒");
        list.add("花洒");
        list.add("花洒");
        list.add("花洒");
        list.add("花洒");
        list.add("花洒");
        list.add("花洒");

        adapter=new TodayGoodsTypeSalesBestAdapter(list,this);
        pr_list.setAdapter(adapter);
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
