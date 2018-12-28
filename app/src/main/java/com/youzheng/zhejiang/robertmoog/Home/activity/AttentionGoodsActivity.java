package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Home.adapter.CommonAdapter;
import com.youzheng.zhejiang.robertmoog.Home.adapter.ViewHolder;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.Model.Home.CustomerIntentDataBean;
import com.youzheng.zhejiang.robertmoog.Model.Home.ShopPersonalListBean;
import com.youzheng.zhejiang.robertmoog.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

public class AttentionGoodsActivity extends BaseActivity {


    ListView ls ;
    ShopPersonalListBean bean ;
    CommonAdapter<String> adapter ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attention_goods_layout);
        initView();
        bean = (ShopPersonalListBean) getIntent().getSerializableExtra("label");

        initData();
    }

    private void initData() {
        Map<String,Object> map = new HashMap<>();
        map.put("id",bean.getCustomerId());
        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.ATTENTION_GOODS_LIST + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()== PublicUtils.code){
                    CustomerIntentDataBean intentDataBean = gson.fromJson(gson.toJson(baseModel.getDatas()),CustomerIntentDataBean.class);

                }
            }
        });
    }

    private void initView() {
        ((TextView)findViewById(R.id.textHeadTitle)).setText("意向商品");
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.iv_next).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.iv_next)).setImageResource(R.mipmap.group_75_1);
        ls = (ListView) findViewById(R.id.ls);

        adapter = new CommonAdapter<String>(mContext,new ArrayList<String>(),R.layout.common_goods_vh_item) {
            @Override
            public void convert(ViewHolder helper, String item) {

            }
        };

        ls.setAdapter(adapter);
    }
}
