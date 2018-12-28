package com.youzheng.zhejiang.robertmoog.Store.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.IconHintView;
import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.bean.GoodsListDetail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Request;

/**
 * 商品详情界面
 */
public class GoodsDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    private RollPagerView pagerView;
    /**
     * 987656201801020002
     */
    private TextView tv_goods_number;
    /**
     * 摩恩淋浴花洒龙头套装pro 淋雨喷头卫生间 浴室冷热水龙头沐浴器
     */
    private TextView tv_goods_content;
    /**
     * ￥123
     */
    private TextView tv_goods_money;
    /**
     * 品类 XXX
     */
    private TextView tv_type;
    /**
     * 品类 XXX
     */
    private TextView tv_series;
    /**
     * 品类 XXX
     */
    private TextView tv_spec;
    /**
     * 品类 XXX
     */
    private TextView tv_marketing_unit;
    /**
     * 品类 XXX
     */
    private TextView tv_value_of_moen;
    private int goodsID;
    private List<String> piclist=new ArrayList<>();
    private TestNormalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        initView();
        initData();
    }

    private void initData() {
        HashMap<String,Object> map=new HashMap<>();
        map.put("id",goodsID);

        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.GOODS_LIST_DETAIL + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e("商品详情",response);
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()==PublicUtils.code){
                    GoodsListDetail goodsListDetail = gson.fromJson(gson.toJson(baseModel.getDatas()),GoodsListDetail.class);
                    setData(goodsListDetail);
                }
            }
        });



    }

    private void setData(GoodsListDetail goodsListDetail) {
        if (goodsListDetail.getProductDdetail()==null) return;

        GoodsListDetail.ProductDdetailBean productDdetailBean=goodsListDetail.getProductDdetail();

        if (productDdetailBean!=null){
            if (productDdetailBean.getSkuId()!=null||!productDdetailBean.getSkuId().equals("")){
                tv_goods_number.setText(productDdetailBean.getSkuId());
            }
            if (productDdetailBean.getName()!=null||!productDdetailBean.getName().equals("")){
                tv_goods_content.setText(productDdetailBean.getName());
            }
            if (productDdetailBean.getPrice()!=null||!productDdetailBean.getPrice().equals("")){
                tv_goods_money.setText("￥"+productDdetailBean.getPrice());
            }
            if (productDdetailBean.getFirstCategory()!=null||!productDdetailBean.getFirstCategory().equals("")){
                tv_type.setText("品类 "+productDdetailBean.getFirstCategory());
            }
            if (productDdetailBean.getSeries()!=null||!productDdetailBean.getSeries().equals("")){
                tv_series.setText("系列 "+productDdetailBean.getSeries());
            }
            if (productDdetailBean.getSpecification()!=null||!productDdetailBean.getSpecification().equals("")){
                tv_spec.setText("规格 "+productDdetailBean.getSpecification());
            }
            if (productDdetailBean.getPackUnit()!=null||!productDdetailBean.getPackUnit().equals("")){
                tv_marketing_unit.setText("销售单位 "+productDdetailBean.getPackUnit());
            }
            if (productDdetailBean.getRetailPrice()!=null||!productDdetailBean.getRetailPrice().equals("")){
                tv_value_of_moen.setText("摩恩PR00售价 "+"￥"+productDdetailBean.getRetailPrice());
            }
            if (productDdetailBean.getList().size()!=0){
                piclist=  productDdetailBean.getList();
                adapter.notifyDataSetChanged();
            }

        }
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        textHeadTitle.setText("商品详情");
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        pagerView = (RollPagerView) findViewById(R.id.pagerView);
        tv_goods_number = (TextView) findViewById(R.id.tv_goods_number);
        tv_goods_content = (TextView) findViewById(R.id.tv_goods_content);
        tv_goods_money = (TextView) findViewById(R.id.tv_goods_money);
        tv_type = (TextView) findViewById(R.id.tv_type);
        tv_series = (TextView) findViewById(R.id.tv_series);
        tv_spec = (TextView) findViewById(R.id.tv_spec);
        tv_marketing_unit = (TextView) findViewById(R.id.tv_marketing_unit);
        tv_value_of_moen = (TextView) findViewById(R.id.tv_value_of_moen);
        goodsID=getIntent().getIntExtra("goodsID",0);
        initPagaer();
    }

    //设置轮播图
    private void initPagaer() {
        pagerView.setPlayDelay(2000);
        pagerView.setAnimationDurtion(500);
        //设置适配器
        adapter=new TestNormalAdapter();
        pagerView.setAdapter(adapter);

        //自定义指示器图片
        pagerView.setHintView(new IconHintView(this, R.mipmap.group_72_2, R.mipmap.group_72_1));

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

    private class TestNormalAdapter extends StaticPagerAdapter {


        @Override
        public View getView(ViewGroup container, final int position) {
            ImageView view = new ImageView(container.getContext());
            Glide.with(GoodsDetailActivity.this).load(piclist.get(position)).into(view);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (piclist.size()==1){
                pagerView.setPlayDelay(0);
            }else {
                pagerView.setPlayDelay(2000);
            }
           // view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //点击事件   具体点击了哪一张图片的下标
//                    Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }
        @Override
        public int getCount() {
            return piclist.size();
        }
    }

}
