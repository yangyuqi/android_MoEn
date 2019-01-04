package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Home.adapter.RecycleViewDivider;
import com.youzheng.zhejiang.robertmoog.Home.adapter.SearchResultAdapter;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.Model.Home.ScanDatas;
import com.youzheng.zhejiang.robertmoog.Model.Home.ScanDatasBean;
import com.youzheng.zhejiang.robertmoog.Model.TestBean;
import com.youzheng.zhejiang.robertmoog.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;

public class SearchGoodsActivity extends BaseActivity {

    RecyclerView recyclerView;
    ImageView iv_click ;
    EditText tv_search ;
    List<ScanDatasBean> data = new ArrayList<>();
    int widWidth ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_goods_layout);
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        widWidth = outMetrics.widthPixels;
        initView();

        initClick();
    }

    private void initClick() {
        iv_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tv_search.getText().toString().equals("")){
                    showToast("请输入搜索条件");
                    return;
                }

                Map<String,Object> map = new HashMap<>();
                map.put("code",tv_search.getText().toString());//f36451db-3efa-4ccb-8260-d7fb7e2128f9
                OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.SCAN_GOODS + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                        if (baseModel.getCode()== PublicUtils.code){
                            ScanDatas scanDatas = gson.fromJson(gson.toJson(baseModel.getDatas()),ScanDatas.class);
                            if (scanDatas.getSelectProducts().size()>0){
                                addapter.setDate(scanDatas.getSelectProducts(),mContext,"1",widWidth);
                            }
                        }
                    }
                });

            }
        });
    }
    SearchResultAdapter addapter ;
    private void initView() {
        ((TextView) findViewById(R.id.textHeadTitle)).setText("查找商品套餐");
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_click = findViewById(R.id.iv_click);
        tv_search = findViewById(R.id.tv_search);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        addapter = new SearchResultAdapter();
        recyclerView.setAdapter(addapter);
        recyclerView.addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.VERTICAL, 10, getResources().getColor(R.color.bg_all)));

    }
}
