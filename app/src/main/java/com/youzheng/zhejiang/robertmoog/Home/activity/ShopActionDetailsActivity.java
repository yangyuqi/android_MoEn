package com.youzheng.zhejiang.robertmoog.Home.activity;

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
import com.youzheng.zhejiang.robertmoog.Model.Home.PromoIdDetails;
import com.youzheng.zhejiang.robertmoog.Model.Home.PromoIdDetailsData;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.utils.CommonAdapter;
import com.youzheng.zhejiang.robertmoog.utils.ViewHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;

public class ShopActionDetailsActivity extends BaseActivity {

    private int promoId ;

    private ListView ls ;
    TextView tv_name ,tv_start_time ,tv_desc ;
    CommonAdapter<String> adapter ;
    List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_action_details_layout);
        promoId = getIntent().getIntExtra("promoId",0);
        initView();
        initData();
    }

    private void initData() {
        Map<String,Object> map = new HashMap<>();
        map.put("promoId",promoId);
        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.ACTION_LIST_DETAILS + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()== PublicUtils.code){
                    PromoIdDetailsData promoIdDetails = gson.fromJson(gson.toJson(baseModel.getDatas()),PromoIdDetailsData.class);
                    tv_name.setText(promoIdDetails.getData().getPromoName());
                    tv_start_time.setText(promoIdDetails.getData().getStartTime()+"-"+promoIdDetails.getData().getEndTime());
                    tv_desc.setText(promoIdDetails.getData().getActivityAbstract());
                    if (promoIdDetails.getData().getOrderPromo().size()>0){
                        adapter.setData(promoIdDetails.getData().getOrderPromo());
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void initView() {
        ((TextView)findViewById(R.id.textHeadTitle)).setText("促销活动");
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ls = findViewById(R.id.ls);
        tv_desc = findViewById(R.id.tv_desc);
        tv_start_time = findViewById(R.id.tv_start_time);
        tv_name = findViewById(R.id.tv_name);

        adapter = new CommonAdapter<String>(mContext,data,R.layout.shop_details_ls_item) {
            @Override
            public void convert(ViewHolder helper, String item) {
                helper.setText(R.id.tv_activity,item);
            }
        };
        ls.setAdapter(adapter);
    }
}
