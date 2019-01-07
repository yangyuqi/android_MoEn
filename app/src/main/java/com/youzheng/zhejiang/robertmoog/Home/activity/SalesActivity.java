package com.youzheng.zhejiang.robertmoog.Home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;


import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Home.adapter.RecycleViewDivider;
import com.youzheng.zhejiang.robertmoog.Home.adapter.SearchResultAdapter;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.Model.Home.EnumsDatas;
import com.youzheng.zhejiang.robertmoog.Model.Home.EnumsDatasBean;
import com.youzheng.zhejiang.robertmoog.Model.Home.OrderProductDatasBean;
import com.youzheng.zhejiang.robertmoog.Model.Home.OrderSetMealDatasBean;
import com.youzheng.zhejiang.robertmoog.Model.Home.SaleData;
import com.youzheng.zhejiang.robertmoog.Model.Home.SaleDataBean;
import com.youzheng.zhejiang.robertmoog.Model.Home.ScanDatasBean;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.utils.QRcode.android.CaptureActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import okhttp3.Request;

public class SalesActivity extends BaseActivity {

    LinearLayout ll_address ;
    private String customerId ,addressId ;
    private ArrayList<ScanDatasBean> data = new ArrayList<>();
    private RecyclerView recyclerView ;
    SearchResultAdapter addapter ;
    int widWidth ;
    TextView tv_confrim ;
    Map<String,Object> map = new HashMap<>();
    private OptionsPickerView pvCustomTime;
    TextView tv_name ,tv_phone ,tv_details ,tv_get_state , tv_dispatching_type ,tv_get_money_type ,tv_get_money_of_now ,tv_should_money ,edt_content;
    String PickUpStatus ,ShoppingMethod ,paymentMethod;
    Switch sv_life ,sv_present ;
    private String payAmount ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_layout);
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        widWidth = outMetrics.widthPixels;
        initView();

        initClick();
    }

    private void initClick() {
        tv_confrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderConfirm();
            }
        });
        findViewById(R.id.rl_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,LocationManageActivity.class);
                intent.putExtra("customerId",customerId);
                startActivity(intent);
            }
        });

        tv_get_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClientManager.postAsynJson(gson.toJson(new HashMap<>()), UrlUtils.LIST_DATA+"?access_token="+access_token, new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                        if (baseModel.getCode()==PublicUtils.code){
                            EnumsDatas enumsDatas = gson.fromJson(gson.toJson(baseModel.getDatas()),EnumsDatas.class);
                            if (enumsDatas.getEnums().size()>0){
                                for (final EnumsDatasBean bean : enumsDatas.getEnums()){
                                    if (bean.getClassName().equals("PickUpStatus")){
                                        final List<String> date = new ArrayList<String>();
                                        for (int i = 0; i < bean.getDatas().size(); i++) {
                                            date.add(bean.getDatas().get(i).getDes());
                                        }
                                        pvCustomTime = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
                                            @Override
                                            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                                for (int i = 0; i < bean.getDatas().size(); i++) {
                                                    if (date.get(options1).equals(bean.getDatas().get(i).getDes())) {
                                                        tv_get_state.setText(date.get(options1));
                                                        PickUpStatus = bean.getDatas().get(i).getId();
                                                    }
                                                }
                                            }
                                        }).setTitleText("选择提货方式").build();
                                        pvCustomTime.setPicker(date);
                                        pvCustomTime.show();
                                    }
                                }
                            }
                        }
                    }
                });
            }
        });

        tv_dispatching_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClientManager.postAsynJson(gson.toJson(new HashMap<>()), UrlUtils.LIST_DATA+"?access_token="+access_token, new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                        if (baseModel.getCode()==PublicUtils.code){
                            EnumsDatas enumsDatas = gson.fromJson(gson.toJson(baseModel.getDatas()),EnumsDatas.class);
                            if (enumsDatas.getEnums().size()>0){
                                for (final EnumsDatasBean bean : enumsDatas.getEnums()){
                                    if (bean.getClassName().equals("ShoppingMethod")){
                                        final List<String> date = new ArrayList<String>();
                                        for (int i = 0; i < bean.getDatas().size(); i++) {
                                            date.add(bean.getDatas().get(i).getDes());
                                        }
                                        pvCustomTime = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
                                            @Override
                                            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                                for (int i = 0; i < bean.getDatas().size(); i++) {
                                                    if (date.get(options1).equals(bean.getDatas().get(i).getDes())) {
                                                        tv_dispatching_type.setText(date.get(options1));
                                                        ShoppingMethod = bean.getDatas().get(i).getId();
                                                    }
                                                }
                                            }
                                        }).setTitleText("选择配送方式").build();
                                        pvCustomTime.setPicker(date);
                                        pvCustomTime.show();
                                    }
                                }
                            }
                        }
                    }
                });
            }
        });

        tv_get_money_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClientManager.postAsynJson(gson.toJson(new HashMap<>()), UrlUtils.LIST_DATA+"?access_token="+access_token, new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                        if (baseModel.getCode()==PublicUtils.code){
                            EnumsDatas enumsDatas = gson.fromJson(gson.toJson(baseModel.getDatas()),EnumsDatas.class);
                            if (enumsDatas.getEnums().size()>0){
                                for (final EnumsDatasBean bean : enumsDatas.getEnums()){
                                    if (bean.getClassName().equals("PaymentMethod")){
                                        final List<String> date = new ArrayList<String>();
                                        for (int i = 0; i < bean.getDatas().size(); i++) {
                                            date.add(bean.getDatas().get(i).getDes());
                                        }
                                        pvCustomTime = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
                                            @Override
                                            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                                for (int i = 0; i < bean.getDatas().size(); i++) {
                                                    if (date.get(options1).equals(bean.getDatas().get(i).getDes())) {
                                                        tv_get_money_type.setText(date.get(options1));
                                                        paymentMethod = bean.getDatas().get(i).getId();
                                                    }
                                                }
                                            }
                                        }).setTitleText("选择收款方式").build();
                                        pvCustomTime.setPicker(date);
                                        pvCustomTime.show();
                                    }
                                }
                            }
                        }
                    }
                });
            }
        });
    }

    private void orderConfirm() {
        if (PickUpStatus == null) {
            showToast("选择提货方式");
            return;
        }
        if (ShoppingMethod == null) {
            showToast("选择配送方式");
            return;
        }
        if (paymentMethod == null) {
            showToast("选择收款方式");
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("isFreeGift", sv_present.isChecked());
        map.put("isMoen", sv_life.isChecked());
        map.put("customerId", customerId);
        map.put("pickUpStatus", PickUpStatus);
        map.put("paymentMethod", paymentMethod);
        map.put("shoppingMethod", ShoppingMethod);
        if (data.size() > 0) {
            List<OrderProductDatasBean> orderProductDatasBeans = new ArrayList<>();
            List<OrderSetMealDatasBean> orderSetMealDatasBeans = new ArrayList<>();
            for (ScanDatasBean scanDatasBean : data) {
                if (!scanDatasBean.isSetMeal()) {
                    OrderProductDatasBean datasBean = new OrderProductDatasBean();
                    datasBean.setNum("" + scanDatasBean.getNum());
                    datasBean.setProductId(scanDatasBean.getId());
                    orderProductDatasBeans.add(datasBean);
                } else {
                    OrderSetMealDatasBean mealDatasBean = new OrderSetMealDatasBean();
                    mealDatasBean.setSetMealId(scanDatasBean.getId());
                    mealDatasBean.setNum("" + scanDatasBean.getNum());
                    orderSetMealDatasBeans.add(mealDatasBean);
                }
            }
            map.put("orderProductDatas", orderProductDatasBeans);
            map.put("orderSetMealDatas", orderSetMealDatasBeans);
            map.put("payAmount",payAmount);
            map.put("info",edt_content.getText().toString());
        }

        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.ORDER_DETAOLS + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                showToast(baseModel.getMsg());
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        map.put("customerId",customerId);
        if (addressId!=null){
            map.put("addressId",addressId);
        }
        if (data.size()>0){
            List<OrderProductDatasBean> orderProductDatasBeans = new ArrayList<>();
            List<OrderSetMealDatasBean> orderSetMealDatasBeans = new ArrayList<>();
            for (ScanDatasBean scanDatasBean :data){
                if (!scanDatasBean.isSetMeal()) {
                    OrderProductDatasBean datasBean = new OrderProductDatasBean();
                    datasBean.setNum("" + scanDatasBean.getNum());
                    datasBean.setProductId(scanDatasBean.getId());
                    orderProductDatasBeans.add(datasBean);
                }else {
                    OrderSetMealDatasBean mealDatasBean = new OrderSetMealDatasBean();
                    mealDatasBean.setSetMealId(scanDatasBean.getId());
                    mealDatasBean.setNum(""+scanDatasBean.getNum());
                    orderSetMealDatasBeans.add(mealDatasBean);
                }
            }
            map.put("orderProductDatas",orderProductDatasBeans);
            map.put("orderSetMealDatas",orderSetMealDatasBeans);
        }
        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtils.SALES_GOODS + "?access_token=" + access_token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()==PublicUtils.code){
                    SaleDataBean saleData = gson.fromJson(gson.toJson(baseModel.getDatas()),SaleDataBean.class);
                    payAmount = saleData.getSaleData().getPayAmount();
                    tv_get_money_of_now.setText("¥"+payAmount);
                    tv_should_money.setText("¥"+saleData.getSaleData().getAmountPayable());
                    if (saleData.getSaleData().getAddressId()==null){
                        findViewById(R.id.rl_address).setVisibility(View.GONE);
                        findViewById(R.id.ll_address).setVisibility(View.VISIBLE);
                    }else {
                        findViewById(R.id.rl_address).setVisibility(View.VISIBLE);
                        findViewById(R.id.ll_address).setVisibility(View.GONE);
                        tv_name.setText(saleData.getSaleData().getShipPerson());
                        tv_phone.setText(saleData.getSaleData().getShipMobile());
                        tv_details.setText(""+saleData.getSaleData().getShipAddress());
                    }
                }
            }
        });
    }

    private void initView() {
        ((TextView)findViewById(R.id.textHeadTitle)).setText(R.string.seller_table);
        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.iv_next).setVisibility(View.GONE);
        ll_address = findViewById(R.id.ll_address);
        customerId = getIntent().getStringExtra("customerId");
        ll_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,LocationManageActivity.class);
                intent.putExtra("customerId",customerId);
                startActivity(intent);
            }
        });
        tv_confrim = findViewById(R.id.tv_confrim);
        data = (ArrayList<ScanDatasBean>) getIntent().getSerializableExtra("data");
        recyclerView = findViewById(R.id.recycler_view);
        addapter = new SearchResultAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(addapter);
        recyclerView.addItemDecoration(new RecycleViewDivider(SalesActivity.this, LinearLayoutManager.VERTICAL, 10, getResources().getColor(R.color.bg_all)));
        if (data.size()>0){
            addapter.setDate(data,SalesActivity.this,"3",widWidth);
        }
        tv_details = findViewById(R.id.tv_details);
        tv_name = findViewById(R.id.tv_name);
        tv_phone = findViewById(R.id.tv_phone);
        tv_get_state = findViewById(R.id.tv_get_state);
        tv_dispatching_type = findViewById(R.id.tv_dispatching_type);
        tv_get_money_type = findViewById(R.id.tv_get_money_type);
        sv_life = findViewById(R.id.sv_life);
        sv_present = findViewById(R.id.sv_present);
        tv_get_money_of_now = findViewById(R.id.tv_get_money_of_now);
        tv_should_money = findViewById(R.id.tv_should_money);
        edt_content = findViewById(R.id.edt_content);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
