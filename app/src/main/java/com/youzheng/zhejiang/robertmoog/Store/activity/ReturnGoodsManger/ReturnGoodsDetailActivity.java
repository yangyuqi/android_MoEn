package com.youzheng.zhejiang.robertmoog.Store.activity.ReturnGoodsManger;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.ReturnGoodsDetailAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReturnGoodsDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    /**
     * 987656201801020002
     */
    private TextView tv_new_return_num;
    /**
     * 987656201801020002
     */
    private TextView tv_old_order_num;
    /**
     * 李白(导购)
     */
    private TextView tv_maker;
    /**
     * 187 0000 0009
     */
    private TextView tv_customer;
    private RecyclerView rv_list;
    /**
     * 5
     */
    private TextView tv_goods_number;
    /**
     * 应退金额
     */
    private TextView tv_get;
    /**  */
    private TextView tv_should_cut_money;
    /**
     * 实退金额
     */
    private TextView tv_dispatching;
    /**  */
    private TextView tv_really_cut_money;
    /**  */
    private TextView tv_cut_reason;
    private List<String> list = new ArrayList<>();
    private ReturnGoodsDetailAdapter adapter;
    /**
     * 吾问无为谓吾问无为谓
     */
    private TextView tv_reason_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_goods_detail);
        initView();
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("退货单详情");
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        tv_new_return_num = (TextView) findViewById(R.id.tv_new_return_num);
        tv_old_order_num = (TextView) findViewById(R.id.tv_old_order_num);
        tv_maker = (TextView) findViewById(R.id.tv_maker);
        tv_customer = (TextView) findViewById(R.id.tv_customer);
        rv_list = (RecyclerView) findViewById(R.id.rv_list);
        tv_goods_number = (TextView) findViewById(R.id.tv_goods_number);
        tv_get = (TextView) findViewById(R.id.tv_get);
        tv_should_cut_money = (TextView) findViewById(R.id.tv_should_cut_money);
        tv_dispatching = (TextView) findViewById(R.id.tv_dispatching);
        tv_really_cut_money = (TextView) findViewById(R.id.tv_really_cut_money);
        tv_cut_reason = (TextView) findViewById(R.id.tv_cut_reason);
        tv_reason_content = (TextView) findViewById(R.id.tv_reason_content);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_list.setLayoutManager(layoutManager);



    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();

    }

    private void initData() {
        list.add("退货单详情");
        list.add("退货单详情");
        list.add("退货单详情");
        list.add("退货单详情");

        adapter = new ReturnGoodsDetailAdapter(list, this);
        rv_list.setAdapter(adapter);
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
