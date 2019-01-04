package com.youzheng.zhejiang.robertmoog.Count.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Count.adapter.TodayStoreSaleAdapter;
import com.youzheng.zhejiang.robertmoog.Count.bean.ShopSale;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.view.RecycleViewDivider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Request;

/**
 * 今日门店销售额界面
 */
public class TodayStoreSalesActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    /**
     * 请选择时间
     */
    private TextView tv_startDate;
    /**
     * 请选择时间
     */
    private TextView tv_endDate;
    /**
     * 查询
     */
    private TextView tv_check;
    private PullLoadMoreRecyclerView pr_list;
    /**
     * 800
     */
    private TextView tv_order_total;
    /**
     * 10000
     */
    private TextView tv_order_money;
    /**
     * 2000
     */
    private TextView tv_order_value;
    private List<ShopSale.ShopDataBean> list=new ArrayList<>();
    private TodayStoreSaleAdapter adapter;

    private int page=1;
    private int pageSize=10;
    private boolean isDay=true;
    private String starstDate="";
    private String endsDate="";
    private String orderCount,orderAmountCount,customerTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_store_sales);
        initView();
        setListener();
    }

    private void setListener() {
       pr_list.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
           @Override
           public void onRefresh() {
               page=1;
               list.clear();
               initData(page,pageSize,isDay,starstDate,endsDate);
           }

           @Override
           public void onLoadMore() {
               page++;
               initData(page,pageSize,isDay,starstDate,endsDate);
           }
       });

    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("今日门店销售额");
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        tv_startDate = (TextView) findViewById(R.id.tv_startDate);
        tv_startDate.setOnClickListener(this);
        tv_endDate = (TextView) findViewById(R.id.tv_endDate);
        tv_endDate.setOnClickListener(this);
        tv_check = (TextView) findViewById(R.id.tv_check);
        tv_check.setOnClickListener(this);
        pr_list = (PullLoadMoreRecyclerView) findViewById(R.id.pr_list);
        tv_order_total = (TextView) findViewById(R.id.tv_order_total);
        tv_order_money = (TextView) findViewById(R.id.tv_order_money);
        tv_order_value = (TextView) findViewById(R.id.tv_order_value);

        pr_list.setLinearLayout();
        pr_list.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, 5, getResources().getColor(R.color.divider_color_item)));
        pr_list.setColorSchemeResources(R.color.colorPrimary);
        adapter=new TodayStoreSaleAdapter(list,this);
        pr_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData(page,pageSize,isDay,starstDate,endsDate);
    }

    private void initData(int page, int pageSize, boolean isDay, String startDate, String endDate) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("pageNum",page);
        map.put("pageSize",pageSize);
        map.put("isDay",isDay);
        map.put("startDate",startDate);
        map.put("endDate",endDate);

        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.SHOP_SALE + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                pr_list.setPullLoadMoreCompleted();
            }

            @Override
            public void onResponse(String response) {
                Log.e("今日门店销量",response);
                pr_list.setPullLoadMoreCompleted();
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()==PublicUtils.code){
                    ShopSale shopSale = gson.fromJson(gson.toJson(baseModel.getDatas()),ShopSale.class);
                    setData(shopSale);
                }

            }
        });
    }

    private void setData(ShopSale shopSale) {
        if (shopSale.getShopData()==null) return;
        orderCount=shopSale.getOrderCount();
        orderAmountCount=shopSale.getOrderAmountCount();
        customerTransaction=shopSale.getCustomerTransaction();

        if (!orderCount.equals("")||orderCount!=null){
            tv_order_total.setText(orderCount);
        }

        if (!orderAmountCount.equals("")||orderAmountCount!=null){
            tv_order_money.setText(orderAmountCount);
        }

        if (!customerTransaction.equals("")||customerTransaction!=null){
            tv_order_value.setText(customerTransaction);
        }

        List<ShopSale.ShopDataBean> beans=shopSale.getShopData();
        if (beans.size()!=0){
            list.addAll(beans);
            adapter.setUI(beans);
        }else {
            showToast(getString(R.string.load_list_erron));
        }

        pr_list.setPullLoadMoreCompleted();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.tv_startDate:
                break;
            case R.id.tv_endDate:
                break;
            case R.id.tv_check:
                break;
        }
    }
}
