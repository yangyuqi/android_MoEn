package com.youzheng.zhejiang.robertmoog.Count.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Count.adapter.TodayRegisterAdapter;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * 今日客户注册数 界面
 */
public class TodayRegisterNumberActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    private PullLoadMoreRecyclerView pr_list;
    private List<String> list=new ArrayList<>();
    private TodayRegisterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_register_number);
        initView();
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        pr_list = (PullLoadMoreRecyclerView) findViewById(R.id.pr_list);

        pr_list.setLinearLayout();
        pr_list.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, 5, getResources().getColor(R.color.divider_color_item)));
        pr_list.setColorSchemeResources(R.color.colorPrimary);

        pr_list.setPushRefreshEnable(false);
        pr_list.setPullRefreshEnable(false);
        initData();
    }

    private void initData() {
        list.add("2018/11/12");
        list.add("2018/11/12");
        list.add("2018/11/12");
        list.add("2018/11/12");
        list.add("2018/11/12");
        list.add("2018/11/12");
        list.add("2018/11/12");
        list.add("2018/11/12");
        list.add("2018/11/12");

        adapter=new TodayRegisterAdapter(list,this);
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
