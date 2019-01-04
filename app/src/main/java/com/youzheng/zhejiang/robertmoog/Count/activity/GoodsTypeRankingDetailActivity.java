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
import com.youzheng.zhejiang.robertmoog.Count.adapter.GoodsTypeRankingDetailAdapter;
import com.youzheng.zhejiang.robertmoog.Count.bean.GoodsTypeDetail;
import com.youzheng.zhejiang.robertmoog.Count.bean.GoodsTypeRankingList;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.view.RecycleViewDivider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Request;

/**
 * 商品品类排名详情界面
 */
public class GoodsTypeRankingDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    /**
     * 销售金额
     */
    private TextView tv_rule;
    private LinearLayout lin_title;
    private PullLoadMoreRecyclerView pr_list;
    private List<GoodsTypeDetail.ProductListBean> list=new ArrayList<>();
    private GoodsTypeRankingDetailAdapter adapter;

    private int page=1;
    private int pageSize=10;
    private boolean isDay=true;
    private String startstr="";
    private String endstr="";
    private int categoryId;
    private String type="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_type_ranking_detail);
        categoryId=getIntent().getIntExtra("goodsId",0);
        if (categoryId==0){
            categoryId=0;
        }
        type=getIntent().getStringExtra("type");

        initView();
        swtListener();
    }

    private void swtListener() {
        pr_list.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page=1;
                list.clear();
                initData(page,pageSize,isDay,startstr,endstr,categoryId);
            }

            @Override
            public void onLoadMore() {
                page++;
                initData(page,pageSize,isDay,startstr,endstr,categoryId);
            }
        });


    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
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

        pr_list.setPushRefreshEnable(false);
        pr_list.setPullRefreshEnable(false);

        // TODO: 2019/1/2 标题判断
        if (type.equals("数量")){
            textHeadTitle.setText("商品销售数量");
            tv_rule.setText("销售数量");
        }else {
            textHeadTitle.setText("商品销售金额");
            tv_rule.setText("销售金额");
        }


        adapter=new GoodsTypeRankingDetailAdapter(list,this);
        pr_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData(page,pageSize,isDay,startstr,endstr,categoryId);
    }

    private void initData(int page, int pageSize, boolean isDay, String startDate, String endDate, int categoryId) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("pageNum",page);
        map.put("pageSize",pageSize);
        map.put("isDay",isDay);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("categoryId",categoryId);

        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.GOODS_TYPE_RANKING_DETAIL + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                pr_list.setPullLoadMoreCompleted();
            }

            @Override
            public void onResponse(String response) {
                Log.e("商品品类排名详情",response);
                pr_list.setPullLoadMoreCompleted();
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()==PublicUtils.code){
                    GoodsTypeDetail goodsTypeDetail = gson.fromJson(gson.toJson(baseModel.getDatas()),GoodsTypeDetail.class);
                    setData(goodsTypeDetail);
                }
            }
        });
    }
    private void setData(GoodsTypeDetail goodsTypeDetail) {
        if (goodsTypeDetail==null)return;
        if (goodsTypeDetail.getProductList()==null) return;

        List<GoodsTypeDetail.ProductListBean> beanList=goodsTypeDetail.getProductList();
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
