package com.youzheng.zhejiang.robertmoog.Store.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Home.activity.RegisterSuccessActivity;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.Model.login.RegisterBean;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.StoreCustomerAdapter;
import com.youzheng.zhejiang.robertmoog.Store.bean.CustomerList;
import com.youzheng.zhejiang.robertmoog.Store.listener.OnRecyclerViewAdapterItemClickListener;
import com.youzheng.zhejiang.robertmoog.Store.view.RecycleViewDivider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import okhttp3.Request;

/**
 * 门店客户界面
 */
public class StoreCustomerActivity extends BaseActivity implements View.OnClickListener, OnRecyclerViewAdapterItemClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    /**
     * 78
     */
    private TextView tv_number;
    private PullLoadMoreRecyclerView lv_list;
    private List<CustomerList.CoustomerListBean> list = new ArrayList<>();
    private StoreCustomerAdapter adapter;
    private int year;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_customer);
        initView();
         calendar = Calendar.getInstance();
        //获取系统的日期
        //年
        year = calendar.get(Calendar.YEAR);
        setListener();
    }

    private void setListener() {
        lv_list.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {

                if (year==calendar.get(Calendar.YEAR)){
                    list.clear();
                    initData(year);
                }else {
                    year++;
                    list.clear();
                    initData(year);
                }

            }

            @Override
            public void onLoadMore() {
                year--;
                initData(year);
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
        tv_number = (TextView) findViewById(R.id.tv_number);
        lv_list = (PullLoadMoreRecyclerView) findViewById(R.id.lv_list);
        lv_list.setLinearLayout();
        lv_list.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, 5, getResources().getColor(R.color.divider_color_item)));
        lv_list.setColorSchemeResources(R.color.colorPrimary);
        adapter = new StoreCustomerAdapter(list, this);
        lv_list.setAdapter(adapter);
          adapter.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData(year);
    }

    /**
     * 网络请求
     */
    private void initData(int year) {

        HashMap<String,Object> map=new HashMap<>();
        map.put("year",year);
        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.GET_CUSTOMER_LIST + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                lv_list.setPullLoadMoreCompleted();
            }

            @Override
            public void onResponse(String response) {
                Log.e("门店客户列表",response);
                lv_list.setPullLoadMoreCompleted();
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()==PublicUtils.code){
                    CustomerList customerList = gson.fromJson(gson.toJson(baseModel.getDatas()),CustomerList.class);
                    setData(customerList);
                }
            }
        });

    }

    private void setData(CustomerList customerList) {
        if (customerList.getCoustomerList()==null) return;
        if (customerList.getTotalAmount()!=0){
            int num=customerList.getTotalAmount();
            tv_number.setText(num+"");
        }else {
            tv_number.setText("0");
        }

        List<CustomerList.CoustomerListBean> coustomerListBeans=customerList.getCoustomerList();
        if (coustomerListBeans.size()!=0){
            list.addAll(coustomerListBeans);
            adapter.setListRefreshUi(coustomerListBeans);
        }else {
            showToast(getString(R.string.load_list_erron));
        }

        lv_list.setPullLoadMoreCompleted();
   }

    @Override
    public void onClick(View v) {
        finish();
    }



    @Override
    public void onItemClick(View view, int position) {
        Intent intent=new Intent(this, StoreCustomerInsideActivity.class);
        intent.putExtra("month",list.get(position).getMonth());
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
