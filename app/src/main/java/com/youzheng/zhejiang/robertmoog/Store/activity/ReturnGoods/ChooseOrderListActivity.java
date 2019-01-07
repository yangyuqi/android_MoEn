package com.youzheng.zhejiang.robertmoog.Store.activity.ReturnGoods;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.ChooseGoodsListAdapter;
import com.youzheng.zhejiang.robertmoog.Store.bean.OrderList;
import com.youzheng.zhejiang.robertmoog.Store.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择退货订单界面
 */
public class ChooseOrderListActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    private PullLoadMoreRecyclerView pr_list;
    private List<OrderList> list = new ArrayList<>();
    private List<Integer> piclist = new ArrayList<>();
    private ChooseGoodsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_order_list);
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pr_list.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, 15, getResources().getColor(R.color.bg_all)));
        pr_list.setLinearLayout();
        pr_list.setColorSchemeResources(R.color.colorPrimary);


        initData();
    }

    private void initData() {
        OrderList orderList = new OrderList();
        orderList.setType(0);
        orderList.setPic(R.mipmap.ic_launcher);
        orderList.setText("摩恩");
        list.add(orderList);

        OrderList orderList1 = new OrderList();
        orderList1.setType(1);
        orderList.setText("摩恩");
        orderList1.setPic(R.mipmap.ic_launcher);
        list.add(orderList1);

        piclist.add(R.mipmap.ic_launcher);
        piclist.add(R.mipmap.ic_launcher);
        piclist.add(R.mipmap.ic_launcher);

        adapter=new ChooseGoodsListAdapter(list,piclist,this);
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
