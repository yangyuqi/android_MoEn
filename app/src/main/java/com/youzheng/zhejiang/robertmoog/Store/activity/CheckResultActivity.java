package com.youzheng.zhejiang.robertmoog.Store.activity;


import android.content.Intent;
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
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.adapter.CheckResultAdapter;
import com.youzheng.zhejiang.robertmoog.Store.bean.CheckStoreList;
import com.youzheng.zhejiang.robertmoog.Store.listener.OnRecyclerViewAdapterItemClickListener;
import com.youzheng.zhejiang.robertmoog.Store.view.RecycleViewDivider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import okhttp3.Request;

/**
 * 巡店列表界面
 */
public class CheckResultActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    /**
     * 2018年
     */
    private TextView tv_title;
    private PullLoadMoreRecyclerView pr_list;
    private List<CheckStoreList.PatrolShopListBean> list=new ArrayList<>();
    private CheckResultAdapter adapter;
    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_result);
        initView();
        Calendar selectedDate = Calendar.getInstance();
        //获取系统的日期
        //年
         year = selectedDate.get(Calendar.YEAR);
        setListener();
    }

    private void setListener() {
        pr_list.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
               list.clear();
               initData(year);
               tv_title.setText(year+"年");
            }

            @Override
            public void onLoadMore() {
               year=year-1;
               initData(year);
                tv_title.setText(year+"年");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData(2018);
        tv_title.setText(year+"年");
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("巡店结果");
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        tv_title = (TextView) findViewById(R.id.tv_title);
        pr_list = (PullLoadMoreRecyclerView) findViewById(R.id.pr_list);
        pr_list.setLinearLayout();
        pr_list.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.VERTICAL, 5, getResources().getColor(R.color.divider_color_item)));
        pr_list.setColorSchemeResources(R.color.colorPrimary);

        adapter=new CheckResultAdapter(list,this);
        pr_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void initData(int Year) {

        HashMap<String,Object> map=new HashMap<>();
        map.put("createDate",Year);

        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.CHECK_STORE_LIST + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                pr_list.setPullLoadMoreCompleted();
            }

            @Override
            public void onResponse(String response) {
                Log.e("巡店列表",response);
                pr_list.setPullLoadMoreCompleted();
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()==PublicUtils.code){
                    CheckStoreList checkStoreList = gson.fromJson(gson.toJson(baseModel.getDatas()),CheckStoreList.class);
                    setData(checkStoreList);
                }
            }
        });

        adapter.setOnItemClickListener(new OnRecyclerViewAdapterItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(CheckResultActivity.this,CheckStoreDetailActivity.class);
                intent.putExtra("checkID",list.get(position).getId());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });


    }

    private void setData(CheckStoreList checkStoreList) {
        if (checkStoreList==null) return;
        if (checkStoreList.getPatrolShopList()==null) return;

        List<CheckStoreList.PatrolShopListBean> beans=checkStoreList.getPatrolShopList();
        if (beans.size()!=0){
            list.addAll(beans);
            adapter.setUI(beans);
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
        }
    }
}
