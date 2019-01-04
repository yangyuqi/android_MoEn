package com.youzheng.zhejiang.robertmoog.Store.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.StoreCustomerInsideAdapter;
import com.youzheng.zhejiang.robertmoog.Store.bean.CustomerList;
import com.youzheng.zhejiang.robertmoog.Store.bean.StoreCustomerDetail;
import com.youzheng.zhejiang.robertmoog.Store.view.RecycleViewDivider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Request;

/**
 * 门店客户点击列表界面
 */
public class StoreCustomerInsideActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    private PullLoadMoreRecyclerView lv_list;
    private List<StoreCustomerDetail.MonthCoustomerDetailBean> list=new ArrayList<>();
    private StoreCustomerInsideAdapter adapter;
    private String month;
    private int pageSize = 10;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_customer_inside);
        initView();
        setListener();
    }

    private void setListener() {
        lv_list.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page=1;
                list.clear();
                initData(page,pageSize);
            }

            @Override
            public void onLoadMore() {
              page++;
              initData(page,pageSize);
            }
        });
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("门店客户");
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        lv_list = (PullLoadMoreRecyclerView) findViewById(R.id.lv_list);
        lv_list.setLinearLayout();
        lv_list.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, 5, getResources().getColor(R.color.divider_color_item)));
        lv_list.setColorSchemeResources(R.color.colorPrimary);

        adapter=new StoreCustomerInsideAdapter(list,this);
        lv_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        month=getIntent().getStringExtra("month");







    }

    @Override
    protected void onResume() {
        super.onResume();
        initData(page,pageSize);
    }

    private void initData(int page, int pageSize) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("pageNum",page);
        map.put("pageSize",pageSize);
        map.put("registerDate",month);

        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.GET_CUSTOMER_LIST_DETAIL + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                lv_list.setPullLoadMoreCompleted();
            }

            @Override
            public void onResponse(String response) {
                Log.e("门店客户详情",response);
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()==PublicUtils.code){
                    StoreCustomerDetail storeCustomerDetail = gson.fromJson(gson.toJson(baseModel.getDatas()),StoreCustomerDetail.class);
                    setData(storeCustomerDetail);
                    lv_list.setPullLoadMoreCompleted();
                }
            }

        });

    }

    private void setData(StoreCustomerDetail storeCustomerDetail) {
        if (storeCustomerDetail.getMonthCoustomerDetail()==null) return;
        List<StoreCustomerDetail.MonthCoustomerDetailBean> monthCoustomerDetailBeans=storeCustomerDetail.getMonthCoustomerDetail();
        if (monthCoustomerDetailBeans.size()!=0){
            list.addAll(monthCoustomerDetailBeans);
            adapter.refreshUI(monthCoustomerDetailBeans);
        }else {
          showToast(getString(R.string.load_list_erron));
        }

        lv_list.setPullLoadMoreCompleted();

    }

    @Override
    public void onClick(View v) {
        finish();
    }


}
