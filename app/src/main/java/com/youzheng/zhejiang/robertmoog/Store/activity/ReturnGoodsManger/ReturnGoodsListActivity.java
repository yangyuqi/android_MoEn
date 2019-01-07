package com.youzheng.zhejiang.robertmoog.Store.activity.ReturnGoodsManger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.ReturnGoodsListAdapter;
import com.youzheng.zhejiang.robertmoog.Store.adapter.ReturnGoodsTimeAdapter;
import com.youzheng.zhejiang.robertmoog.Store.bean.OrderList;
import com.youzheng.zhejiang.robertmoog.Store.listener.OnRecyclerViewAdapterItemClickListener;
import com.youzheng.zhejiang.robertmoog.Store.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

public class ReturnGoodsListActivity extends BaseActivity implements View.OnClickListener, OnRecyclerViewAdapterItemClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    /**
     * 搜索订单编号
     */
    private EditText tv_search;
    private ImageView iv_search;
    private LinearLayout lin_search;
    /**
     * 时间
     */
    private TextView tv_time;
    private PullLoadMoreRecyclerView rv_list;
    private GridView gv_time;
    /**
     * 重置
     */
    private TextView tv_again;
    /**
     * 确定
     */
    private TextView tv_confirm;
    private DrawerLayout drawer_layout;
    private List<OrderList> list = new ArrayList<>();
    private List<Integer> piclist = new ArrayList<>();
    private ReturnGoodsTimeAdapter goodsTimeAdapter;
    private ReturnGoodsListAdapter adapter;
    private List<String> strlist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_goods_list);
        initView();
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("退货单列表");
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        tv_search = (EditText) findViewById(R.id.tv_search);
        iv_search = (ImageView) findViewById(R.id.iv_search);
        iv_search.setOnClickListener(this);
        lin_search = (LinearLayout) findViewById(R.id.lin_search);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_time.setOnClickListener(this);
        rv_list = (PullLoadMoreRecyclerView) findViewById(R.id.rv_list);
        gv_time = (GridView) findViewById(R.id.gv_time);
        tv_again = (TextView) findViewById(R.id.tv_again);
        tv_confirm = (TextView) findViewById(R.id.tv_confirm);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//禁止手势滑动
        initData();
    }

    private void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_list.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, 15, getResources().getColor(R.color.bg_all)));
        rv_list.setLinearLayout();
        rv_list.setColorSchemeResources(R.color.colorPrimary);


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


        adapter=new ReturnGoodsListAdapter(list,piclist,this);
        rv_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        strlist.add("全部");
        strlist.add("一到三个月");
        strlist.add("一到三个月");
        strlist.add("一到三个月");
        strlist.add("一到三个月");
        strlist.add("一到三个月");

        goodsTimeAdapter=new ReturnGoodsTimeAdapter(strlist,this);
        gv_time.setAdapter(goodsTimeAdapter);
        goodsTimeAdapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.tv_time:
                drawer_layout.openDrawer(GravityCompat.END);
                break;
            case R.id.iv_search:

                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent=new Intent(this,ReturnGoodsDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
