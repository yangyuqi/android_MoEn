package com.youzheng.zhejiang.robertmoog.Count.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Count.adapter.TodayGoodsTypeSalesBestAdapter;
import com.youzheng.zhejiang.robertmoog.Count.bean.GoodsTypeRankingList;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.view.RecycleViewDivider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Request;

public class TodayGoodsTypeSaleBestActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    /**
     * 销售数量
     */
    private TextView tv_rule;
    private LinearLayout lin_title;
    private PullLoadMoreRecyclerView pr_list;
    private TodayGoodsTypeSalesBestAdapter adapter;
    private List<GoodsTypeRankingList.CategoryListBean> list=new ArrayList<>();

    private int page=1;
    private int pageSize=10;
    private boolean isDay=true;
    private String startstr="";
    private String endstr="";
    private String rulestr="COUNT";//默认是数量



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_goods_type_sale_best);
        initView();
        setListener();
    }

    private void setListener() {
        pr_list.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page=1;
                list.clear();
                initData(page,pageSize,isDay,startstr,endstr,rulestr);
            }

            @Override
            public void onLoadMore() {
                page++;
                initData(page,pageSize,isDay,startstr,endstr,rulestr);
            }
        });

    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("今日品类销量排行");
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
        adapter=new TodayGoodsTypeSalesBestAdapter(list,this);
        pr_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData(page,pageSize,isDay,startstr,endstr,rulestr);
    }

    private void initData(int page, int pageSize, boolean isDay, String startDate, String endDate, String rule) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("pageNum",page);
        map.put("pageSize",pageSize);
        map.put("isDay",isDay);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("rule",rule);

        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.GOODS_TYPE_RANKING_LIST + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                pr_list.setPullLoadMoreCompleted();
            }

            @Override
            public void onResponse(String response) {
                Log.e("今日商品品类排名",response);
                pr_list.setPullLoadMoreCompleted();
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()==PublicUtils.code){
                    GoodsTypeRankingList goodsTypeRankingList = gson.fromJson(gson.toJson(baseModel.getDatas()),GoodsTypeRankingList.class);
                    setData(goodsTypeRankingList);
                }
            }
        });
    }
    private void setData(GoodsTypeRankingList goodsTypeRankingList) {
        if (goodsTypeRankingList==null) return;
        if (goodsTypeRankingList.getCategoryList()==null) return;

        List<GoodsTypeRankingList.CategoryListBean> beanList=goodsTypeRankingList.getCategoryList();
        if (beanList.size()!=0){
            list.addAll(beanList);
            adapter.setUI(beanList);
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
        }
    }
}
