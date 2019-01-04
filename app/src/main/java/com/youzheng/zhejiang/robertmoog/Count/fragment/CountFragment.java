package com.youzheng.zhejiang.robertmoog.Count.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.Base.BaseFragment;
import com.youzheng.zhejiang.robertmoog.Base.request.OkHttpClientManager;
import com.youzheng.zhejiang.robertmoog.Base.utils.PublicUtils;
import com.youzheng.zhejiang.robertmoog.Base.utils.UrlUtils;
import com.youzheng.zhejiang.robertmoog.Count.activity.GoodsSaleActivity;
import com.youzheng.zhejiang.robertmoog.Count.activity.GoodsTypeRankingActivity;
import com.youzheng.zhejiang.robertmoog.Count.activity.MealRankingActivity;
import com.youzheng.zhejiang.robertmoog.Count.activity.StoreSalesNumberActivity;
import com.youzheng.zhejiang.robertmoog.Count.activity.TodayGoodsTypeSaleBestActivity;
import com.youzheng.zhejiang.robertmoog.Count.activity.TodayMealSalesBestActivity;
import com.youzheng.zhejiang.robertmoog.Count.activity.TodayRegisterNumberActivity;
import com.youzheng.zhejiang.robertmoog.Count.activity.TodaySingleGoodsSalesBestDetailActivity;
import com.youzheng.zhejiang.robertmoog.Count.activity.TodayStoreSalesActivity;
import com.youzheng.zhejiang.robertmoog.Count.bean.CountAll;
import com.youzheng.zhejiang.robertmoog.Model.BaseModel;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.utils.SharedPreferencesUtils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Request;

public class CountFragment extends BaseFragment implements BaseFragment.ReloadInterface, View.OnClickListener {

    View mView;
    private View view;
    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    /**
     * 点击重试
     */
    private Button state_layout_error_bt;
    private LinearLayout state_layout_error;
    private LinearLayout state_layout_empty;
    private LinearLayout activity_base_root;
    private RelativeLayout rv_store_number;
    private RelativeLayout rv_sort_ranking_number;
    private ImageView iv_icon;
    private RelativeLayout rv_ranking;
    private RelativeLayout rv_today_store_number;
    private LinearLayout lin_best_meal;
    private RelativeLayout rv_best_meal_sort;
    private LinearLayout lin_best_meal_one;
    private LinearLayout lin_register_number;
    /**
     * 是多久啊是觉得
     */
    private TextView tv_today_meal_content;
    /**
     * 哈哈哈
     */
    private TextView tv_best_today_meal;
    /**
     * 是多久啊是觉得
     */
    private TextView tv_today_one_content;
    /**
     * 哈哈哈
     */
    private TextView tv_today_one;
    /**
     * 是多久啊是觉得
     */
    private TextView tv_register_type;
    /**
     * 哈哈哈
     */
    private TextView tv_number;
    /**
     * 哈哈哈
     */
    private TextView tv_sale_number;
    /**
     * 水槽
     */
    private TextView tv_today_best_ranking;
    private String shopCount,setMealCount,categoryCount,singleProductCount,customerCount,productName,setMealInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.count_fragment_layout, null);
        setUpView(mView);
        initView();
        setReloadInterface(this);
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        final String token = (String) SharedPreferencesUtils.getParam(mContext, PublicUtils.access_token,"");
        OkHttpClientManager.postAsynJson(gson.toJson(new HashMap<>()), UrlUtils.COUNT_ALL + "?access_token=" + token, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e("统计全部",response);
                BaseModel baseModel = gson.fromJson(response,BaseModel.class);
                if (baseModel.getCode()==PublicUtils.code){
                    CountAll countAll = gson.fromJson(gson.toJson(baseModel.getDatas()),CountAll.class);
                    setData(countAll);
                }
            }
        });
    }

    private void setData(CountAll countAll) {
        if (countAll.getDayCountData()==null) return;
        shopCount=countAll.getDayCountData().getShopCount();
        setMealCount=countAll.getDayCountData().getSetMealName();
        categoryCount=countAll.getDayCountData().getCategoryName();
        singleProductCount=countAll.getDayCountData().getProductSku();
        customerCount=countAll.getDayCountData().getCustomerCount();
        productName=countAll.getDayCountData().getProductName();
        setMealInfo=countAll.getDayCountData().getSetMealInfo();
        if (!shopCount.equals("")||shopCount!=null){
            tv_sale_number.setText(shopCount);
        }
        if (!setMealCount.equals("")||setMealCount!=null){
            tv_best_today_meal.setText(setMealCount);
        }
        if (!categoryCount.equals("")||categoryCount!=null){
            tv_today_best_ranking.setText(categoryCount);
        }
        if (!singleProductCount.equals("")||singleProductCount!=null){
            tv_today_one.setText(singleProductCount);
        }
        if (!customerCount.equals("")||customerCount!=null){
            tv_number.setText(customerCount);
        }
        if (!productName.equals("")||productName!=null){
            tv_today_one_content.setText(productName);
        }
        if (!setMealInfo.equals("")||setMealInfo!=null){
            tv_today_meal_content.setText(setMealInfo);
        }

    }

    private void initView() {

        btnBack = (ImageView) mView.findViewById(R.id.btnBack);
        btnBack.setVisibility(View.GONE);
        textHeadTitle = (TextView) mView.findViewById(R.id.textHeadTitle);
        textHeadNext = (TextView) mView.findViewById(R.id.textHeadNext);
        iv_next = (ImageView) mView.findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) mView.findViewById(R.id.layout_header);
        state_layout_error_bt = (Button) mView.findViewById(R.id.state_layout_error_bt);
        state_layout_error = (LinearLayout) mView.findViewById(R.id.state_layout_error);
        state_layout_empty = (LinearLayout) mView.findViewById(R.id.state_layout_empty);
        activity_base_root = (LinearLayout) mView.findViewById(R.id.activity_base_root);
        rv_store_number = (RelativeLayout) mView.findViewById(R.id.rv_sort_ranking_number);
        rv_store_number.setOnClickListener(this);
        rv_sort_ranking_number = (RelativeLayout) mView.findViewById(R.id.rv_goods_number);
        rv_sort_ranking_number.setOnClickListener(this);
        iv_icon = (ImageView) mView.findViewById(R.id.iv_icon);
        rv_ranking = (RelativeLayout) mView.findViewById(R.id.rv_ranking);
        rv_ranking.setOnClickListener(this);
        rv_store_number = (RelativeLayout) mView.findViewById(R.id.rv_store_number);
        rv_store_number.setOnClickListener(this);
        rv_today_store_number = (RelativeLayout) mView.findViewById(R.id.rv_today_store_number);
        rv_today_store_number.setOnClickListener(this);
        lin_best_meal = (LinearLayout) mView.findViewById(R.id.lin_best_meal);
        lin_best_meal.setOnClickListener(this);
        rv_best_meal_sort = (RelativeLayout) mView.findViewById(R.id.rv_best_meal_sort);
        rv_best_meal_sort.setOnClickListener(this);
        lin_best_meal_one = (LinearLayout) mView.findViewById(R.id.lin_best_meal_one);
        lin_best_meal_one.setOnClickListener(this);
        lin_register_number = (LinearLayout) mView.findViewById(R.id.lin_register_number);
        lin_register_number.setOnClickListener(this);
        tv_today_meal_content = (TextView) mView.findViewById(R.id.tv_today_meal_content);
        tv_best_today_meal = (TextView) mView.findViewById(R.id.tv_best_today_meal);
        tv_today_one_content = (TextView) mView.findViewById(R.id.tv_today_one_content);
        tv_today_one = (TextView) mView.findViewById(R.id.tv_today_one);
        tv_register_type = (TextView) mView.findViewById(R.id.tv_register_type);
        tv_number = (TextView) mView.findViewById(R.id.tv_number);
        tv_sale_number = (TextView) mView.findViewById(R.id.tv_sale_number);
        tv_today_best_ranking = (TextView) mView.findViewById(R.id.tv_today_best_ranking);
    }

    @Override
    public void reloadClickListener() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;

            case R.id.rv_store_number://门店销量
                 startActivity(new Intent(getActivity(),StoreSalesNumberActivity.class));
                break;
            case R.id.rv_goods_number://商品销量
                startActivity(new Intent(getActivity(),GoodsSaleActivity.class));
                break;
            case R.id.rv_ranking://套餐排名
                startActivity(new Intent(getActivity(),MealRankingActivity.class));
                break;

            case R.id.rv_sort_ranking_number://商品品类排名
                startActivity(new Intent(getActivity(),GoodsTypeRankingActivity.class));
                break;
            case R.id.rv_today_store_number://今日门店销售额
                startActivity(new Intent(getActivity(),TodayStoreSalesActivity.class));
                break;
            case R.id.lin_best_meal://今日套餐销量最佳
                startActivity(new Intent(getActivity(),TodayMealSalesBestActivity.class));
                break;
            case R.id.rv_best_meal_sort://今日品类销量最佳
                startActivity(new Intent(getActivity(),TodayGoodsTypeSaleBestActivity.class));
                break;
            case R.id.lin_best_meal_one://今日单品销量最佳
                startActivity(new Intent(getActivity(),TodaySingleGoodsSalesBestDetailActivity.class));
                break;
            case R.id.lin_register_number://今日客户注册数
                startActivity(new Intent(getActivity(),TodayRegisterNumberActivity.class));
                break;
        }
    }
}
