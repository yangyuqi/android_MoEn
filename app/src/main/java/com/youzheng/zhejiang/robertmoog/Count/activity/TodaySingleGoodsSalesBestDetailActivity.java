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
import com.youzheng.zhejiang.robertmoog.Count.adapter.TodayGoodsTypeSalesBestDetailAdapter;
import com.youzheng.zhejiang.robertmoog.Count.adapter.TodaySingeleGoodSalesBestDetailAdapter;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * 今日单品销售最佳界面
 */
public class TodaySingleGoodsSalesBestDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    /**
     * 销售金额
     */
    private TextView tv_rule;
    private LinearLayout lin_title;
    private PullLoadMoreRecyclerView pr_list;
    private List<String> list=new ArrayList<>();
    private TodaySingeleGoodSalesBestDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_type_ranking_detail);
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

        pr_list.setPushRefreshEnable(false);
        pr_list.setPullRefreshEnable(false);

        // TODO: 2019/1/2 标题判断
        textHeadTitle.setText("今日单品销售排名");

        initData();
    }

    private void initData() {
        list.add("SKU987568");
        list.add("SKU987568");
        list.add("SKU987568");
        list.add("SKU987568");
        list.add("SKU987568");
        list.add("SKU987568");

        adapter=new TodaySingeleGoodSalesBestDetailAdapter(list,this);
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
