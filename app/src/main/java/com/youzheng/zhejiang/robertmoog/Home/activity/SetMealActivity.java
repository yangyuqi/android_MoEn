package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.Model.Home.HomeListDataBean;
import com.youzheng.zhejiang.robertmoog.Model.Home.HomeListDataBeanBean;
import com.youzheng.zhejiang.robertmoog.Model.Home.MealMainDataBean;
import com.youzheng.zhejiang.robertmoog.Model.Home.MealMainDataList;
import com.youzheng.zhejiang.robertmoog.Model.Home.SetMealData;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.utils.CommonAdapter;
import com.youzheng.zhejiang.robertmoog.utils.ViewHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;

public class SetMealActivity extends BaseActivity{

    ListView ls ;
    TabLayout tab ;
    List<String> tab_str = new ArrayList<>();
    List<Integer> tab_id = new ArrayList<>();

    CommonAdapter<HomeListDataBean> adapter ;
    List<HomeListDataBean> data = new ArrayList<>();

    int pageNum = 1 ,pageSize = 20,typeId ;

    EditText tv_search ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_meal_layout);

        initView();
        initData();
        initEvent();
    }

    public void refreshData(int typeId){
        Map<String,Object> map = new HashMap<>();
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        map.put("skuCode",tv_search.getText().toString());
        map.put("typeId",typeId);
        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.MEAL_USER_LIST+"?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()==PublicUtils.code){
                    SetMealData setMealData = gson.fromJson(gson.toJson(baseModel.getDatas()),SetMealData.class);
                    if (setMealData.getListData().size()>0){
                        adapter.setData(setMealData.getListData());
                        adapter.notifyDataSetChanged();
                    }else {
                        adapter.setData(new ArrayList<HomeListDataBean>());
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void initEvent() {
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                typeId = tab_id.get(tab.getPosition());
                refreshData(tab_id.get(tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initView() {
        ((TextView)findViewById(R.id.textHeadTitle)).setText("套餐列表");
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tab = findViewById(R.id.tab);
        tv_search = findViewById(R.id.tv_search);
        ls = findViewById(R.id.ls);
        adapter = new CommonAdapter<HomeListDataBean>(mContext,data,R.layout.set_meal_ls_item) {
            @Override
            public void convert(ViewHolder helper, HomeListDataBean item) {
                helper.setText(R.id.tv_name,item.getComboCode());
                helper.setText(R.id.tv_desc,item.getComboName());
                Glide.with(mContext).load(item.getImgUrl()).error(R.mipmap.type_icon).into((ImageView) helper.getView(R.id.iv_icon));
                helper.setText(R.id.tv_golden,item.getComboDescribe());
                ListView listView = helper.getView(R.id.ls);
                CommonAdapter<HomeListDataBeanBean> adapter = new CommonAdapter<HomeListDataBeanBean>(mContext,item.getList(),R.layout.shop_details_ls_item) {
                    @Override
                    public void convert(ViewHolder helper, HomeListDataBeanBean item) {
                        helper.setText(R.id.tv_activity,item.getName());
                        helper.setText(R.id.tv_price,"¥"+item.getPrice());
                    }
                };
                listView.setAdapter(adapter);
            }
        };
        ls.setAdapter(adapter);

        findViewById(R.id.iv_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData(typeId);
            }
        });
    }

    private void initData() {
        OkHttpClientManager.postAsynJson(gson.toJson(new HashMap<>()), UrlUtils.SET_MEAL_LIST + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()== PublicUtils.code){
                    MealMainDataList list = gson.fromJson(gson.toJson(baseModel.getDatas()),MealMainDataList.class);
                    if (list.getMealMainData().size()>0){
                        for (MealMainDataBean bean : list.getMealMainData()){
                            tab_str.add(bean.getName());
                            tab_id.add(bean.getId());
                            tab.addTab(tab.newTab().setText(bean.getName()));
                        }
                    }
                }
            }
        });
    }
}
