package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.Model.Home.GetPromoListBean;
import com.youzheng.zhejiang.robertmoog.Model.Home.GetPromoListDatas;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.utils.CommonAdapter;
import com.youzheng.zhejiang.robertmoog.utils.ViewHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Request;

public class ShopActionActivity extends BaseActivity {

    ListView ls ;
    private String promoType = "current";
    int pageNum = 1 , pageSize =20 ;
    private Integer customerId ;

    CommonAdapter<GetPromoListBean> adapter ;
    private List<GetPromoListBean> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_action_layout);
        initView();
        initData();
    }

    private void initData() {
        Map<String,Object> map = new HashMap<>();
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        if (customerId!=0){
            map.put("customerId",customerId);
        }
        if (promoType==null){
            map.put("promoType","current");
        }else {
            map.put("promoType",promoType);
        }
        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.ACTION_LIST + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()== PublicUtils.code){
                    GetPromoListDatas getPromoListDatas = gson.fromJson(gson.toJson(baseModel.getDatas()),GetPromoListDatas.class);
                    if (getPromoListDatas.getGetPromoList().size()>0){
                        adapter.setData(getPromoListDatas.getGetPromoList());
                        adapter.notifyDataSetChanged();
                    }else {
                        adapter.setData(new ArrayList<GetPromoListBean>());
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void initView() {
        ls = findViewById(R.id.ls);
        ((TextView)findViewById(R.id.textHeadTitle)).setText("门店活动");
        ((TextView)findViewById(R.id.textHeadNext)).setText("历史信息");
        ((TextView)findViewById(R.id.textHeadNext)).setVisibility(View.VISIBLE);
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        customerId = getIntent().getIntExtra("customerId",0);
        promoType = getIntent().getStringExtra("promoType");
        if (promoType!=null){
            ((TextView)findViewById(R.id.textHeadNext)).setVisibility(View.GONE);
        }
         adapter = new CommonAdapter<GetPromoListBean>(mContext,data,R.layout.shop_action_otem) {
            @Override
            public void convert(ViewHolder helper, final GetPromoListBean item) {

                helper.setText(R.id.tv_name,item.getPromoName());

                String regEx="[^0-9]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(item.getPromoDes());
                helper.setText(R.id.tv_day,m.replaceAll("").trim());
                if (item.getPromoTime()!=null){
                    String[] strings = item.getPromoTime().split("-");
                    if (strings.length>1){
                        helper.setText(R.id.tv_start_time,strings[0]);
                        helper.setText(R.id.tv_end_time,strings[1]);
                    }else {
                        helper.setText(R.id.tv_start_time,item.getPromoTime());
                    }
                }

                helper.getView(R.id.tv_see_details).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext,ShopActionDetailsActivity.class);
                        intent.putExtra("promoId",item.getPromoId());
                        startActivity(intent);
                    }
                });

            }
        };
        ls.setAdapter(adapter);

        ((TextView)findViewById(R.id.textHeadNext)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ShopActionActivity.class);
                intent.putExtra("promoType","history");
                startActivity(intent);
            }
        });
    }
}
