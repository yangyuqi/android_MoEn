package com.youzheng.zhejiang.robertmoog.Store.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.OrderListAdapter;
import com.youzheng.zhejiang.robertmoog.Store.bean.OrderList;
import com.youzheng.zhejiang.robertmoog.Store.view.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单列表界面
 */
public class OrderListActivity extends AppCompatActivity implements View.OnClickListener {


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
    private RecyclerView rv_list;
    private OrderListAdapter adapter;
    private List<OrderList> list = new ArrayList<>();
    private List<Integer> piclist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        initView();
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        tv_search = (EditText) findViewById(R.id.tv_search);
        iv_search = (ImageView) findViewById(R.id.iv_search);
        iv_search.setOnClickListener(this);
        lin_search = (LinearLayout) findViewById(R.id.lin_search);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_time.setOnClickListener(this);
        rv_list = (RecyclerView) findViewById(R.id.rv_list);
        initData();
    }

    private void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_list.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL, 10, ContextCompat.getColor(this, R.color.bg_all)));
        rv_list.setLayoutManager(linearLayoutManager);

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

        adapter = new OrderListAdapter(list, piclist, this);
        rv_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_search:
                break;
            case R.id.tv_time:
                break;
        }
    }
}
